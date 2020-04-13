package com.mountblue.SpringSecurityjwt.services;

import com.mountblue.SpringSecurityjwt.models.Post;
import com.mountblue.SpringSecurityjwt.models.Tag;
import com.mountblue.SpringSecurityjwt.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.Objects.isNull;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagService {
    @Autowired
    private TagRepository tagRepository;


    public List<Tag> tagList(Post post, String tags){
        List<Tag> tagList = new ArrayList<>();
        String[] arrayList = tags.split(",");
        Tag found = null;
        try {
            for (String taglist : arrayList) {
                found = tagRepository.findByTagName(taglist); //change

                if (isNull(found)) {
                    found = new Tag();
                    List<Post> posts = new ArrayList<>();
                    posts.add(post);
                    found.setTagName(taglist);
                    found.setPost(posts);
                } else {
                    found.getPost().add(post);
                }
                tagRepository.save(found);
                tagList.add(found);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tagList;
    }

    public Tag getTagName(String name) {
        return tagRepository.findByTagName(name);
    }

}
