package com.example.hotelbookingsystem.service;

import com.example.hotelbookingsystem.model.Client;
import com.example.hotelbookingsystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUser kaldt: user=" + username);
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        Optional<Client> user = null;
        try {
            user = clientRepository.findByUsername(username);
        } catch (Exception ex) {
            System.out.println("Database fejl =" + ex.getMessage());
        }
        if (user.isPresent()) {
            userName = user.get().getUsername();
            password = user.get().getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.get().getRole()));
        } else {
            throw new UsernameNotFoundException("User details not found for the user:" + username);
        }
        return new User(username,password,authorities);
    }

    public Optional<Client> postUser(Client client) {
        return Optional.of(clientRepository.save(client));
    }
}
