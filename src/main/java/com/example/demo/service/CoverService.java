package com.example.demo.service;

import com.example.demo.model.Cover;
import com.example.demo.repo.ICoverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CoverService implements ICoverService{

    @Autowired
    private ICoverRepo coverRepo;

    @Override
    public Iterable<Cover> findAll() {
        return coverRepo.findAll();
    }

    @Override
    public Optional<Cover> findById(Long id) {
        return coverRepo.findById(id);
    }

    @Override
    public Cover save(Cover cover) {
        return coverRepo.save(cover);
    }

    @Override
    public void remove(Long id) {
        coverRepo.deleteById(id);
    }

    @Override
    public void removeCover(Long id) {
        coverRepo.removeCover(id);
    }
}
