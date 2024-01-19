package com.example.hotelbookingsystem.Controller;

import com.example.hotelbookingsystem.DTO.ApiResponse;
import com.example.hotelbookingsystem.model.Reservation;
import com.example.hotelbookingsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> createReservation(
            @RequestParam int roomId,
            @RequestParam int clientId,
            @RequestBody Reservation reservation) {

        LocalDateTime reservationDate = reservation.getReservationDate();

        Optional<Reservation> savedReservation = reservationService.createReservation(roomId, clientId, reservationDate);

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

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable int reservationId) {
        try {
            reservationService.deleteReservation(reservationId);
            return ResponseEntity.ok(new ApiResponse("Reservation deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error deleting reservation"));
        }
    }
}
