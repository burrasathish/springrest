package com.mountblue.SpringSecurityjwt.repository;

import com.mountblue.SpringSecurityjwt.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post , Long> {

    @Query("select  p from Post p  where  p.title like ?1 " +
            "or p.author like ?1 or p.excerpt like ?1")
    List<Post> search(String name);



    List<Post> findByAuthor(String name);

//    @Query("select p from Post p join p.tags t where  p.is_published=?1 and t.tagName=?2")
//    List<Post> findByTag(boolean value,String TagName);

}
