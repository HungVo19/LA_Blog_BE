package blog.service;

import blog.Model.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService extends ICoreService<Blog, Long> {
    List<Blog> findAllByDeleteStatusIsFalseAndUserId(Long user_id);

    void setDeleteStatusToFalse(Long blogID);

    List<Blog> findAllByTitleContaining(String keyword);
    List<Blog> findAllBlogsByTag(Long tagId);

    int setTagsBlog(Long blogId, Long tagId);

    void resetTagsBlog(Long blogId);

}
