package com.example.demo.service.blog;

import com.example.demo.model.Blog;
import com.example.demo.service.IGeneralService;

public interface IBlogService extends IGeneralService<Blog> {

    Iterable<Blog> findByIdCategory(Long id);
}
