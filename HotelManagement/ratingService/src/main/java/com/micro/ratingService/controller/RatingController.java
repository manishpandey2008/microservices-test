package com.micro.ratingService.controller;


import com.micro.ratingService.entities.Rating;
import com.micro.ratingService.services.RatingServiceImp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingServiceImp ratingServiceImp;

    public RatingController(RatingServiceImp ratingServiceImp) {
        this.ratingServiceImp = ratingServiceImp;
    }

    @PostMapping
    public Rating saveRating(@RequestBody Rating rating) {
        return ratingServiceImp.saveRating(rating);
    }

    @GetMapping
    public List<Rating> getRatings() {
        return ratingServiceImp.getRatings();
    }

    @GetMapping("/user/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable String userId) {
        return ratingServiceImp.getRatingsByUserId(userId);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Rating> getRatingsByHotelId(@PathVariable String hotelId) {
        return ratingServiceImp.getRatingsByHotelId(hotelId);
    }

    @GetMapping("/{id}")
    public Rating getRatingById(@PathVariable String id) {
        return ratingServiceImp.getRatingById(id);
    }
}
