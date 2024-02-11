FROM eclipse-temurin:17-jdk-alpine
WORKDIR /usr/app/weather-info-app
COPY build/libs/WeatherInfo-0.0.1-SNAPSHOT.jar ./weather-info-app.jar
ENTRYPOINT ["java","-jar","/usr/app/weather-info-app/weather-info-app.jar"]