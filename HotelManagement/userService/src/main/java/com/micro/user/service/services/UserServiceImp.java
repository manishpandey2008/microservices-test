package com.micro.user.service.services;

import com.micro.user.service.entities.Rating;
import com.micro.user.service.entities.UserEntity;
import com.micro.user.service.exceptions.ResourceNotFoundException;
import com.micro.user.service.extarnalService.RatingService;
import com.micro.user.service.reositories.UserRepo;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService{

    private final UserRepo userRepo;
    private final RestTemplate restTemplate;
    private final RatingService ratingService;

    public UserServiceImp(UserRepo userRepo, RestTemplate restTemplate, RatingService ratingService) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
        this.ratingService = ratingService;
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        if(userEntity.getUserId()==null || userEntity.getUserId().isEmpty()){
            String id= UUID.randomUUID().toString();
            userEntity.setUserId(id);
        }
        return this.userRepo.save(userEntity);
    }

    @Override
    public List<UserEntity> getUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public UserEntity getUserById(String userId) {
        UserEntity userEntity=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found of "+ userId));
        // Call API call
        List<Rating> ratings=ratingService.getRatingByUserId(userId);
//      restTemplate.getForObject("http://RATINGSERVICE/ratings/user/"+userId,List.class);
        userEntity.setRatings(ratings);
        return userEntity;
    }
}
