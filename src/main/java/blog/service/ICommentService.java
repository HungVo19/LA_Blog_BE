package blog.service;

import blog.Model.Comment;

import java.util.List;

public interface ICommentService extends ICoreService<Comment,Long> {
    List<Comment> findAllByBlogId(Long id);

}
