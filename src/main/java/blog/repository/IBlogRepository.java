package blog.repository;

import blog.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBlogRepository extends JpaRepository <Blog,Long> {
    List<Blog> findAllByDeleteStatusIsFalseOrderByIdDesc();

    List<Blog> findAllByDeleteStatusIsFalseAndUserIdOrderByIdDesc(Long user_id);

    List<Blog> findAllByTitleContainingAndDeleteStatusIsFalse(String keyword);

    @Query("SELECT b FROM blog b JOIN b.tagBlog t WHERE t.id = :tagId and b.deleteStatus = false order by b.id")
    List<Blog> findAllByTagId(@Param("tagId") long tagId);

    @Query(value = "insert into la31_blog_project.blog_tag (blog_id,tag_id) values (?1,?2)", nativeQuery = true)
    @Transactional
    @Modifying
    int setTagsBlog(Long blogId,Long tagId);

    @Modifying
    @Transactional
    @Query(value = "delete from la31_blog_project.blog_tag where blog_tag.blog_id = ?1",nativeQuery = true)
    void resetTagsBlog(Long blogId);

}
