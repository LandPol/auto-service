package org.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${client.service.url}") String clientUrl,
            @Value("${car.service.url}") String carUrl,
            @Value("${gateway.host}") String host
    ) {
        return builder.routes()
                .route("car-service", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/clients/{clientId}/cars",
                                "/api/clients/{clientId}/cars/**"
                        )
                        .uri(carUrl)
                )
                .route("client-service", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/api/clients",
                                "/api/clients/**"
                        )
                        .uri(clientUrl)
                )
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

}
