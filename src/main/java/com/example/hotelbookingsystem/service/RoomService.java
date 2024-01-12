package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Room;

import java.util.Optional;

public interface RoomService {
    Optional<Room> createRoom(Room room, int hotelId);
}
