package com.ahsanul.blogappapi.repositories;

import com.ahsanul.blogappapi.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {


}
