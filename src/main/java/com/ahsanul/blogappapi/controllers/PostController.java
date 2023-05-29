package com.ahsanul.blogappapi.controllers;



import com.ahsanul.blogappapi.entites.Post;
import com.ahsanul.blogappapi.payloads.PostDto;
import com.ahsanul.blogappapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId );


        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);




    }
}
