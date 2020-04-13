package com.mountblue.SpringSecurityjwt.services;


import com.mountblue.SpringSecurityjwt.models.CommenterDetails;
import com.mountblue.SpringSecurityjwt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public void saveComment(CommenterDetails cd) {
        commentRepository.save(cd);
    }


    public List<CommenterDetails> CommenterList() {
        return commentRepository.findAll();
    }


    public CommenterDetails getCommentData(Long id) {
        CommenterDetails post = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return post;
    }




}
