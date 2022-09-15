//package com.example.demo.controller;
//
//import com.example.demo.model.Blog;
//import com.example.demo.model.BlogForm;
//import com.example.demo.model.Category;
//import com.example.demo.model.Cover;
//import com.example.demo.service.ICoverService;
//import com.example.demo.service.blog.IBlogService;
//import com.example.demo.service.category.ICategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//import java.io.File;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Controller
//@Slf4j
//@RequestMapping("/blogs")
//public class BlogController {
//
//    @Autowired
//    private IBlogService blogService;
//
//    @Autowired
//    private ICategoryService categoryService;
//
//    @Autowired
//    private ICoverService coverService;
//
//    @Autowired
//    Environment env;
//
//    @ModelAttribute("categories")
//    private Iterable<Category> categories(){
//        return categoryService.findAll();
//    }
//
//    @GetMapping("/cate")
//    public  String showAllCate(Model model){
//        model.addAttribute("cate", categoryService.findAll());
//        return "cate";
//    }
//
//    @GetMapping("/create")
//    public ModelAndView showCreateForm() {
//        ModelAndView modelAndView = new ModelAndView("/create");
//        modelAndView.addObject("blog", new BlogForm());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public ModelAndView saveBlog(@Validated @ModelAttribute("blog") BlogForm blogForm, BindingResult bindingResult) {
//        if (!bindingResult.hasErrors()){
//            Blog blog = new Blog(blogForm.getTitle(), blogForm.getContent(), blogForm.getCategory());
//            Blog blog1 = blogService.save(blog);
//            for (MultipartFile file : blogForm.getFiles()) {
//                String fileName =file.getOriginalFilename();
//                String fileUpload = env.getProperty("upload.path");
//                try {
//                    FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
//                    Cover cover = new Cover(fileName, blog1);
//                    coverService.save(cover);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return new ModelAndView("create", "mess", "New blog was created");
//        }
//        return new ModelAndView("create");
//    }
//
//    @PostMapping("/edit")
//    public ModelAndView updateBlog(@ModelAttribute("blog") BlogForm blogForm, BindingResult bindingResult) {
//        if (!bindingResult.hasErrors()){
//            Blog blog = blogService.findById(blogForm.getId()).get();
//            coverService.removeCover(blog.getId());
//            blog.setCategory(blogForm.getCategory());
//            blog.setContent(blogForm.getContent());
//            blog.setTitle(blogForm.getTitle());
//            for (MultipartFile file: blogForm.getFiles()){
//                String fileName =file.getOriginalFilename();
//                Cover cover = new Cover(fileName, blog);
//                coverService.save(cover);
//                String fileUpload = env.getProperty("upload.path");
//                try {
//                    FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            return new ModelAndView("list");
//        }
//        return new ModelAndView("edit");
//    }
//
//    @GetMapping()
//    public String blogs(@RequestParam("id") Long id, Model model){
//        model.addAttribute("blogs", blogService.findByIdCategory(id));
//        return "result";
//    }
//
//    @GetMapping("/list")
//    public String listBlogs(Model model) {
//        model.addAttribute("blogs", blogService.findAll());
//        return "list";
//    }
//
//    @GetMapping("/search/{id}")
//    public String listBlogByCate( @PathVariable Long id, Model model){
//        model.addAttribute("blogs", blogService.findByIdCategory(id) );
//        return "result";
//    }
//
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Long id, Model model){
//        Blog blog = blogService.findById(id).get();
//        model.addAttribute("blog", blog);
//        return "delete";
//    }
//
//    @PostMapping("/delete")
//    public String deleteBlog(@ModelAttribute("blog") Blog blog) {
//        coverService.removeCover(blog.getId());
//        blogService.remove(blog.getId());
//        return "redirect:list";
//    }
//
//    @GetMapping("/edit/{id}")
//    public ModelAndView showEditForm(@PathVariable Long id) {
//        Optional<Blog> blog1 = blogService.findById(id);
//        if (blog1.isPresent()) {
//            BlogForm blog = new BlogForm(blog1.get());
//            ModelAndView modelAndView = new ModelAndView("/edit");
//            modelAndView.addObject("blog", blog);
//            return modelAndView;
//        } else {
//            return new ModelAndView("/error-404");
//        }
//    }
//}
