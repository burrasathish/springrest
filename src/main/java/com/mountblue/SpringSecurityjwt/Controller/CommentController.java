package com.mountblue.SpringSecurityjwt.Controller;

import com.mountblue.SpringSecurityjwt.models.Post;
import com.mountblue.SpringSecurityjwt.models.UserData;
import com.mountblue.SpringSecurityjwt.repository.CommentRepository;
import com.mountblue.SpringSecurityjwt.services.CommentService;
import com.mountblue.SpringSecurityjwt.models.CommenterDetails;
import com.mountblue.SpringSecurityjwt.services.MyUserDetailsService;
import com.mountblue.SpringSecurityjwt.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MyUserDetailsService userService;
    @Autowired
    private PostService postService;

    private Logger logger= LoggerFactory.getLogger(CommentController.class);
    @PostMapping("/postComment/{id}")
    public String addComment(@PathVariable Long id, Model model, Authentication auth,
                             @RequestBody CommenterDetails commenterDetails) {
        CommenterDetails Obj = new CommenterDetails();

        UserData userList = userService.findUser(auth.getName());
        Obj.setComment(commenterDetails.getComment());
        Obj.setEmail(userList.getEmail());
        Obj.setName(userList.getUsername());
        Obj.setPost(postService.get(id));
        commentService.saveComment(Obj);
        return "DisplayPost";
    }

     @GetMapping("/viewComment/{id}")
    public List<CommenterDetails> viewComment(@PathVariable Long id){
         Post post = postService.getPostData(id);
       return   commentRepository.findByPost(post);
    }


}
