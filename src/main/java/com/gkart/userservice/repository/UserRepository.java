package com.gkart.userservice.repository;

import com.gkart.userservice.model.UserDao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDao, String> {

    Boolean existsByEmail(String email);

    Boolean existsByMobile(String mobile);

    UserDao findByUsername(String username);
}
