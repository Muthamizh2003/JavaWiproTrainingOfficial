package com.wipro.ecom.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.entity.User;
import com.wipro.ecom.repository.UserRepository;
import com.wipro.ecom.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    public User save(User user) {
        return repo.save(user);
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}