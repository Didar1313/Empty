package com.example.Empty.model.dto;

import java.time.LocalDateTime;

public  record CreatePostRequestRecord(
        Long id,
        String title,
        String content,
        String slug,
        Boolean published,
        LocalDateTime publishedAt
) {
}
