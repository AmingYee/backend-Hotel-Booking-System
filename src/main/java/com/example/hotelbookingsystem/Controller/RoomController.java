package com.example.hotelbookingsystem.Controller;

import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestParam int hotelId, @RequestBody Room room){
        Optional<Room> savedRoom = roomService.createRoom(room, hotelId);
        if(savedRoom.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom.get());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Room didn't save");
        }
    }
}
