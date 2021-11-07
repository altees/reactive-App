package com.alt.ReactiveProductApp.service;

import com.alt.ReactiveProductApp.domain.User;
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

    public Mono<User> saveUser(User user) {
        return userRepo.save(user);
    }

    public Mono<User> getUser(String id) {
        log.info("finding User by userId : {} ",id);
        return userRepo.findById(id).switchIfEmpty(Mono.empty());
    }

    public Flux<User> getAllUser() {
        return userRepo.findAll().
                    switchIfEmpty(Flux.empty());
    }

    public Mono<User> updateUser(User user) throws Exception {
        Mono<User> userMono = getUser(user.getId());
        if (Objects.isNull(userMono))
            throw new Exception("user not found with userId : " + user.getId());
        return userRepo.save(user);
    }

    public Mono<Void> deleteUser(String id) throws Exception {
        Mono<User> userMono = getUser(id);
        if (Objects.isNull(userMono))
            throw new Exception("user not found with userId : " + id);
        return userMono.
                flatMap(userTobeDeleted -> userRepo.delete(userTobeDeleted));
    }


}
