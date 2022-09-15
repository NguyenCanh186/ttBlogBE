package com.example.demo.model;


import org.springframework.web.multipart.MultipartFile;

public class CoverForm {
    private Long id;

    private MultipartFile name;

    private Blog blog;

    public CoverForm() {
    }

    public CoverForm(CoverFormBuilder coverFormBuilder) {
        this.name = coverFormBuilder.name;
        this.blog = coverFormBuilder.blog;
    }

    public CoverForm(MultipartFile file, Blog blog) {
        this.name = file;
        this.blog = blog;
    }
    public static class CoverFormBuilder {
        private final MultipartFile name;

        private Blog blog;

        public CoverFormBuilder(MultipartFile file) {
            this.name = file;
        }

        public CoverFormBuilder blog(Blog blog) {
            this.blog = blog;
            return this;
        }

        public CoverForm build() {
            return new CoverForm(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getName() {
        return name;
    }

    public void setName(MultipartFile name) {
        this.name = name;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
