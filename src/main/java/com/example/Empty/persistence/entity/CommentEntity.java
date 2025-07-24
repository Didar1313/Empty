package com.example.Empty.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
