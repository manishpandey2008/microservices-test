package com.micro.ratingService.services;

import com.micro.ratingService.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating saveRating(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);

    Rating getRatingById(String id);

}
