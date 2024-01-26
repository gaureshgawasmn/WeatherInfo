package com.techlab.weatherinfo.service;

import com.techlab.weatherinfo.config.WeatherAPIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * The class WeatherService
 * <p>
 * This class is used to handle the weather related requests.
 */
@Service
public class WeatherService {

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
        String url = weatherAPIConfig.getUrl() + "/rapidapi/forecast/" + locationName + "/summary/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", weatherAPIConfig.getApiKey());
        headers.set("X-RapidAPI-Host", weatherAPIConfig.getHost());
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
        String url = weatherAPIConfig.getUrl() + "/rapidapi/forecast/" + locationName + "/hourly/";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", weatherAPIConfig.getApiKey());
        headers.set("X-RapidAPI-Host", weatherAPIConfig.getHost());
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        return restTemplate.exchange(requestEntity, Object.class);
    }
}
