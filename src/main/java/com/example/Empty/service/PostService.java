package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.mapper.PostMapper;
import com.example.Empty.model.domain.Post;
import com.example.Empty.model.dto.CreatePostRequestRecord;
import com.example.Empty.model.dto.UpdatePostRequestRecord;
import com.example.Empty.persistence.entity.PostEntity;
import com.example.Empty.persistence.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;


    public List<Post> getAllPost(Pageable pageable){
        List<PostEntity>getPostData = postRepository.findAll(pageable).getContent();
        return getPostData.stream().map(postMapper::entityToDomain).toList();
    }

    public void createPost(CreatePostRequestRecord  createPostRequestRecord){
        PostEntity postEntity = postMapper.domainToEntity(createPostRequestRecord);
        postRepository.save(postEntity);
    }

    public void updatePost(Long id, UpdatePostRequestRecord updatePostRequestRecord) throws NotFoundException {
        PostEntity postEntity = this.findEntityById(id);
        postMapper.updateRequestToEntity(updatePostRequestRecord, postEntity);
        postRepository.save(postEntity);
    }

    public void deletePost(Long id) throws NotFoundException{
        this.findEntityById(id);
        postRepository.deleteById(id);
    }

    public Post getPostById(Long id){
        PostEntity postEntity = this.findEntityById(id);
        return postMapper.entityToDomain(postEntity);
    }

    private PostEntity findEntityById(Long id) throws NotFoundException {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found"));
    }


}
