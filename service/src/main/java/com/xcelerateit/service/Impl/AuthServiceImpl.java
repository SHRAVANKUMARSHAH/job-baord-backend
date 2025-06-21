package com.xcelerateit.service.Impl;


import com.xcelerateit.domain.User;
import com.xcelerateit.repository.UserRepository;
import com.xcelerateit.security.JwtUtil;
import com.xcelerateit.domain.LoginRequest;
import com.xcelerateit.domain.LoginResponse;
import com.xcelerateit.domain.RegisterRequest;
import com.xcelerateit.service.api.AuthService;
import com.xcelerateit.service.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public  class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void register(RegisterRequest request) {
        
        User user = new User(
                request.name,
                request.email,
                passwordEncoder.encode(request.password),
                request.role
        );

        logger.info("User info : "+user);
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token);
    }
}

