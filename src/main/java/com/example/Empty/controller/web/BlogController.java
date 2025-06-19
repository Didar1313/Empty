package com.example.Empty.controller.web;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/blog")
@Controller
public class BlogController {
    private final Parser markdownParser;
    private final HtmlRenderer htmlRenderer;
    private final List<Post> dummyPosts;
    private final List<Comment> dummyComments;

    public BlogController() {
        this.markdownParser = Parser.builder().build();;
        this.htmlRenderer = HtmlRenderer.builder().build();
        this.dummyPosts = initializeDummyPosts();
        this.dummyComments = initializeDummyComments();
    }

    private List<Post> initializeDummyPosts() {
        List<Post> posts = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");

        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("Exploring Java Concurrency with Thread Pools");
        post1.setAuthor("Author");
        post1.setCreatedAt(new Date()); // Today: May 17, 2025
        post1.setCreatedAtFormatted(dateFormat.format(post1.getCreatedAt()));
        post1.setContent("""
            # Why Java Concurrency Matters
            Java’s concurrency utilities, introduced in **Java 5**, provide robust tools for managing multi-threaded applications. Thread pools, in particular, are essential for optimizing resource usage and improving performance in applications with multiple tasks.

            ## Key Benefits of Thread Pools:
            - **Resource Management**: Reuses threads to reduce overhead.
            - **Scalability**: Handles large numbers of tasks efficiently.
            - **Simplified Control**: Manages thread lifecycle automatically.

            Below is an example of using a **ThreadPoolExecutor** to process tasks concurrently. This code demonstrates creating a thread pool and submitting tasks to it. [Learn more in the official Java documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ThreadPoolExecutor.html).

            ```java
            import java.util.concurrent.*;

            public class ThreadPoolExample {
                public static void main(String[] args) {
                    // Create a thread pool with 2-4 threads
                    ThreadPoolExecutor executor = new ThreadPoolExecutor(
                        2, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
                    );
                    // Submit 5 tasks to the thread pool
                    for (int i = 1; i <= 5; i++) {
                        final int taskId = i;
                        executor.submit(() -> {
                            System.out.println("Task " + taskId + " is running on " + Thread.currentThread().getName());
                            try {
                                Thread.sleep(1000); // Simulate work
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Task " + taskId + " completed");
                        });
                    }
                    // Shutdown the executor
                    executor.shutdown();
                    try {
                        executor.awaitTermination(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            ```

            ## How It Works:
            1. **ThreadPoolExecutor Creation**: Configures a pool with a core size of 2 and a maximum of 4 threads.
            2. **Task Submission**: Submits 5 tasks, which are executed by available threads.
            3. **Shutdown**: Gracefully stops the executor after tasks are complete.

            This is a basic example. For production, consider tuning parameters like queue size and rejection policies based on your application’s needs.
            """);

        Post post2 = new Post();
        post2.setId(2L);
        post2.setTitle("Understanding Spring Boot Autoconfiguration");
        post2.setAuthor("Author");
        post2.setCreatedAt(new Date());
        post2.setCreatedAtFormatted(dateFormat.format(post2.getCreatedAt()));
        post2.setContent("""
        # Spring Boot Autoconfiguration Demystified

        Spring Boot’s autoconfiguration mechanism simplifies setup by guessing what you want and wiring it for you. It uses classpath inspection and default settings to reduce boilerplate.

        ## Key Features:
        - Automatically configures beans based on libraries in the classpath
        - Can be overridden with custom configuration
        - Helps reduce `applicationContext.xml` or manual `@Bean` definitions

        ## Example:
        Just include `spring-boot-starter-web` in your `pom.xml` or `build.gradle` and Spring Boot sets up:
        - Embedded Tomcat
        - DispatcherServlet
        - Jackson for JSON
        - And more...

        ```java
        @RestController
        public class HelloController {
            @GetMapping("/")
            public String hello() {
                return "Hello, Spring Boot!";
            }
        }
        ```

        ## Why It Matters:
        Spring Boot helps you focus on logic, not configuration. But knowing how autoconfiguration works helps in debugging and fine-tuning.

        🔍 Want to explore? Use `@EnableAutoConfiguration` and `spring-boot-actuator` to inspect what's auto-configured.
        """);

        posts.add(post1);
        posts.add(post2);
        return posts;
    }

    private List<Comment> initializeDummyComments() {
        List<Comment> comments = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");

        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setPostId(1L);
        comment1.setAuthor("Jane Doe");
        comment1.setContent("Great explanation of thread pools! The example was super clear.");
        comment1.setCreatedAt(new Date());
        comment1.setCreatedAtFormatted(dateFormat.format(comment1.getCreatedAt()));

        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setPostId(1L);
        comment2.setAuthor("John Smith");
        comment2.setContent("Thanks for the Java concurrency insights!");
        comment2.setCreatedAt(new Date());
        comment2.setCreatedAtFormatted(dateFormat.format(comment2.getCreatedAt()));

        //  New comments for post ID 2
        Comment comment3 = new Comment();
        comment3.setId(3L);
        comment3.setPostId(2L);
        comment3.setAuthor("Alex Lee");
        comment3.setContent("Really helpful breakdown of Spring Boot auto-config!");
        comment3.setCreatedAt(new Date());
        comment3.setCreatedAtFormatted(dateFormat.format(comment3.getCreatedAt()));

        Comment comment4 = new Comment();
        comment4.setId(4L);
        comment4.setPostId(2L);
        comment4.setAuthor("Emily Chen");
        comment4.setContent("The sample code made it very easy to understand. Thanks!");
        comment4.setCreatedAt(new Date());
        comment4.setCreatedAtFormatted(dateFormat.format(comment4.getCreatedAt()));

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);
        comments.add(comment4);
        return comments;
    }



    @GetMapping
    public String indexPage() {
        return "blog/index";
    }

    @GetMapping("{id}")
    public String detailPage(@PathVariable Long id, Model model) {
        // Find post by ID
        Optional<Post> postOptional = dummyPosts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        if (postOptional.isEmpty()) {
            return "redirect:/";
        }

        Post post = postOptional.get();

        // Find comments for the post
        List<Comment> comments = dummyComments.stream()
                .filter(comment -> comment.getPostId().equals(id))
                .toList();

        // Convert Markdown content to HTML
        Node document = markdownParser.parse(post.getContent());
        String markdownContent = htmlRenderer.render(document);

        // Add attributes to the model
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("markdownContent", markdownContent);

        return "blog/detail";
    }

    // Post class
    public static class Post {
        private Long id;
        private String title;
        private String content;
        private String author;
        private Date createdAt;
        private String createdAtFormatted;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public Date getCreatedAt() { return createdAt; }
        public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
        public String getCreatedAtFormatted() { return createdAtFormatted; }
        public void setCreatedAtFormatted(String createdAtFormatted) { this.createdAtFormatted = createdAtFormatted; }
    }

    // Comment class
    public static class Comment {
        private Long id;
        private Long postId;
        private String author;
        private String content;
        private Date createdAt;
        private String createdAtFormatted;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public Long getPostId() { return postId; }
        public void setPostId(Long postId) { this.postId = postId; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public Date getCreatedAt() { return createdAt; }
        public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
        public String getCreatedAtFormatted() { return createdAtFormatted; }
        public void setCreatedAtFormatted(String createdAtFormatted) { this.createdAtFormatted = createdAtFormatted; }
    }
}
