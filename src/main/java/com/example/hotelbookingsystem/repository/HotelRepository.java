package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
