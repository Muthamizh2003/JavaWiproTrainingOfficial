package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.entity.User;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User getById(Long id);

    void delete(Long id);
}