package com.niit.userService.UserService.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "favourite-list-service",url = "http://localhost:8081/favourite/")
public interface FavouriteProxy {

    @PostMapping("favAcc/{email}")
    ResponseEntity createFavouriteAccount(@PathVariable String email);
}
