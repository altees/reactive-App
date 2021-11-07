package com.alt.ReactiveProductApp.repo;

import com.alt.ReactiveProductApp.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepo extends ReactiveMongoRepository<User, String> {
}
