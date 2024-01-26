package com.techlab.weatherinfo.service;

import com.techlab.weatherinfo.config.WeatherAPIConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.techlab.weatherinfo.constants.ApplicationConstants.X_RAPID_API_HOST;
import static com.techlab.weatherinfo.constants.ApplicationConstants.X_RAPID_API_KEY;

/**
 * The class WeatherService
 * <p>
 * This class is used to handle the weather related requests.
 */
@Service
public class WeatherService {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private WeatherAPIConfig weatherAPIConfig;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get forecast summary by location name
     *
     * @param locationName Name of the location for forecast summary
     * @return
     */
    public ResponseEntity<Object> getForecastSummaryByLocationName(String locationName) {
        logger.info("Request received for forecast summary by location name: {}", locationName);
        String url = weatherAPIConfig.getUrl() + "/rapidapi/forecast/" + locationName + "/summary/";
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_RAPID_API_KEY, weatherAPIConfig.getApiKey());
        headers.set(X_RAPID_API_HOST, weatherAPIConfig.getHost());
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        return restTemplate.exchange(requestEntity, Object.class);
    }

    /**
     * Get hourly forecast by location name
     *
     * @param locationName Name of the location for hourly forecast
     * @return
     */
    public ResponseEntity<Object> getHourlyForecastByLocationName(String locationName) {
        logger.info("Request received for hourly forecast by location name: {}", locationName);
        String url = weatherAPIConfig.getUrl() + "/rapidapi/forecast/" + locationName + "/hourly/";
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_RAPID_API_KEY, weatherAPIConfig.getApiKey());
        headers.set(X_RAPID_API_HOST, weatherAPIConfig.getHost());
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        return restTemplate.exchange(requestEntity, Object.class);
    }

}
