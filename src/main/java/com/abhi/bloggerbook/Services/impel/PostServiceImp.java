package com.abhi.bloggerbook.Services.impel;

import com.abhi.bloggerbook.Exceptions.ResourceNotFoundException;
import com.abhi.bloggerbook.Payloads.PostDto;
import com.abhi.bloggerbook.Payloads.PostResponse;
import com.abhi.bloggerbook.Repositories.CatRepo;
import com.abhi.bloggerbook.Repositories.PostRepo;
import com.abhi.bloggerbook.Repositories.UserRepo;
import com.abhi.bloggerbook.Services.PostService;
import com.abhi.bloggerbook.models.Category;
import com.abhi.bloggerbook.models.Post;
import com.abhi.bloggerbook.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class PostServiceImp implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired(required = true)
    CatRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer catId) {
        User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        Category cat= this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category","id",catId));


        Post post= modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setImageName("default.png");
        post.setCategory(cat);
        post.setUser(user);

        Post p=this.postRepo.save(post);
        return modelMapper.map(p,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("category","id",postId));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);


    }

    @Override
    public void deletePost(Integer postId) {

        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("category","id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPost(Integer postId) {

        Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("category","id",postId));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getAllPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize) {

        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        Pageable p= PageRequest.of(pageNumber,pageSize);

        Page<Post> posts= this.postRepo.findByCategory(cat,p);
        PostResponse postResponse= new PostResponse();
        List<Post> allPosts= posts.getContent();
        List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


        postResponse.setPageNumber(pageNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setPostDtos(postDtos);
        postResponse.setLastPage(posts.isLast());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());

        return postResponse;
    }

    @Override
    public PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

        User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        Pageable p= PageRequest.of(pageNumber,pageSize);

        Page<Post> posts= this.postRepo.findByUser(user,p);
        PostResponse postResponse= new PostResponse();
        List<Post> allPosts= posts.getContent();
        List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


        postResponse.setPageNumber(pageNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setPostDtos(postDtos);
        postResponse.setLastPage(posts.isLast());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {

        List<Post> allPosts= this.postRepo.findByTitleContaining("%"+keyword+"%");
        List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }else{
            sort=Sort.by(sortBy).descending();
        }
        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> posts= this.postRepo.findAll(p);

        PostResponse postResponse= new PostResponse();
        List<Post> allPosts= posts.getContent();
        List<PostDto> postDtos= allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());


        postResponse.setPageNumber(pageNumber);
        postResponse.setPageSize(pageSize);
        postResponse.setPostDtos(postDtos);
        postResponse.setLastPage(posts.isLast());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());

        return postResponse;
    }
}
