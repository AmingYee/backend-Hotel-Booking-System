package com.example.hotelbookingsystem.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface JwtTokenService {
    String generateJwtToken(Authentication authentication);
}
