package blog.service;

import blog.Model.Tag;

import java.util.List;

public interface ITagService extends ICoreService<Tag,Long>{
    List<Tag> findTagsByBlogId(long blogId);
}
