package com.micro.user.service.services;


import com.micro.user.service.entities.UserEntity;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity userEntity);

    List<UserEntity> getUsers();

    UserEntity getUserById(String userId) throws IOException;

}
