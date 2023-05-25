package com.ahsanul.blogappapi.services;

import com.ahsanul.blogappapi.payloads.CategoryDto;
import com.ahsanul.blogappapi.payloads.UserDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto category, Integer id);
    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategory();

    void categoryDelete (Integer categoryId);
}
