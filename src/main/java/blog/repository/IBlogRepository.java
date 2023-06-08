package blog.repository;

import blog.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBlogRepository extends JpaRepository <Blog,Long> {
    List<Blog> findAllByDeleteStatusIsFalseOrderByIdDesc();

    List<Blog> findAllByDeleteStatusIsFalseAndUserId(Long user_id);

    List<Blog> findAllByTitleContaining(String keyword);

}
