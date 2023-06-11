package blog.service.impl;

import blog.Model.Tag;
import blog.repository.TagRepository;
import blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TagServiceImpl implements ITagService {
    private final TagRepository tagRepository;
    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    @Override
    public List<Tag> findAll() {
        return this.tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findById(Long aLong) {
        return this.tagRepository.findById(aLong);
    }

    @Override
    public Tag save(Tag tag) {
        Tag existedTag = this.tagRepository.findTagsByName(tag.getName());
        if(Objects.isNull(existedTag)) {
            return  this.tagRepository.save(tag);
        } else {
            return existedTag;
        }
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Tag> findTagsByBlogId(long blogId) {
        return this.tagRepository.findTagsByBlogs_Id(blogId);
    }
}
