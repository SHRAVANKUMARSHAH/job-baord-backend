package com.xcelerateit.endpoints.rest;

import com.xcelerateit.domain.LoginRequest;
import com.xcelerateit.domain.LoginResponse;
import com.xcelerateit.domain.RegisterRequest;
import com.xcelerateit.service.api.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        System.out.println("Register endpoint called with: " + request);
        authService.register(request);
        return ResponseEntity.ok("Registered successfully " +request);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<?> adminOnlyEndpoint() {
        return ResponseEntity.ok("Hello Admin!");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<?> userOnlyEndpoint() {
        return ResponseEntity.ok("Hello User!");
    }

    @GetMapping("/public")
    public ResponseEntity<?> publicEndpoint() {
        return ResponseEntity.ok("Public data accessible by all");
    }


}


