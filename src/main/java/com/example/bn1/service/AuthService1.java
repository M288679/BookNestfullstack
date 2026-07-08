package com.example.bn1.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bn1.entity.SystemUser;
import com.example.bn1.repository.SystemUserRepo;

@Service
public class AuthService1 {

    private final PasswordEncoder encoder;
    private final SystemUserRepo userRepo;    

    public AuthService1(PasswordEncoder encoder, SystemUserRepo userRepo) {
        this.encoder = encoder;
        this.userRepo = userRepo;
    }

    public SystemUser register(SystemUser user) {
       user.setPassword(encoder.encode(user.getPassword()));
       return userRepo.save(user);
    }
    public boolean verifyPassword(String email, String password) {

    SystemUser user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // System.out.println("Entered Password : " + password);
    // System.out.println("Stored Password  : " + user.getPassword());

    boolean result = encoder.matches(password, user.getPassword());

    System.out.println("Password Match : " + result);

    return result;
}


}

