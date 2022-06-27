package com.alt.ReactiveProductApp.router;

import com.alt.ReactiveProductApp.handler.UserHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class UserRouter {

    @Autowired
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        log.info("Route function invoked...");
        return RouterFunctions.
                 route()
                .GET("/user/{id}", request -> userHandler.getUser(request)).
                 GET("/user", request -> userHandler.getAllUser(request)).
                 POST("/user", request -> userHandler.saveUser(request))
                .build();
    }
}
