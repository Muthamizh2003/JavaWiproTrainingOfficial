package com.wipro.ecom.services;

import java.util.List;

import com.wipro.ecom.dto.UserDTO;

public interface UserService {


	UserDTO save(UserDTO dto);
	
	List<UserDTO> getAll();
	
	UserDTO getById(Long id);


    void delete(Long id);
}