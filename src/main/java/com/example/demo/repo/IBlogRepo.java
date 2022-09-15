package com.example.demo.repo;

import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepo extends JpaRepository<Blog, Long> {

    @Query(nativeQuery = true, value = "select * from blog where category_id = ?;")
    Iterable<Blog> getBlogByCate(Long id);

}
