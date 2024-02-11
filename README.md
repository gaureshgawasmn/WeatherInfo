# Weather Forecast Service

This Spring Boot application provides RESTful APIs to retrieve weather forecast summaries and hourly forecasts for
different locations.

## Overview

The application integrates with a weather API to fetch weather forecasts for various locations. It exposes two
endpoints:

1. `/weather/summary/{locationName}`: Retrieves the forecast summary for a specific location. This uses the '
   RapidApiGetForecastSummaryByLocationName' API from the weather API.
2. `/weather/hourly/{locationName}`: Retrieves the hourly forecast for a specific location. This uses the '
   RapidApiGetHourlyForecastByLocationName' API from the weather API.

## API Documentation

The API documentation is generated using Swagger. You can access the Swagger UI to explore and test the APIs.

Swagger UI: `http://localhost:8081/swagger-ui/index.html`

## Endpoints

### 1. Get token of authentication

**Endpoint**: `/token`

- **Method**: POST
- **Body** :
    - `username` : User
    - `password` : User@123
- **Response**:
    - Returns the auth token for further use.

### 2. Get Forecast Summary by Location Name

**Endpoint**: `/weather/summary/{locationName}`

- **Method**: GET
- **Headers**:
    - `X-Client-ID` : `your-client_id`
    - `X-Client-Secret` : `your-client_secret`
    - `Authorization` : `<Auth token from API>`
- **Parameters**:
    - `locationName`: The location to retrieve the forecast summary.
- **Response**:
    - Returns the forecast summary in JSON format.

### 3. Get Hourly Forecast by Location Name

**Endpoint**: `/weather/hourly/{locationName}`

- **Method**: GET
- **Headers**:
    - `X-Client-ID` : `your-client_id`
    - `X-Client-Secret` : `your-client_secret`
    - `Authorization` : `<Auth token from API>`
- **Parameters**:
    - `locationName`: The location to retrieve the hourly forecast.
- **Response**:
    - Returns the hourly forecast in JSON format.

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/gaureshgawasmn/WeatherInfo
    ```
2. Import the project into your IDE as a Gradle project. (It should automatically build your project if not then use
   the `gradle build` command from the directory where build.gradle is present)
3. Create a gradle task `bootRun` to run the application. Alternatively, you can run the application from the IDE.
4. While running the bootrun task pass the parameters --args='--service.rapidapi.apiKey=`<your_api_key>`
5. `gradle bootRun --args='--service.rapidapi.apiKey=nYBv1IwB5dLb7lz5wjnTfuIuG8LzrQhO1dhUfvCnM6X2RbH7'`

## To run the distributed application

1. Navigate to the directory `./Distribution` where the jar file is present.
2. Run the following command to run the application.
   ```bash
   java -jar WeatherInfo-0.0.1-SNAPSHOT.jar --service.rapidapi.apiKey=`<your_api_key>`
   ```
   example:
    ```bash 
   java -jar WeatherInfo-0.0.1-SNAPSHOT.jar --service.rapidapi.apiKey=nYBv1IwB5dLb7lz5wjnTfuIuG8LzrQhO1dhUfvCnM6X2RbH7
   ```
   if different java version is set then use following command to locate the java version and run the application.
   ```bash
   C:\Users\<UserName>\.jdks\jdk-17.0.9\bin\java -jar WeatherInfo-0.0.1-SNAPSHOT.jar --service.rapidapi.apiKey=nYBv1IwB5dLb7lz5wjnTfuIuG8LzrQhO1dhUfvCnM6X2RbH7
   ```

Note: The Key mentioned above and in application.yml file is a sample key. Please use your own key.

### Version Information

1. Java 17 is required to run the application.
2. Gradle 8.5 is required to build the application.

### Docker Image Support

1. Gradle task
    - To create the docker images added the gradle task `dockerBuildImage` in build.gradle file.
    - To create the docker image run the following command from the directory where build.gradle is present.
      ```bash
      gradle dockerBuildImage
      ```
    - To run the docker image run the following command.
       ```bash
       docker run -e "service.rapidapi.apiKey=nYBv1IwB5dLb7lz5wjnTfuIuG8LzrQhO1dhUfvCnM6X2RbH7" -p 8082:8081 docker.io/gaureshgawasmn/techlab-weather-info-service:0.0.1-SNAPSHOT
        ```
2. Using Dockerfile
    - To create the image using docker file run the following command from the root directory
       ```bash
       docker build -t <customImageName> .
       ```
      example
        ```bash
       docker build -t gaureshgawasmn/techlab-weather-info-service .
       ```
    - To run the docker image run the following command.
       ```bash
       docker run -e "service.rapidapi.apiKey=nYBv1IwB5dLb7lz5wjnTfuIuG8LzrQhO1dhUfvCnM6X2RbH7" -p 8085:8081 docker.io/gaureshgawasmn/techlab-weather-info-service
        ```