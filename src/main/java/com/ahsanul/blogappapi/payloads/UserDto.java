package com.ahsanul.blogappapi.payloads;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDto {

     private int id;

     @NotEmpty
     @Size(min = 4 , message = "Username must be min of 4 characters")
     private String name;

     @Email(message = "Email address is not valid !!")
     private String email;

     @NotEmpty
     @Size(min = 4 , max=10 , message = "Password must be min of 4 characters and mx od 10 characters")
     private String password;

     @NotEmpty
     private String about;
}
