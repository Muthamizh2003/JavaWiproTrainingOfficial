package com.wipro.ecom.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.ecom.dto.UserDTO;
import com.wipro.ecom.entity.User;
import com.wipro.ecom.repository.UserRepository;
import com.wipro.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

	private User convertToEntity(UserDTO dto) {
	    User user = new User();
	    user.setName(dto.getName());
	    user.setEmail(dto.getEmail());

	    user.setUsername(dto.getUsername()); 
	    user.setPassword(dto.getPassword()); 
	    user.setRole(dto.getRole()); 

	    return user;
	}
	
	private UserDTO convertToDTO(User user) {
	    UserDTO dto = new UserDTO();
	    dto.setId(user.getId());
	    dto.setName(user.getName());
	    dto.setEmail(user.getEmail());
	    return dto;
	}

	public UserDTO save(UserDTO dto) {
	    User user = convertToEntity(dto);
	    User saved = repo.save(user);
	    return convertToDTO(saved);
	}
	
	public List<UserDTO> getAll() {
	    return repo.findAll()
	               .stream()
	               .map(this::convertToDTO)
	               .toList();
	}

    public UserDTO getById(Long id) {
        User user = repo.findById(id).orElseThrow();
        return convertToDTO(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}