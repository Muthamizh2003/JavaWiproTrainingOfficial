package com.wipro.ecom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecom.dto.AuthRequest;
import com.wipro.ecom.dto.UserDTO;
import com.wipro.ecom.securityservices.UserDetailsImp;
import com.wipro.ecom.securityservices.UserDetailsServiceImp;
import com.wipro.ecom.service.JwtService;
import com.wipro.ecom.service.UserService;

import jakarta.validation.Valid;


@RestController
@EnableMethodSecurity 
@RequestMapping("/users")
public class UserController {
	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDetailsServiceImp userDetailsImp;

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword())); 
        
        return ResponseEntity.ok(service.save(dto));
    }
    
    @PostMapping("/login/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		String token = null;

		if (authentication.isAuthenticated()) {

			// call generate token method from jwtService class

			UserDetails userDetails =
			        userDetailsImp.loadUserByUsername(authRequest.getUsername());

			String role = userDetails.getAuthorities().iterator().next().getAuthority();

			token = jwtService.generateToken(authRequest.getUsername(), role);


			logger.info("Token : " + token);

		} else {

			logger.info("invalid");

			throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");

		}

		return token; // if it returns token in response body means login successful..
	}
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    
    @GetMapping("/{id}")
	 @PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
	 public UserDTO getUser(@PathVariable Long id) {
	     return service.getById(id);
	 }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
