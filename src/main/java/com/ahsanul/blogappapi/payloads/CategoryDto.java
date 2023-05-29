package com.ahsanul.blogappapi.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotEmpty
    @Size(min = 4 , message = "Title must be a word.")
    private String categoryTitle;


    @NotEmpty
    @Size(min = 4 , message = "Category Description must be a sentence.")
    private String categoryDescription;
}
