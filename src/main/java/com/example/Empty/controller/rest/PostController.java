package com.example.Empty.controller.rest;

import com.example.Empty.model.domain.Post;
import com.example.Empty.model.dto.CreatePostRequestRecord;
import com.example.Empty.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Post Resource", description = "API for managing posts")
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Operation(summary = "Get all posts")
    @GetMapping
    public List<Post> getPost() {
        return postService.getAllPost();
    }

    @Operation(summary = "Creat posts")
    @PostMapping("/api/posts")
    public Post create(@RequestBody CreatePostRequestRecord createPostRequestRecord) {
        return postService.createPost(createPostRequestRecord);
    }


}
