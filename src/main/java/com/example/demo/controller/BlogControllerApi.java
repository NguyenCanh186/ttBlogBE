package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.BlogForm;
import com.example.demo.model.Category;
import com.example.demo.model.Cover;
import com.example.demo.service.ICoverService;
import com.example.demo.service.blog.IBlogService;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/blogs")
public class BlogControllerApi {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICoverService coverService;

    @Autowired
    Environment env;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAll(){
        return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cate")
    public  ResponseEntity<Iterable<Category>> showAllCate(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping ("/list")
    public ResponseEntity<Iterable<Blog>> listBlog(){
        return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
    }

    @ModelAttribute("categories")
    private Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @PostMapping("/create")
    private ResponseEntity<Blog> saveBlog(@ModelAttribute BlogForm blogForm, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            Blog blog = new Blog(blogForm.getTitle(), blogForm.getContent(), blogForm.getCategory());
            Blog blog1 = blogService.save(blog);
            for (MultipartFile file : blogForm.getFiles()) {
                String fileName =file.getOriginalFilename();
                String fileUpload = env.getProperty("upload.path");
                try {
                    FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
                    Cover cover = new Cover(fileName, blog1);
                    coverService.save(cover);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id){
        Optional<Blog> blogOptional = blogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            blogService.remove(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findOne(@PathVariable Long id){
        return new ResponseEntity<>(blogService.findById(id).get(),HttpStatus.OK);
    }
}
