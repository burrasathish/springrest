package com.mountblue.SpringSecurityjwt.repository;

import com.mountblue.SpringSecurityjwt.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

      Tag findByTagName(String tagName);

//    public Tag findByTag(String name);

//    Tag findByName(String name);

}
