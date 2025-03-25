package com.micro.hotelService.services;


import com.micro.hotelService.entities.Hotel;
import com.micro.hotelService.exceptions.ResourceNotFoundException;
import com.micro.hotelService.ropositories.HotelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImp implements HotelService {

    private final HotelRepo hotelRepo;

    public HotelServiceImp(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        if(hotel.getId()==null){
            String id= UUID.randomUUID().toString();
            hotel.setId(id);
        }
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getHotel() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        return hotelRepo.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel Not Found for id "+hotelId));
    }
}
