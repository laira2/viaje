package com.viaje.viaje.service;

import com.viaje.viaje.model.Users;
import com.viaje.viaje.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public boolean isAdmin(String email) {
        Users user = userRepository.findByEmail(email).orElse(null);
        return user != null && user.isAdmin();
    }
}
