package com.ahsanul.blogappapi.services;

import com.ahsanul.blogappapi.entites.Post;
import com.ahsanul.blogappapi.payloads.CategoryDto;
import com.ahsanul.blogappapi.payloads.PostDto;
import com.ahsanul.blogappapi.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    List<PostDto> getAllPostsByCategory(Integer categoryId);

    List<PostDto> getAllPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);


}
