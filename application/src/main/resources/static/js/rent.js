var app = angular.module('rents', []).config(function ($httpProvider) {
    csrftoken = $("meta[name='_csrf']").attr("content");
    csrfheader = $("meta[name='_csrf_header']").attr("content");
    $httpProvider.defaults.headers.common["X-CSRF-TOKEN"] = csrftoken;
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(csrfheader, csrftoken);
    });
});;

app.controller("RentController", function ($scope, $http) {

    $scope.successGetRentsCallback = function (response) {
         $scope.rentsList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetRentsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of rents";
    };

    $scope.getRents = function () {
        $http.get('/api/v1/rent/').then($scope.successGetRentsCallback, $scope.errorGetSchoolsCallback);
    };

    $scope.successDeleteRentCallback = function (response) {
        for (var i = 0; i < $scope.rentsList.length; i++) {
            var j = $scope.rentsList[i];
            if (j.id === $scope.deletedId) {
                $scope.rentsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteRentCallback = function (error) {
        console.log(error);
        $scope.errormessage=" Unable to delete rent";
    };

    $scope.deleteRent = function (id) {
        $scope.deletedId = id;
        $http.delete('api/v1/rent/' + id).then($scope.successDeleteRentCallback, $scope.errorDeleteRentCallback);
    };


    $scope.successAddRentCallback = function (response) {
        $scope.rentsList.push(response.data);
        $scope.errormessage="";
    };

    $scope.errorAddRentCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add rent";
    };

    $scope.addRent = function () {
               $http.post('/api/v1/rent/?address='+$scope.address+"&cost="+$scope.cost + "&status=" +$scope.status)
               .then($scope.successAddRentCallback, $scope.errorAddRentCallback);
    };
  $scope.successUpdateRentCallback = function (response) {
        for (var i = 0; i < $scope.rentsList.length; i++) {
              var j = $scope.rentsList[i];
              if (j.id === $scope.updatedId) {
                  $scope.rentsList.splice(i, 1);
                  break;
              }
          }
        $scope.rentsList.push(response.data);
        $scope.errormessage="";
    };

    $scope.errorUpdateRentCallback = function (error) {

        console.log(error);
        $scope.errormessage="Impossible to update rent";
    };

    $scope.updateRent = function () {
        var parameter =JSON.stringify({address:$scope.updatedAddress, cost:$scope.updatedCost,
                              status:$scope.updatedStatus, id:$scope.updatedId});
        $http.put('/api/v1/rent/'+$scope.updatedId, parameter )
               .then($scope.successUpdateRentCallback, $scope.errorUpdateRentCallback);
    };

});
