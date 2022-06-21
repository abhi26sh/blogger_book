package com.abhi.bloggerbook.Controllers;

import com.abhi.bloggerbook.Payloads.ApiResponse;
import com.abhi.bloggerbook.Payloads.CategoryDto;
import com.abhi.bloggerbook.Services.CategoryService;
import com.abhi.bloggerbook.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService catService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
    {

        CategoryDto c=this.catService.createCategory(categoryDto);
        return new ResponseEntity<>(c, HttpStatus.CREATED);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer cId)
    {
        CategoryDto c= this.catService.updateCategory(categoryDto,cId);
        return new ResponseEntity<>(c, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory( @PathVariable("catId") Integer catId)
    {
        CategoryDto c=this.catService.getCategoryById(catId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> c=this.catService.getAllCategory();
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId)
    {
        this.catService.deleteCategory(catId);
        return new ResponseEntity<>(new ApiResponse("category deleted successfully", true), HttpStatus.OK);
    }
}
