package com.ahsanul.blogappapi.repositories;

import com.ahsanul.blogappapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
