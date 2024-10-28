package soul.dev.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import soul.dev.gateway.config.RsaConfig;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
                                                        DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

//    @Bean
    RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(r-> r.path("/Enseignants/**").uri("http://localhost:8081"))
                .route(r-> r.path("/Chercheurs/**").uri("http://localhost:8082"))
                .route(r-> r.path("/Projets/**").uri("http://localhost:8083"))
                .build();
    }
}

