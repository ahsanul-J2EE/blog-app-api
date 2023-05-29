package com.ahsanul.blogappapi.services.impl;

import com.ahsanul.blogappapi.exceptions.ResourceNotFoundException;
import com.ahsanul.blogappapi.entites.Category;
import com.ahsanul.blogappapi.payloads.CategoryDto;
import com.ahsanul.blogappapi.repositories.CategoryRepo;
import com.ahsanul.blogappapi.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepo.save(category);
        return this.categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category category = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));


        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(category);

        CategoryDto categoryDto1 =  this.categoryToDto(updatedCategory);

        return categoryDto1;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));

        return this.categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories =  this.categoryRepo.findAll();
        List<CategoryDto> categoryDto = categories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDto;
    }

    @Override
    public void categoryDelete(Integer categoryId) {

        Category user = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        this.categoryRepo.delete(user);

    }


    private Category dtoToCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto,Category.class);

        return category;
    }

    private CategoryDto categoryToDto(Category category){

        CategoryDto categoryDto = this.modelMapper.map(category,CategoryDto.class);

        return categoryDto;
    }
}
