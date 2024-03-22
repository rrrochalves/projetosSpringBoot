package com.site.blog_api.factory;

import com.site.blog_api.dto.CategoryDTO;
import com.site.blog_api.model.Category;

public class CategoryFactory {

    private static int id = 1;

    public static Category createCategory(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(id++)
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build();
    }
}
