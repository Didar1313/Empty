package com.example.Empty.controller.web;

import com.example.Empty.model.domain.Comment;
import com.example.Empty.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentContrller {

    @Autowired
    private PostService postService;

    @PostMapping("/blog/{id}/comment")
    public String submitComment(@PathVariable Long id,
                                @ModelAttribute Comment commentForm) {
        postService.saveComment(id, commentForm);
        return "redirect:/blog/detail/" + id;
    }
}
