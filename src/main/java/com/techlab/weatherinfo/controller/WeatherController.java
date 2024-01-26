package com.techlab.weatherinfo.controller;

import com.techlab.weatherinfo.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class WeatherController
 * <p>
 * This class is used to handle the weather related requests.
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Parameter(in = ParameterIn.HEADER, name = "X-Client-ID", description = "Client ID", required = true, schema = @Schema(type = "string"), example = "CLIENT-295")
    @Parameter(in = ParameterIn.HEADER, name = "X-Client-Secret", description = "Client Secret", required = true, schema = @Schema(type = "string"), example = "WAPI-295-dhUfvCnM6X2RbH7")
    @Operation(description = "Get forecast summary by location name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved forecast summary"),
            @ApiResponse(responseCode = "404", description = "Location not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/summary/{locationName}")
    public ResponseEntity<byte[]> getForecastSummaryByLocationName(
            @Parameter(description = "Name of the location for forecast summary", example = "Berlin")
            @PathVariable String locationName) {
        return weatherService.getForecastSummaryByLocationName(locationName);
    }

    @Parameter(in = ParameterIn.HEADER, name = "X-Client-ID", description = "Client ID", required = true, schema = @Schema(type = "string"), example = "CLIENT-295")
    @Parameter(in = ParameterIn.HEADER, name = "X-Client-Secret", description = "Client Secret", required = true, schema = @Schema(type = "string"), example = "WAPI-295-dhUfvCnM6X2RbH7")
    @Operation(description = "Get hourly forecast by location name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved hourly forecast"),
            @ApiResponse(responseCode = "404", description = "Location not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/hourly/{locationName}")
    public ResponseEntity<byte[]> getHourlyForecastByLocationName(
            @Parameter(description = "Name of the location for hourly forecast", example = "Berlin")
            @PathVariable String locationName) {
        return weatherService.getHourlyForecastByLocationName(locationName);
    }
}
