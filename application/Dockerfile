FROM openjdk:17

COPY target/application-0.0.1-SNAPSHOT.jar producer.jar
COPY target/ application/target/
ENTRYPOINT ["java", "-jar", "producer.jar"]