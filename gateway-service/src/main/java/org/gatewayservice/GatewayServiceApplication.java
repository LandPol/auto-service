package org.gatewayservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

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
}
