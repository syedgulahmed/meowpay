package com.meowpay.meowpay.controller;

import com.meowpay.meowpay.model.Cat;
import com.meowpay.meowpay.repository.CatRepository;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatController {

    private final CatRepository catRepository;

    public CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @GetMapping("/cats")
    public List<Cat> getCats() {
        return catRepository.findAll(Sort.by("createdAt"));
    }
}
