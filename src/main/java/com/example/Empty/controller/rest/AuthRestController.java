package com.example.Empty.controller.rest;

import com.example.Empty.model.dto.auth.AuthRequest;
import com.example.Empty.model.dto.auth.AuthResponse;
import com.example.Empty.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "API for Authentication")
@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthRestController {
    private final AuthService authService;

    @Operation(summary = "Login with User Credentials")
    @PostMapping("login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
