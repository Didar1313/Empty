package com.example.Empty.service;

import com.example.Empty.mapper.PostMapper;
import com.example.Empty.model.domain.Post;
import com.example.Empty.model.dto.CreatePostRequestRecord;
import com.example.Empty.model.dto.UpdatePostRequestRecord;
import com.example.Empty.persistence.entity.PostEntity;
import com.example.Empty.persistence.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    public List<Post> getAllPost(){
        List<PostEntity>getPostData = postRepository.findAll();
        return getPostData.stream().map(entity -> {
            return postMapper.entityToDomain(entity);
        }).toList();
    }

    public Post createPost(CreatePostRequestRecord  createPostRequestRecord){
        PostEntity postEntity = postMapper.domainToEntity(createPostRequestRecord);
        PostEntity savedEntity = postRepository.save(postEntity);
       return postMapper.entityToDomain(savedEntity);
    }

    public void updatePost(Long id, UpdatePostRequestRecord updatePostRequestRecord) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
        postEntity.setContent(updatePostRequestRecord.content());
        postRepository.save(postEntity);
    }

    public void deletePost(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Post not found"));
        postRepository.delete(postEntity);
    }

}
