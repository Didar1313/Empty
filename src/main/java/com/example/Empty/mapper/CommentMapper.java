package com.example.Empty.mapper;

import com.example.Empty.model.domain.Comment;
import com.example.Empty.persistence.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentEntity domainToEntity(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setName(comment.getName());
        entity.setText(comment.getText());
        return entity;
    }

    public Comment entityToDomain(CommentEntity entity) {
        Comment comment = new Comment();
        comment.setName(entity.getName());
        comment.setText(entity.getText());
        return comment;
    }
}
