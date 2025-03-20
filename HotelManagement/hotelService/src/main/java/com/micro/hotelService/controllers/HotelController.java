package com.micro.hotelService.controllers;

import com.micro.hotelService.entities.Hotel;
import com.micro.hotelService.services.HotelServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    
    private final HotelServiceImp hotelServiceImp;


    public HotelController(HotelServiceImp hotelServiceImp) {
        this.hotelServiceImp = hotelServiceImp;
    }


    @PostMapping
    private ResponseEntity<Hotel> save(@RequestBody Hotel Hotel){
        return new ResponseEntity<>(hotelServiceImp.saveHotel(Hotel), HttpStatus.CREATED);
    }


    @GetMapping("/{hotelid}")
    private ResponseEntity<Hotel> getHotelById(@PathVariable String hotelid){
        return new ResponseEntity<>(hotelServiceImp.getHotelById(hotelid), HttpStatus.OK);
    }


    @GetMapping
    private ResponseEntity<List<Hotel>> getAllHotels(){
        return new ResponseEntity<>(hotelServiceImp.getHotel(), HttpStatus.OK);
    }
    
}
