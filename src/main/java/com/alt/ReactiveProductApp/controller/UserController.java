/*
package com.alt.ReactiveProductApp.controller;

import com.alt.ReactiveProductApp.domain.User;
import com.alt.ReactiveProductApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user/{id}")
    public Mono<User> getUser(@PathVariable String id) {
        return userService.getUser(id).log();
    }

    @GetMapping(value = "/user",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(value = "/user")
    public Mono<User> saveUser(@RequestBody User user) {
        return userService.saveUser(user).log();
    }

    @PutMapping(value = "/user")
    public Mono<User> updateUser(@RequestBody User user) throws Exception {
        return userService.updateUser(user).log();
    }

    @DeleteMapping(value = "/user/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) throws Exception {
        return userService.deleteUser(id).log();
    }


}
*/
