package com.wipro.ecom.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(
	    name = "USER-SERVICE",
	    configuration = com.wipro.ecom.config.FeignConfig.class
	)
public interface UserClient {

    @GetMapping("/users/{id}")
    Object getUser(@PathVariable("id") Long id);
}