FROM openjdk:17
EXPOSE 8080
workdir ./web-app
ADD build/libs/ProBackend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "--enable-preview", "app.jar"]