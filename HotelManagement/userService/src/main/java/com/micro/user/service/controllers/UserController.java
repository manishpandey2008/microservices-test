package com.micro.user.service.controllers;

import com.micro.user.service.entities.Rating;
import com.micro.user.service.entities.UserEntity;
import com.micro.user.service.services.UserService;
import com.micro.user.service.services.UserServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImp userServiceImp;

    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @PostMapping
    private ResponseEntity<UserEntity> save(@RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userServiceImp.saveUser(userEntity), HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    private ResponseEntity<UserEntity> getUserById(@PathVariable String userid) throws IOException {
        return new ResponseEntity<>(userServiceImp.getUserById(userid), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<UserEntity>> getAllUser(){
        return new ResponseEntity<>(userServiceImp.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/rating")
    private Rating saveRatingByUser(@RequestBody Rating rating){
        return userServiceImp.saveRating(rating);
    }

}
