package blog.service.impl;

import blog.Model.Blog;
import blog.repository.IBlogRepository;
import blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BlogServiceImpl implements IBlogService {
    private final IBlogRepository blogRepository;

    @Override
    public List<Blog> findAllByDeleteStatusIsFalseAndUserId(Long user_id) {
        return this.blogRepository.findAllByDeleteStatusIsFalseAndUserId(user_id);
    }

    @Autowired
    public BlogServiceImpl(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
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
        return this.blogRepository.save(blog);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void setDeleteStatusToFalse(Long blogID) {
        Blog blog = this.blogRepository.findById(blogID).orElse(null);
        if(blog != null) {
            blog.setDeleteStatus(true);
            this.blogRepository.save(blog);
        }
    }
}
