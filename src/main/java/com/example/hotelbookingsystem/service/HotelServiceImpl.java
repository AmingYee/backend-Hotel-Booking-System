package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Hotel;
import com.example.hotelbookingsystem.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Optional<Hotel> createHotel(Hotel hotel) {
        return Optional.of(hotelRepository.save(hotel));
    }
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    @Override
    public Optional<Hotel> updateHotel(Hotel hotel) {
        Optional<Hotel> foundHotel = hotelRepository.findById(hotel.getId());
        if (foundHotel.isPresent()){
            return Optional.of(hotelRepository.save(hotel));
        } else return Optional.empty();
    }

    @Override
    public void deleteHotel(int hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
