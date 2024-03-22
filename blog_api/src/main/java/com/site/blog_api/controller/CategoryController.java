package com.site.blog_api.controller;


import com.site.blog_api.dto.CategoryDTO;
import com.site.blog_api.model.Category;
import com.site.blog_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(service.findCategory());
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(service.createCategory(categoryDTO));
    }

    @PutMapping
    public ResponseEntity<Category> update(int id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(service.updateCategory(id, categoryDTO));
    }

    @DeleteMapping
    public ResponseEntity delete(int id) {
        service.deleteCategory(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
