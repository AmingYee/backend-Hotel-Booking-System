package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> createReservation(int roomId, int clientId, Reservation reservation);

    List<Reservation> getClientReservations(int clientId);
}
