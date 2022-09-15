package com.example.demo.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "blog")
    @JsonIgnore
    private Set<Cover> covers;

    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    private List<MultipartFile> files;

    public Blog() {
    }

    public Blog(String title, Set<Cover> covers, String content, Category category, List<MultipartFile> files) {
        this.title = title;
        this.covers = covers;
        this.content = content;
        this.category = category;
        this.files = files;
    }

    public Blog(Long id, String title, Set<Cover> covers, String content, Category category, List<MultipartFile> files) {
        this.id = id;
        this.title = title;
        this.covers = covers;
        this.content = content;
        this.category = category;
        this.files = files;
    }

    public Blog(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
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

    public Set<Cover> getCovers() {
        return covers;
    }

    public void setCovers(Set<Cover> covers) {
        this.covers = covers;
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

    public Blog(String title, Set<Cover> covers, String content, Category category) {
        this.title = title;
        this.covers = covers;
        this.content = content;
        this.category = category;
    }

    public Blog(String title, List<MultipartFile> files, String content, Category category) {
        this.title = title;
        this.files = files;
        this.content = content;
        this.category = category;
    }
}
