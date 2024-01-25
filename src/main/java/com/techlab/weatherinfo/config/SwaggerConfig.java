package com.techlab.weatherinfo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class SwaggerConfig.
 * <p>
 * This class is used to configure the swagger.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public static OpenAPI openAPIMetaInfo() {
        return new OpenAPI()
                .info(new Info().title("API Documentation Weather Info")
                        .description("API developer portal documentation Weather Info")
                        .version("v1"));
    }
}
