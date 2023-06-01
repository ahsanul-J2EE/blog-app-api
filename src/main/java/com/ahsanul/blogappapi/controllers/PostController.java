package com.ahsanul.blogappapi.controllers;
import com.ahsanul.blogappapi.payloads.PostDto;
import com.ahsanul.blogappapi.payloads.PostResponse;
import com.ahsanul.blogappapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    //get all post

    @GetMapping("/all-posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir){

        PostResponse posts = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);

    }


    //get all post by id

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){

        PostDto posts = this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(posts, HttpStatus.OK);

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

    //search post by title

    @GetMapping("search/posts/{title}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String title){

        List<PostDto> posts = this.postService.searchPosts(title);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);

    }
}
