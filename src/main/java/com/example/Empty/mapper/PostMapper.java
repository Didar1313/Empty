package com.example.Empty.mapper;

import com.example.Empty.model.domain.Post;
import com.example.Empty.model.dto.CreatePostRequestRecord;

import com.example.Empty.persistence.entity.PostEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post entityToDomain(PostEntity post){
        Post domain=new Post();
        BeanUtils.copyProperties(post,domain);
        return domain;
    }

    public PostEntity domainToEntity(CreatePostRequestRecord post){
        PostEntity entity=new PostEntity();
        BeanUtils.copyProperties(post,entity);
        return entity;
    }

//    public PostEntity updateDataToEntity(UpdatePostRequestRecord updatePostRequestRecord) {
//    }
}
