package com.example.Empty.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    @Column(name = "content", columnDefinition = "CLOB")
    private String content;
    private String slug;
    @Column(name = "is_published")
    private Boolean published;
    private LocalDateTime publishedAt;
    @Column(length = 400)
    private String intro;
}