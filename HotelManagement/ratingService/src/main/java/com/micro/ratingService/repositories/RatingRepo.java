package com.micro.ratingService.repositories;

import com.micro.ratingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends JpaRepository<Rating,String> {

    List<Rating> findAllByUserId(String userId);
    List<Rating> findAllByHotelId(String hotelId);

}
