package com.meowpay.meowpay.controller;

import com.meowpay.meowpay.model.Cat;
import com.meowpay.meowpay.repository.CatRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatController {

    private final CatRepository catRepo;

    public CatController(CatRepository catRepo) {
        this.catRepo = catRepo;
    }

    @GetMapping("/cats")
    public List<Cat> getCats() {
        return catRepo.findAll();
    }
}
