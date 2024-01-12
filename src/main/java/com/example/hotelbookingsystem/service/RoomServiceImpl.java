package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Hotel;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.repository.HotelRepository;
import com.example.hotelbookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Optional<Room> createRoom(Room room, int hotelId){
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        room.setHotel(hotel);
        room.setCreated(LocalDateTime.now());
        room.setUpdated(LocalDateTime.now());

        try {
            Room savedRoom = roomRepository.save(room);
            return Optional.of(savedRoom);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
