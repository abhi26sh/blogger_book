package com.abhi.bloggerbook.Repositories;

import com.abhi.bloggerbook.models.Category;
import com.abhi.bloggerbook.models.Post;
import com.abhi.bloggerbook.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;
import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    Page<Post> findByUser(User user, Pageable p);
    Page<Post> findByCategory(Category cat, Pageable p);

    @Query("SELECT P FROM Post P WHERE P.title LIKE :KEY")
    List<Post> findByTitleContaining(@Param("KEY") String title);
}
