package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.constant.SecurityConstants;
import com.example.hotelbookingsystem.model.Client;
import com.example.hotelbookingsystem.repository.ClientRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtTokenServiceImpl implements JwtTokenService{
    @Autowired
    ClientRepository clientRepository;
    public String generateJwtToken(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        Set<String> authorities = authentication.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .setIssuer("Wenmin")
                .setSubject("JWT Token")
                .claim("username", authentication.getName())
                .claim("authorities", String.join(",", authorities))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key)
                .compact();
    }

    //TODO: this method should be changed to extract principal and include username and authorities too alternativly could change it to a filter that does it automatically
    public int extractUserIdFromToken(String jwtToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        String username = claims.get("username", String.class);
        Optional<Client> clientOptional = clientRepository.findByUsername(username);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return client.getId();
        } else {
            throw new IllegalArgumentException("User not found for the provided username");
        }
    }
}
