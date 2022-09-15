package com.example.demo.service;

import com.example.demo.model.Cover;

public interface ICoverService extends IGeneralService<Cover> {

    void removeCover(Long id);
}
