package com.example.demo.repo;

import com.example.demo.model.Blog;
import com.example.demo.model.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ICoverRepo extends JpaRepository<Cover, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from cover where blog_id = ?;")
    void removeCover(Long id);
}
