package com.wipro.ecom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wipro.ecom.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}