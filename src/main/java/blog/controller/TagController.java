package blog.controller;

import blog.Model.Tag;
import blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TagController {
    private final ITagService ITagService;
    @Autowired
    public TagController(ITagService ITagService) {
        this.ITagService = ITagService;
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        return new ResponseEntity<>(this.ITagService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Tag> findById(@PathVariable Long tagId) {
        return new ResponseEntity<>(this.ITagService.findById(tagId).get(),HttpStatus.OK);
    }

    @GetMapping("/tags/blog/{blogId}")
    public ResponseEntity<List<Tag>> findTagsByBlogId(@PathVariable Long blogId) {
        return new ResponseEntity<>(this.ITagService.findTagsByBlogId(blogId),HttpStatus.OK);
    }
}


