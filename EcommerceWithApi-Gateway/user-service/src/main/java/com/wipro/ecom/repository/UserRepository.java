package com.wipro.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.ecom.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
