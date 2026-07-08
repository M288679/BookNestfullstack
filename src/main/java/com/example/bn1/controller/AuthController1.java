package com.example.bn1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.bn1.entity.SystemUser;
import com.example.bn1.service.AuthService1;
import com.example.bn1.service.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController1 {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AuthService1 authService;

    public AuthController1(JwtService jwtService,
                           AuthenticationManager authenticationManager,
                           AuthService1 authService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SystemUser user) {

        try {

            SystemUser savedUser = authService.register(user);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration Successful");
            response.put("user", savedUser);

            return ResponseEntity.ok(response);

        } catch (Exception e) {

            e.printStackTrace();

            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SystemUser user) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    ));

            String token = jwtService.generateToken(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid Email or Password");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}