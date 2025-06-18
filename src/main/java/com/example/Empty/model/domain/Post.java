package com.example.Empty.model.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    @Setter
    private String title;

    @Setter
    private String content;

    @Setter
    private String slug;

    @Setter
    private Boolean published;

    @Setter
    private LocalDateTime publishedAt;

}
