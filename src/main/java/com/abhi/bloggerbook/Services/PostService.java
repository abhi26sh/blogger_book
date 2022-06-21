package com.abhi.bloggerbook.Services;

import com.abhi.bloggerbook.Payloads.PostDto;
import com.abhi.bloggerbook.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostDto getPost(Integer postId);

    PostResponse getAllPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);


    PostResponse getAllPostByUser( Integer userId,Integer pageNumber, Integer pageSize);

    List<PostDto> searchPost( String keyword);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

}
