package com.mountblue.SpringSecurityjwt.Controller;


import com.mountblue.SpringSecurityjwt.models.Post;
import com.mountblue.SpringSecurityjwt.models.Tag;
import com.mountblue.SpringSecurityjwt.services.PostService;
import com.mountblue.SpringSecurityjwt.services.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    private Logger logger= LoggerFactory.getLogger(TagController.class);

    @GetMapping("/tag/{tagName}")
      public List<Post> filterTag(@PathVariable("tagName") String tagName){
//        logger.info(tagName);
        Tag TagList=tagService.getTagName(tagName);
        List<Post> postTags=TagList.getPost();
        return postTags;
    }

}
