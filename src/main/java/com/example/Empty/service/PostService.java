package com.example.Empty.service;

import com.example.Empty.model.domain.Post;
import com.example.Empty.model.dto.CreatePostRequestRecord;
import com.example.Empty.persistence.entity.PersonEntity;
import com.example.Empty.persistence.entity.PostEntity;
import com.example.Empty.persistence.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Post createPost(CreatePostRequestRecord  createPostRequestRecord){
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(createPostRequestRecord.title());
        postEntity.setContent(createPostRequestRecord.content());
        postEntity.setSlug(createPostRequestRecord.slug());
        postEntity.setPublished(createPostRequestRecord.published());
        postEntity.setPublishedAt(createPostRequestRecord.publishedAt());

        PostEntity savedEntity = postRepository.save(postEntity);

        return new Post(savedEntity.getId(),savedEntity.getTitle(),savedEntity.getContent(),
                savedEntity.getSlug(),savedEntity.getPublished(),savedEntity.getPublishedAt());
    }
}
