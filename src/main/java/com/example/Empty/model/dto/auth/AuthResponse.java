package com.example.Empty.model.dto.auth;

public record AuthResponse(String token, String username, Long expiresAt) {
}
