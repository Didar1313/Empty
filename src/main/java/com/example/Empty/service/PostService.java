package com.example.Empty.service;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.mapper.CommentMapper;
import com.example.Empty.mapper.PostMapper;
import com.example.Empty.model.domain.Comment;
import com.example.Empty.model.domain.Post;
import com.example.Empty.model.dto.CreatePostRequestRecord;
import com.example.Empty.model.dto.UpdatePostRequestRecord;
import com.example.Empty.persistence.entity.CommentEntity;
import com.example.Empty.persistence.entity.PostEntity;
import com.example.Empty.persistence.repository.CommentRepository;
import com.example.Empty.persistence.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;



    public List<Post> getAllPost(Pageable pageable){
        List<PostEntity>getPostData = postRepository.findAll(pageable).getContent();
        return getPostData.stream().map(postMapper::entityToDomain).toList();
    }

    public void createPost(CreatePostRequestRecord  createPostRequestRecord){
        PostEntity postEntity = postMapper.domainToEntity(createPostRequestRecord);
        postEntity.setIntro(getIntroForContent(postEntity.getContent()));
        if (createPostRequestRecord.published()) {
            postEntity.setPublishedAt(LocalDateTime.now());
        }
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
    private String getIntroForContent(String content){
        if(content == null || content.isBlank()){
           return "";
        }
        int limit = 300;
        String suffix="...";
        return content.length()<=limit?content:content.substring(0,limit)+suffix;
    }

    public void saveComment(Long postId, Comment commentForm) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        CommentEntity commentEntity = commentMapper.domainToEntity(commentForm);
        commentEntity.setPost(postEntity);
        commentRepository.save(commentEntity);
    }
    public List<Comment> getCommentList(Long postId) {
        List<CommentEntity> commentEntities = commentRepository.findAllByPostId(postId);
        return commentEntities.stream()
                .map(commentMapper::entityToDomain)
                .toList();
    }

}
