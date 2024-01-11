package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
