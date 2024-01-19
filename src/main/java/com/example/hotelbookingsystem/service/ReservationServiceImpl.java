package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Client;
import com.example.hotelbookingsystem.model.Reservation;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.repository.ClientRepository;
import com.example.hotelbookingsystem.repository.ReservationRepository;
import com.example.hotelbookingsystem.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Reservation> getClientReservations(int clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    @Override
    public Optional<Reservation> createReservation(int roomId, int clientId, LocalDateTime reservationDate) {
        Reservation reservation = new Reservation();

        if (reservationDate == null) {
            throw new IllegalArgumentException("Invalid reservation data");
        }

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        reservation.setRoom(room);
        reservation.setClient(client);
        reservation.setReservationDate(reservationDate);
        reservation.setCreated(LocalDateTime.now());
        reservation.setUpdated(LocalDateTime.now());

        return Optional.of(reservationRepository.save(reservation));
    }

    @Override
    public void deleteReservation(int reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}
