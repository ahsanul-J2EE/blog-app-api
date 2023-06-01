package com.ahsanul.blogappapi.repositories;

import com.ahsanul.blogappapi.entites.Category;
import com.ahsanul.blogappapi.entites.Post;
import com.ahsanul.blogappapi.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
