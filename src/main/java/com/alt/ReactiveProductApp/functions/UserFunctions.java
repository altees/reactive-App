package com.alt.ReactiveProductApp.functions;

import com.alt.ReactiveProductApp.domain.User;
import com.alt.ReactiveProductApp.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Component
public class UserFunctions {


    @Autowired
    private UserRepo userRepo;


   public Function<User,Mono<User>> updateUserFunction = user->{
        Mono<User> userMono = getUser(user.getId());
        if (Objects.isNull(userMono))
            try {
                throw new Exception("user not found with userId : " + user.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        return userRepo.save(user);
    };
   public Function<User,Mono<User>> saveUserFunction = user->{
       return userRepo.save(user);
    };
   public Function<String,Mono<Void>> deleteUserFunction = userId->{
       Mono<User> userMono = getUser(userId);
       if (Objects.isNull(userMono))
           try {
               throw new Exception("user not found with userId : " + userId);
           } catch (Exception e) {
               e.printStackTrace();
           }
       return userMono.
               flatMap(userTobeDeleted -> userRepo.delete(userTobeDeleted));
    };

   public Function<String,Mono<User>> getUserFunction = userId->{
        return getUser(userId);
    };

   public Supplier<Flux<User>> getAllUserFunction = ()->{
       return userRepo.findAll().
               switchIfEmpty(Flux.empty());
    };



    public Mono<User> getUser(String id) {
        log.info("finding User by userId : {} ",id);
        return userRepo.findById(id).switchIfEmpty(Mono.empty());
    }
}
