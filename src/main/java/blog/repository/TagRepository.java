package blog.repository;

import blog.Model.Blog;
import blog.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query("select t from tag t join blog b where b.id = :id")
    List<Tag> findTagsByBlogId(@Param("id") Long id);

    List<Tag>findTagsByBlogs_Id(@Param("id")Long id);
    Tag findTagsByName(String name);

}
