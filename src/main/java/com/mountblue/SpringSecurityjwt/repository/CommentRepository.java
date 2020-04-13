package com.mountblue.SpringSecurityjwt.repository;

import com.mountblue.SpringSecurityjwt.models.CommenterDetails;
import com.mountblue.SpringSecurityjwt.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<CommenterDetails, Long> {

    @Query("select c from CommenterDetails  c where c.post=?1")
    public List<CommenterDetails> findByPost(Post post);

    @Query("DELETE FROM CommenterDetails c WHERE c.post=?1")
  public  List<CommenterDetails> deletePost(Long  PostId);

}
