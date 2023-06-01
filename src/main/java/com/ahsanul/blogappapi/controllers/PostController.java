package com.ahsanul.blogappapi.controllers;
import com.ahsanul.blogappapi.config.AppConfig;
import com.ahsanul.blogappapi.payloads.PostDto;
import com.ahsanul.blogappapi.payloads.PostResponse;
import com.ahsanul.blogappapi.services.FileService;
import com.ahsanul.blogappapi.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDto createPost = this.postService.createPost(postDto, userId, categoryId );


        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    //get all post

    @GetMapping("/all-posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = AppConfig.pageNumber,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConfig.pageSize,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConfig.sortBy,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConfig.sortDir,required = false) String sortDir){

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

    @Value("${project.image}")
    private String path;

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,@PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,image);

        postDto.setImageName(fileName);
        PostDto updatedPost =  this.postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
    }


    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_GIF_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName , HttpServletResponse response)throws IOException{

        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
