package com.microservice.userService.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;
    private final Logger logger= LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("save")
    public UserModel saveUser(@RequestBody UserModel userModel){
        return userService.saveUser(userModel);
    }

    @GetMapping
    public List<UserModel> getAllUsers(){
        return userService.getListOfAllUser();
    }

    @GetMapping("/:id")
    public UserModel getUserById(@PathVariable("id") Long id){
        return userService.getByUserId(id);
    }

}
