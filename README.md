# Weather Forecast Service

This Spring Boot application provides RESTful APIs to retrieve weather forecast summaries and hourly forecasts for different locations.

## Overview

The application integrates with a weather API to fetch weather forecasts for various locations. It exposes two endpoints:

1. `/weather/summary/{locationName}`: Retrieves the forecast summary for a specific location. This uses 'RapidApiGetForecastSummaryByLocationName' API from the weather API.
2. `/weather/hourly/{locationName}`: Retrieves the hourly forecast for a specific location. This uses 'RapidApiGetHourlyForecastByLocationName' API from the weather API.

## API Documentation

The API documentation is generated using Swagger. You can access the Swagger UI to explore and test the APIs.

Swagger UI: `http://localhost:8081/swagger-ui/index.html`

## Endpoints

### Get Forecast Summary by Location Name

**Endpoint**: `/weather/summary/{locationName}`

- **Method**: GET
- **Parameters**:
    - `locationName`: Name of the location for which to retrieve the forecast summary.
- **Response**:
    - Returns the forecast summary.

### Get Hourly Forecast by Location Name

**Endpoint**: `/weather/hourly/{locationName}`

- **Method**: GET
- **Parameters**:
    - `locationName`: Name of the location for which to retrieve the hourly forecast.
- **Response**:
    - Returns the hourly forecast.

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/gaureshgawasmn/WeatherInfo
    ```
2. Import the project into your IDE as Gradle project.
3. Create gradle task `bootRun` to run the application. Alternatively, you can run the application from the IDE.
4. While creating the bootrun task pass the parameters --args='--service.rapidapi.apiKey=`<your_api_key>`
5. `gradle bootRun --args='--service.rapidapi.apiKey=nYBv1IwB5dLb7lz5wjnTfuIuG8LzrQhO1dhUfvCnM6X2RbH7'`

Note: The Key mentioned above and in application.yml file is a sample key. Please use your own key.
