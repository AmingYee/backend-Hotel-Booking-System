package com.example.hotelbookingsystem.Controller;

import com.example.hotelbookingsystem.DTO.ApiResponse;
import com.example.hotelbookingsystem.model.Reservation;
import com.example.hotelbookingsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create-reservation")
    public ResponseEntity<?> createReservation(
            @RequestParam int roomId,
            @RequestParam int clientId,
            @RequestBody Reservation reservation) {

        Optional<Reservation> savedReservation = reservationService.createReservation(roomId, clientId, reservation);

        if (savedReservation.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Reservation didn't save"));
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Reservation>> getClientReservations(@PathVariable int clientId) {
        List<Reservation> clientReservations = reservationService.getClientReservations(clientId);
        return new ResponseEntity<>(clientReservations, HttpStatus.OK);
    }
}
