package com.techlab.weatherinfo.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The Class WeatherAPIConfig.
 * <p>
 * This class is used to configure the weather api properties.
 */
@Component
@ConfigurationProperties(prefix = "service.rapidapi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherAPIConfig {
    private String url;
    private String apiKey;
    private String host;
}
