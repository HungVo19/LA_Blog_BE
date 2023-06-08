package blog.service;

import blog.Model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICoreService <E,K>{
    List<E> findAll();
    Optional<E> findById(K k);
    E save(E e);
    void delete(K k);
}
