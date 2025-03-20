package com.microservice.userService.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final Logger logger= LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserModel saveUser(UserModel userModel){
        logger.info("Entered into UserService of saveUser method: {}",userModel.getName());
        return userRepo.save(userModel);
    }

    public List<UserModel> getListOfAllUser(){
        return userRepo.findAll();
    }

    public UserModel getByUserId(Long id){
        return userRepo.findById(id).orElse(null);
    }
}
