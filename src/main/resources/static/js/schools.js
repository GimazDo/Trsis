var app = angular.module('rents', []);

app.controller("RentController", function ($scope, $http) {

    $scope.successGetRentsCallback = function (response) {
         $scope.rentList = response.data;
        $scope.errormessage="";
    };

    $scope.errorGetSchoolsCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get list of rents";
    };

    $scope.getRents = function () {
        $http.get('/api/v1/rent/').then($scope.successGetRentsCallback, $scope.errorGetSchoolsCallback);
    };

    $scope.successDeleteSchoolCallback = function (response) {
        for (var i = 0; i < $scope.schoolsList.length; i++) {
            var j = $scope.schoolsList[i];
            if (j.id === $scope.deletedId) {
                $scope.schoolsList.splice(i, 1);
                break;
            }
        }
        $scope.errormessage="";
    };

    $scope.errorDeleteSchoolCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to delete school; check if there are any related records exist. Such records should be removed first";
    };

    $scope.deleteSchool = function (id) {
        $scope.deletedId = id;
        $http.delete('/public/rest/schools/' + id).then($scope.successDeleteSchoolCallback, $scope.errorDeleteSchoolCallback);
    };


    $scope.successGetSchoolCallback = function (response) {
        $scope.schoolsList.splice(0, 0, response.data);
        $scope.errormessage="";
    };

    $scope.errorGetSchoolCallback = function (error) {
        console.log(error);
        $scope.errormessage="Unable to get information on school number "+$scope.inputnumber;
    };

    $scope.successAddSchoolCallback = function (response) {
        $http.get('/public/rest/schools/' + $scope.inputnumber).then($scope.successGetSchoolCallback, $scope.errorGetSchoolCallback);
        $scope.errormessage="";
    };

    $scope.errorAddSchoolCallback = function (error) {
        console.log(error);
        $scope.errormessage="Impossible to add new school; check if it's number is unique";
    };

    $scope.addRent = function () {
        var body = [];
               $http.post('/api/v1/rent/', body).then($scope.successAddSchoolCallback, $scope.errorAddSchoolCallback);
    };

});
