package blog.controller;

import blog.Model.Comment;
import blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class CommentController {
    private final ICommentService commentService;
    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{blogId}")
    public ResponseEntity<List<Comment>> getListByBlogId(@PathVariable Long blogId) {
        return new ResponseEntity<>(this.commentService.findAllByBlogId(blogId), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> postNewComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(this.commentService.save(comment),HttpStatus.CREATED);
    }

}
