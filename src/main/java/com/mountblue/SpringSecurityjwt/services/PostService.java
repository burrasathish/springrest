package com.mountblue.SpringSecurityjwt.services;

import com.mountblue.SpringSecurityjwt.models.Post;
import com.mountblue.SpringSecurityjwt.models.Tag;
import com.mountblue.SpringSecurityjwt.models.UserData;
import com.mountblue.SpringSecurityjwt.repository.PostRepository;
import com.mountblue.SpringSecurityjwt.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private TagRepository tagRepository;

    public void PostAddService(Post posts, Authentication auth, String tags) {
        List<Tag> tagList = new ArrayList<>();
        String[] arrayList = tags.split(",");
        Tag found = null;
        try {
            for (String tagString : arrayList) {
                found = tagRepository.findByTagName(tagString); //change
                if (isNull(found)) {
                Tag   tag = new Tag();
                    tag.setTagName(tagString);
                    tag.getPost().add(posts);
                   posts.getTags().contains(tag);
                   tagList.add(tag);
                } else {
                     posts.getTags().add(found);
                     tagList.add(found);
                }
            }
            posts.setAuthor(auth.getName());
              posts.setTags(tagList);
            postRepository.save(posts);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Post getPostData(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return post;
    }

    public Post get(Long id) {
        return postRepository.findById(id).get();
    }

    


    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findPostByUser(String name){
        return postRepository.findByAuthor(name);
    }

    public List<Post> listAll() {
        return postRepository.findAll();
    }

//    public List<Post> tagData(String TagName) {
//        return postRepository.findByTag(true, TagName);
//    }





}