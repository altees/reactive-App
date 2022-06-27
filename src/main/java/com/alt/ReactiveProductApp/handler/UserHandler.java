package com.alt.ReactiveProductApp.handler;

import com.alt.ReactiveProductApp.domain.User;
import com.alt.ReactiveProductApp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserHandler {

    @Autowired
    private UserService userService;


    public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<User> user = userService.getUser(id);
        log.info(user.toString());
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(user, User.class);
    }

    public Mono<ServerResponse> getAllUser(ServerRequest serverRequest) {
        Flux<User> allUser = userService.getAllUser();
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allUser, User.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest serverRequest) {
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return userMono.flatMap(user -> {
            return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userService.saveUser(user), User.class);
        });
    }
}
