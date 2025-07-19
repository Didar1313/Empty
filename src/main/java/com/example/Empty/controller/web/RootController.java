package com.example.Empty.controller.web;

import com.example.Empty.exception.custom.NotFoundException;
import com.example.Empty.model.domain.Comment;
import com.example.Empty.model.domain.Post;
import com.example.Empty.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping({"/", "/blog"})
@Controller
public class RootController {

    @Autowired
    PostService postService;

    @GetMapping
    public String home(Model model) {
        Pageable topTwo = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "publishedAt"));
        List<Post> posts = postService.getAllPost(topTwo);
        model.addAttribute("posts", posts);
        return "blog/index"; // This points to templates/index.html
    }

    @GetMapping("/blog/detail/{id}")
    public String post(@PathVariable Long id, Model model) throws NotFoundException {
        Post post = postService.getPostById(id);
        List<Comment> comments = postService.getCommentList(id);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", new Comment());

        return "/blog/detail";
    }



}
