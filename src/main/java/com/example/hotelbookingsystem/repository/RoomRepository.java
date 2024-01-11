package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
