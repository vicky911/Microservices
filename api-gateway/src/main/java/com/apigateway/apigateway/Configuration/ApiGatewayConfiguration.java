package com.apigateway.apigateway.Configuration;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

//It is used to indicate that class has the bean definition methods

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        //This is used when we are not customizing the route
        //return builder.routes().build();

/*        Function<PredicateSpec, Buildable<Route>> routeFunction=p -> p.path("/get")
                .filters(f -> f.addRequestHeader("Myheader","MyUri")
                        .addRequestParameter("Params","MyValue"))
                .uri("http://httpbin.org:80");*/

        //if request comes to get then it will redirect http://httpbin.org:80

        //path parameter will be matched with coming uri(website) and it will find the in naming server.
        return builder.routes()
                .route(p -> p.path("/get")
                .filters(f -> f.addRequestHeader("Myheader","MyUri")
                        .addRequestParameter("Params","MyValue"))
                .uri("http://httpbin.org:80"))

                .route(p -> p.path("/currency-exchange/**")
                        .uri("lb://currency-exchange"))//find currency-exchange in naming server and redirect to that

                .route(p -> p.path("/currency-conversion/**")
                        .uri("lb://currency-conversion"))

                .route(p -> p.path("/currency-conversion-feign/**")
                        .uri("lb://currency-conversion"))

                .route(p -> p.path("/currency-conversion-new/**")
                        .filters(f -> f.rewritePath(
                                "/currency-conversion-new/(?<extrapart>.*)",
                                "/currency-conversion-feign/${extrapart}"))
                        .uri("lb://currency-conversion"))

                .build();

        /* */
    }
}
