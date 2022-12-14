FROM openjdk:17

COPY target/logger-0.0.1-SNAPSHOT.jar application/consumer.jar
WORKDIR application
ENTRYPOINT ["java", "-jar", "consumer.jar"]