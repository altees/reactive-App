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

    public static final String GET_USER_URI = "/user/{id}";
    public static final String GET_ALL_USER_URI = "/user";
    public static final String SAVE_USER_URI = "/user";
    @Autowired
    private UserHandler userHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        log.info("Route function invoked...");
        return RouterFunctions.
                 route()
                .GET(GET_USER_URI, request -> userHandler.getUser(request)).
                 GET(GET_ALL_USER_URI, request -> userHandler.getAllUser(request)).
                 POST(SAVE_USER_URI, request -> userHandler.saveUser(request))
                .build();
    }
}
