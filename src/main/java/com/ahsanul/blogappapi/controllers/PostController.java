package com.ahsanul.blogappapi.controllers;



import com.ahsanul.blogappapi.entites.Post;
import com.ahsanul.blogappapi.payloads.PostDto;
import com.ahsanul.blogappapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId );


        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    //get by user

    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){

        List<PostDto> posts = this.postService.getAllPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }

    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){

        List<PostDto> posts = this.postService.getAllPostsByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }
}
