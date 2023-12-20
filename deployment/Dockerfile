FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
ADD ../target/movie-catalogue-service-0.0.1-SNAPSHOT.jar movie-catalogue-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "movie-catalogue-service.jar"]