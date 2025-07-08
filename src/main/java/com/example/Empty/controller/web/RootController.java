package com.example.Empty.controller.web;

import com.example.Empty.model.domain.Post;
import com.example.Empty.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RequestMapping({"/"})
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


}
