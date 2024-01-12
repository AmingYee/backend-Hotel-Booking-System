package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    Optional<Hotel> createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotelById(int hotelId);

    Optional<Hotel> updateHotel(Hotel hotel);

    void deleteHotel(int hotelId);
}
