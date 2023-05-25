package com.ahsanul.blogappapi.controllers;


import com.ahsanul.blogappapi.payloads.ApiResponse;
import com.ahsanul.blogappapi.payloads.CategoryDto;
import com.ahsanul.blogappapi.payloads.UserDto;
import com.ahsanul.blogappapi.services.CategoryService;
import com.ahsanul.blogappapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }


    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){

        CategoryDto updatedCategory =  this.categoryService.updateCategory(categoryDto,categoryId);
        return ResponseEntity.ok(updatedCategory);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){

        this.categoryService.categoryDelete(categoryId);

        return new ResponseEntity(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategory());
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategoryById( @PathVariable Integer categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }




}
