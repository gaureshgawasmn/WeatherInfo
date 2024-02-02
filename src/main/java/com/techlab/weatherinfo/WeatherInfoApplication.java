package com.techlab.weatherinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class WeatherInfoApplication.
 * <p>
 * This class is the main class of the application.
 * This class will start the application using tomcat server.
 */
@SpringBootApplication
public class WeatherInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherInfoApplication.class, args);
    }

}
