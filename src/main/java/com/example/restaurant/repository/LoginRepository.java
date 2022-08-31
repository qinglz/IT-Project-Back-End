package com.example.restaurant.repository;

import com.example.restaurant.pojo.BusinessUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository<BusinessUser,String> {
    BusinessUser findBusinessUserByEmail(String email);

}
