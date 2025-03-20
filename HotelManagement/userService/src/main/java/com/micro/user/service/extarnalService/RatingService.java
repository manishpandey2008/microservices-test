package com.micro.user.service.extarnalService;


import com.micro.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRatingByUserId(@PathVariable String userId);
}
