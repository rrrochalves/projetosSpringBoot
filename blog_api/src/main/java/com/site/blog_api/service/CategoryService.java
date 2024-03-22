package com.site.blog_api.service;

import com.site.blog_api.dto.CategoryDTO;
import com.site.blog_api.factory.CategoryFactory;
import com.site.blog_api.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private List<Category> categoryList = new ArrayList<>();

    public List<Category> findCategory() {
        return categoryList;
    }

    public Category createCategory(CategoryDTO categoryDTO) {

        Category category = CategoryFactory.createCategory(categoryDTO);
        categoryList.add(category);

        return category;
    }

    public Category updateCategory(int id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryList.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElseThrow(()
                        -> new RuntimeException("Categoria com id: " + id + " não existe!")
                ));

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            return category;
        }

        return null;
    }

    public void deleteCategory(int id) {
        boolean removed = categoryList.removeIf(category -> category.getId() == id);

        if (!removed) throw new RuntimeException("A categoria com id: "+ id + " não foi removida!");
    }
}
