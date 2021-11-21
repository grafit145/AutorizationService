FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ADD target/demo-0.0.1-SNAPSHOT.jar autorizationService.jar
ENTRYPOINT ["java", "-jar", "autorizationService.jar"]
