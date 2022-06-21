package com.abhi.bloggerbook.Services.impel;

import com.abhi.bloggerbook.Exceptions.ResourceNotFoundException;
import com.abhi.bloggerbook.Payloads.CategoryDto;
import com.abhi.bloggerbook.Repositories.CatRepo;
import com.abhi.bloggerbook.Services.CategoryService;
import com.abhi.bloggerbook.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CatRepo categoryRepo;
    @Override

    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category c=this.categoryRepo.save(categoryDtoToCategory(categoryDto));
        return categoryToCategoryDto(c);
    }



    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category cat= this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category","id",catId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDesc(categoryDto.getCategoryDesc());
        Category c= this.categoryRepo.save(cat);
        return categoryToCategoryDto(c);
    }

    @Override
    public void deleteCategory(Integer catId) {

        Category cat= this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category","id",catId));

        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategoryById(Integer catId) {
        Category cat= this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("category","id",catId));

        return categoryToCategoryDto(cat);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories= this.categoryRepo.findAll();
        List<CategoryDto> categoriesDto= categories.stream().map((cat)-> new ModelMapper().map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoriesDto;
    }


    public Category categoryDtoToCategory(CategoryDto cDto)
    {
        ModelMapper mp= new ModelMapper();
        return  mp.map(cDto,Category.class);
    }

    private CategoryDto categoryToCategoryDto(Category c) {
        ModelMapper mp= new ModelMapper();
        return  mp.map(c,CategoryDto.class);
    }

}
