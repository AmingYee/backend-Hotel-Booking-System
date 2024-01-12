package com.example.hotelbookingsystem.config;

import com.example.hotelbookingsystem.model.Hotel;
import com.example.hotelbookingsystem.model.Room;
import com.example.hotelbookingsystem.repository.HotelRepository;
import com.example.hotelbookingsystem.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class TestData implements CommandLineRunner {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public TestData(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // lower the dummy hotels from 250 if its laggy haven't optimized the calls so it fetches all of them
        for (int i = 1; i <= 250; i++) {
            Hotel hotel = createDummyHotel("TestHotel nr: " + i);
            hotelRepository.save(hotel);

            int numberOfRooms = new Random().nextInt(31) + 10;
            for (int i2 = 1; i2 <= numberOfRooms; i2++) {
                Room room = createDummyRoom("Room nr: " + i2, new Random().nextInt(4) + 1);
                room.setHotel(hotel);
                roomRepository.save(room);
            }
        }
    }

    private Hotel createDummyHotel(String name) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setStreet("Dummy Street");
        hotel.setCity("Dummy City");
        hotel.setZip("2900");
        hotel.setCountry("Dummy Country");
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());
        return hotel;
    }

    private Room createDummyRoom(String roomNumber, int numberOfBeds) {
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setNumberOfBeds(numberOfBeds);
        room.setCreated(LocalDateTime.now());
        room.setUpdated(LocalDateTime.now());
        return room;
    }
}
