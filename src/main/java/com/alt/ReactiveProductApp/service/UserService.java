package com.alt.ReactiveProductApp.service;

import com.alt.ReactiveProductApp.domain.User;
import com.alt.ReactiveProductApp.functions.UserFunctions;
import com.alt.ReactiveProductApp.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserFunctions userFunctions;

    public Mono<User> saveUser(User user) {
        return userFunctions.saveUserFunction.apply(user);
    }

    public Mono<User> getUser(String id) {
      return userFunctions.getUserFunction.apply(id);
    }

    public Flux<User> getAllUser() {
       return userFunctions.getAllUserFunction.get();
    }

    public Mono<User> updateUser(User user) throws Exception {
       return userFunctions.updateUserFunction.apply(user);
    }

    public Mono<Void> deleteUser(String id) throws Exception {
       return  userFunctions.deleteUserFunction.apply(id);
    }


}
