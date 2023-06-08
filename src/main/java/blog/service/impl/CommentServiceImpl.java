package blog.service.impl;

import blog.Model.Comment;
import blog.repository.ICommentRepository;
import blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {


    private final ICommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(ICommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
    @Override
    public List<Comment> findAllByBlogId(Long id) {
        return commentRepository.findAllByBlogId(id);
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Optional<Comment> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public void delete(Long aLong) {

    }
}
