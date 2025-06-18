package com.example.Empty.service;

import com.example.Empty.model.domain.Post;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.entity.PostEntity;
import com.example.Empty.persistence.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPost(){
        List<PostEntity>getPostData = postRepository.findAll();
        return getPostData.stream().map(postEntity -> {
            Post post= new Post(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(),postEntity.getSlug(),
                    postEntity.getPublished(),postEntity.getPublishedAt());
            return post;
        }).toList();
    }
}
