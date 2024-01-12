package com.example.hotelbookingsystem.Controller;

import com.example.hotelbookingsystem.DTO.ApiResponse;
import com.example.hotelbookingsystem.model.Hotel;
import com.example.hotelbookingsystem.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        Optional<Hotel> savedHotel = hotelService.createHotel(hotel);
        if (savedHotel.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Hotel didn't save"));
        }
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable int hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        if (hotel != null) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateHotel(@RequestBody Hotel hotel) {
        Optional<Hotel> updatedHotel = hotelService.updateHotel(hotel);
        if (updatedHotel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Hotel not found or couldn't be updated"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Hotel updated successfully"));
        }
    }

    @DeleteMapping("/delete/{hotelId}")
    public ResponseEntity<Void> deleteHotel(@PathVariable int hotelId) {
        hotelService.deleteHotel(hotelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
