package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> createReservation(int roomId, int clientId, LocalDateTime reservationDate);

    List<Reservation> getClientReservations(int clientId);

    void deleteReservation(int reservationId);
}
