package com.site.blog_api.service;

import com.site.blog_api.dto.CategoryDTO;
import com.site.blog_api.model.Category;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;


public class CategoryServiceTest {


    private CategoryService categoryService = new CategoryService();
    private List<Category> categoryList;

    @Test
    public void whenFindCategoryWithListEmpty() {
        List<Category> categories = categoryService.findCategory();
        assertEquals(0, categories.size());
    }

    @Test
    public void whenCreateCategory() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Java")
                .description("Programação")
                .build();

        Category response = categoryService.createCategory(categoryDTO);

        assertEquals(categoryDTO.getName(), response.getName());
        assertEquals(categoryDTO.getDescription(), response.getDescription());

    }

    @Test
    public void whenCreateCategoryWithNullDTO() {
        assertThrows(NullPointerException.class, () -> categoryService.createCategory(null));
    }

    @Test
    public void whenUpdateCategory_ExistingCategory_ReturnsUpdatedCategory() {
        // Cenário feliz
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Nova Categoria");
        categoryDTO.setDescription("Descrição da Nova Categoria");
        Category category = new Category(1, "Categoria Antiga", "Descrição da Categoria Antiga");
        categoryList.add(category);
        Category updatedCategory = categoryService.updateCategory(1, categoryDTO);
        assertEquals("Nova Categoria", updatedCategory.getName());
        assertEquals("Descrição da Nova Categoria", updatedCategory.getDescription());

        // Categoria não encontrada
        try {
            categoryService.updateCategory(2, categoryDTO);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Categoria com id: 2 não existe!", e.getMessage());
        }

        // Categoria nula
        assertNull(categoryService.updateCategory(1, null));

        // Atualização parcial
        categoryDTO = new CategoryDTO();
        categoryDTO.setName("Nova Categoria 2");
        updatedCategory = categoryService.updateCategory(1, categoryDTO);
        assertEquals("Nova Categoria 2", updatedCategory.getName());
        assertEquals("Descrição da Nova Categoria", updatedCategory.getDescription());

        // ID inválido no DTO
        categoryDTO = new CategoryDTO();
        categoryDTO.setId(2);
        categoryDTO.setName("Nova Categoria 3");
        updatedCategory = categoryService.updateCategory(1, categoryDTO);
        assertEquals("Nova Categoria 3", updatedCategory.getName());
        assertEquals("Descrição da Nova Categoria", updatedCategory.getDescription());

        // Lista de categorias vazia
        categoryList.clear();
        try {
            categoryService.updateCategory(1, categoryDTO);
            fail("Expected RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("Categoria com id: 1 não existe!", e.getMessage());
        }
    }
}
