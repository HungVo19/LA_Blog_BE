package blog.controller;

import blog.Model.Blog;
import blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping
public class BlogController {
    private final IBlogService blogService;

    @Autowired
    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> findAll() {
        return new ResponseEntity<>(this.blogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<Blog> findById(@PathVariable long id) {
        return new ResponseEntity<>(this.blogService.findById(id).get(), HttpStatus.OK);
    }

    @GetMapping("/blogs/users/{userId}")
    public ResponseEntity<List<Blog>> findAllBlogByUser(@PathVariable long userId) {
        return new ResponseEntity<>(this.blogService.findAllByDeleteStatusIsFalseAndUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/blogs")
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(this.blogService.save(blog), HttpStatus.CREATED);
    }

    @PutMapping("/blogs/delete/{id}")
    public ResponseEntity<Blog> delete(@PathVariable Long id) {
        Optional<Blog> blog = this.blogService.findById(id);
        if (blog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.blogService.setDeleteStatusToFalse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/blogs")
    public ResponseEntity<Blog> update(@RequestBody Blog blog) {
        return new ResponseEntity<>(this.blogService.save(blog),HttpStatus.OK);
    }

    @GetMapping("/blogs/search")
    public ResponseEntity<List<Blog>> search(@RequestParam(value = "q") String keyword) {
        return new ResponseEntity<>(this.blogService.findAllByTitleContaining(keyword),HttpStatus.OK);
    }
}
