package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;


public class BlogForm {

    private Long id;

    @NotBlank(message = "không để trống")
    @Size(min = 2, message = "hãy nhập nhiều hơn 1 ký tự")
    private String title;

    private Set<Cover> coverForms;

    private String content;

    private Category category;

    private List<MultipartFile> files;

    public BlogForm() {
    }

    public BlogForm(Blog blog) {
        this.title = blog.getTitle();
        this.category = blog.getCategory();
        this.content = blog.getContent();
        this.files = blog.getFiles();
        this.coverForms = blog.getCovers();
        this.id = blog.getId();
    }

    public BlogForm(Long id, String title, Set<Cover> coverForms, String content, Category category, List<MultipartFile> files) {
        this.id = id;
        this.title = title;
        this.coverForms = coverForms;
        this.content = content;
        this.category = category;
        this.files = files;
    }

    public BlogForm(String title, Set<Cover> coverForms, String content, Category category, List<MultipartFile> files) {
        this.title = title;
        this.coverForms = coverForms;
        this.content = content;
        this.category = category;
        this.files = files;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Cover> getCoverForms() {
        return coverForms;
    }

    public void setCoverForms(Set<Cover> coverForms) {
        this.coverForms = coverForms;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
