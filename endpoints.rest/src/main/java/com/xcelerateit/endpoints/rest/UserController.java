package com.xcelerateit.endpoints.rest;

import com.xcelerateit.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return "User registered: ";
    }

    @GetMapping("/hi")
    public String home() {
        return "Hello,Job Board Application!";
    }
}