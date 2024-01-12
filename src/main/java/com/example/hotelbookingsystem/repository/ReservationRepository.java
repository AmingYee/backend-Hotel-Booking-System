package com.example.hotelbookingsystem.repository;

import com.example.hotelbookingsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByClientId(int clientId);

    Optional<Reservation> findByIdAndClientId(int reservationId, int clientId);
}
