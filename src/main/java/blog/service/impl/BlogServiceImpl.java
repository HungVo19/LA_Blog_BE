package blog.service.impl;

import blog.Model.Blog;
import blog.Model.Tag;
import blog.repository.IBlogRepository;
import blog.repository.TagRepository;
import blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BlogServiceImpl implements IBlogService {
    private final IBlogRepository blogRepository;
    private final TagServiceImpl tagService;


    @Autowired
    public BlogServiceImpl(IBlogRepository blogRepository, TagServiceImpl tagService) {
        this.blogRepository = blogRepository;
        this.tagService = tagService;
    }

    @Override
    public List<Blog> findAllByDeleteStatusIsFalseAndUserId(Long user_id) {
        return this.blogRepository.findAllByDeleteStatusIsFalseAndUserIdOrderByIdDesc(user_id);
    }

    @Override
    public List<Blog> findAll() {
        return this.blogRepository.findAllByDeleteStatusIsFalseOrderByIdDesc();
    }

    @Override
    public Optional<Blog> findById(Long aLong) {
        return this.blogRepository.findById(aLong);
    }

    @Override
    public Blog save(Blog blog) {
        String text = blog.getContent();
        String patternString = "<img src=\"(.*?)\"";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String img = matcher.group(1);
            blog.setImage(img);
        }

        Blog latest = this.blogRepository.save(blog);
        Long latestId = latest.getId();

        this.blogRepository.resetTagsBlog(latestId);

        Pattern patternTag = Pattern.compile("#[a-z0-9]+");
        Matcher matcherTag = patternTag.matcher(latest.getContent());
        Set<String> tags = new HashSet<>();
        while (matcherTag.find()) {
            tags.add(matcherTag.group());
        }

        for (String t : tags) {
            Tag tag = new Tag(t);
            Tag newTag = this.tagService.save(tag);
            Long tagId = newTag.getId();
            this.blogRepository.setTagsBlog(latestId, tagId);
        }
        return this.blogRepository.save(blog);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void setDeleteStatusToFalse(Long blogID) {
        Blog blog = this.blogRepository.findById(blogID).orElse(null);
        if (blog != null) {
            blog.setDeleteStatus(true);
            this.blogRepository.save(blog);
        }
    }

    @Override
    public List<Blog> findAllByTitleContaining(String keyword) {
        return this.blogRepository.findAllByTitleContainingAndDeleteStatusIsFalse(keyword);
    }

    @Override
    public List<Blog> findAllBlogsByTag(Long tagId) {
        return this.blogRepository.findAllByTagId(tagId);
    }

    @Override
    public int setTagsBlog(Long blogId, Long tagId) {
        return this.blogRepository.setTagsBlog(blogId, tagId);
    }

    @Override
    public void resetTagsBlog(Long blogId) {
        this.blogRepository.resetTagsBlog(blogId);
    }
}
