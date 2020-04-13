package com.mountblue.SpringSecurityjwt.Controller;

import com.mountblue.SpringSecurityjwt.models.AuthenticationResponse;
import com.mountblue.SpringSecurityjwt.models.Post;
import com.mountblue.SpringSecurityjwt.models.Tag;
import com.mountblue.SpringSecurityjwt.repository.CommentRepository;
import com.mountblue.SpringSecurityjwt.repository.PostRepository;
import com.mountblue.SpringSecurityjwt.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PostController {

    @Autowired
   private PostService postService;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    private Logger logger= LoggerFactory.getLogger(PostController.class);


    @PostMapping("/savePosts/{tags}")
    public ResponseEntity<?> addPosts(@RequestBody Post post, @PathVariable("tags") String tags , Authentication auth) {
        postService.PostAddService(post , auth, tags);
        return ResponseEntity.ok("added post");
    }

    @PutMapping("/editPost/{id}")
    public  ResponseEntity<?>  editPost(@RequestBody Post post, @PathVariable(name="id") Long id,Authentication auth){
          post.setId(id);
          post.setAuthor(auth.getName());
    postRepository.save(post);
        return ResponseEntity.ok("post updated");
    }

//    ==============dubt============

    @DeleteMapping("/deletePost/{id}")
    public  ResponseEntity<?> DeletePost(@PathVariable(name = "id") Long id){
        commentRepository.deletePost(id);
        postService.deletePost(id);
        return ResponseEntity.ok("deleted post");
    }

    @GetMapping("/search/{StringSearch}")
    public ResponseEntity<?> searchPosts (@PathVariable String StringSearch) {
        List<Post> posts =  postRepository.search(StringSearch);
        return ResponseEntity.ok(posts);
    }


}
