package com.example.Empty.model.dto;

import java.time.LocalDateTime;

public record CreatePostRequestRecord(String title, String content, String slug, Boolean published, LocalDateTime publishedAt, String intro) {
}
