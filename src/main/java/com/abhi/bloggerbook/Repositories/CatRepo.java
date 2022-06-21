package com.abhi.bloggerbook.Repositories;

import com.abhi.bloggerbook.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepo extends JpaRepository<Category,Integer> {
}
