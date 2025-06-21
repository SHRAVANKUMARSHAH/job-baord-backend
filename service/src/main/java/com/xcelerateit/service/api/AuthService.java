package com.xcelerateit.service.api;

import com.xcelerateit.domain.LoginRequest;
import com.xcelerateit.domain.LoginResponse;
import com.xcelerateit.domain.RegisterRequest;


public interface AuthService {
    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
