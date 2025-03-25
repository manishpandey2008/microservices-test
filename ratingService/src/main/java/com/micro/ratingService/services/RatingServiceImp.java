package com.micro.ratingService.services;


import com.micro.ratingService.entities.Rating;
import com.micro.ratingService.exceptions.ResourceNotFoundException;
import com.micro.ratingService.repositories.RatingRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImp implements RatingService{

    private final RatingRepo ratingRepo;

    public RatingServiceImp(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public Rating saveRating(Rating rating) {
        if(rating.getId()==null){
            String id= UUID.randomUUID().toString();
            rating.setId(id);
        }
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepo.findAllByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepo.findAllByHotelId(hotelId);
    }

    @Override
    public Rating getRatingById(String id) {
        return ratingRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found rating by id "+ id));
    }
}
