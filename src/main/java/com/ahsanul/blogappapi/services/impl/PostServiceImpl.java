package com.ahsanul.blogappapi.services.impl;

import com.ahsanul.blogappapi.entites.Category;
import com.ahsanul.blogappapi.entites.Post;
import com.ahsanul.blogappapi.entites.User;
import com.ahsanul.blogappapi.exceptions.ResourceNotFoundException;
import com.ahsanul.blogappapi.payloads.PostDto;
import com.ahsanul.blogappapi.repositories.CategoryRepo;
import com.ahsanul.blogappapi.repositories.PostRepo;
import com.ahsanul.blogappapi.repositories.UserRepo;
import com.ahsanul.blogappapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));


        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);

        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostDto getCategoryById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        List<Post> posts = this.postRepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}