package com.example.hotelbookingsystem.Controller;

import com.example.hotelbookingsystem.DTO.ApiResponse;
import com.example.hotelbookingsystem.model.Client;
import com.example.hotelbookingsystem.service.JwtTokenService;
import com.example.hotelbookingsystem.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class ClientController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    //TODO: missing logic to deny creation of a new user with same username
    @PostMapping("/new-user")
    public ResponseEntity<?> postUser(@RequestBody Client client){
        try {
            client.setPwd(passwordEncoder.encode(client.getPwd()));
            Optional<Client> response = userService.postUser(client);
            if (response.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("new user applied"));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse("connection error, try again"));
            }
        }catch (Exception error){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("system error: "+ error);
        }
    }
    @PostMapping("/login-user") public ResponseEntity<?> login(@RequestBody Client client) {
        System.out.println("user" + client);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(client.getUsername(), client.getPwd()));
        if(authentication.isAuthenticated()){
            String jwtToken = jwtTokenService.generateJwtToken(authentication);
            System.out.println("--endpoint login-user: Du er logget på--");
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Authorization", "Bearer " + jwtToken)
                    .body(new ApiResponse("Du er logget på"));
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @GetMapping("/user/id")
    public ResponseEntity<Integer> getUserIdFromToken(@RequestHeader("Authorization") String jwtToken) {
        System.out.println("getidcalled");
        try {
            int userId = jwtTokenService.extractUserIdFromToken(jwtToken);
            System.out.println("userId");
            return ResponseEntity.ok(userId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
