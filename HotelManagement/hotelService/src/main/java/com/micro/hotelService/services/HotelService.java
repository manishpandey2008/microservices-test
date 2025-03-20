package com.micro.hotelService.services;

import com.micro.hotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);

    List<Hotel> getHotel();

    Hotel getHotelById(String userId);
}
