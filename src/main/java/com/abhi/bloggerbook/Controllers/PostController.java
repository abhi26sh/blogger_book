package com.abhi.bloggerbook.Controllers;

import com.abhi.bloggerbook.Payloads.ApiResponse;
import com.abhi.bloggerbook.Payloads.PostDto;
import com.abhi.bloggerbook.Payloads.PostResponse;
import com.abhi.bloggerbook.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired(required = true)
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post/")
    public ResponseEntity<PostDto> createDto(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){

        return new ResponseEntity<>(this.postService.createPost(postDto,userId,categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getAllPostByUsers
            (@PathVariable Integer userId,
             @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
             @RequestParam(value = "size",defaultValue = "10",required = false)Integer pageSize )
    {
        PostResponse postResponse=this.postService.getAllPostByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getAllPostByCategory(
            @PathVariable Integer categoryId ,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "size",defaultValue = "10",required = false)Integer pageSize
    )
    {
        return new ResponseEntity<>(this.postService.getAllPostByCategory(categoryId,pageNumber,pageSize),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId){
        return new ResponseEntity<>(this.postService.getPost(postId),HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getALlPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "size",defaultValue = "10",required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
        return new ResponseEntity<>(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId)
    {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> UpdatePost( @RequestBody PostDto postDto ,@PathVariable int postId){

        PostDto postDto1= this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(postDto1,HttpStatus.CREATED);
    }

    @GetMapping("/posts/search/{keyword}") ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(this.postService.searchPost(keyword),HttpStatus.OK);
    }
}
