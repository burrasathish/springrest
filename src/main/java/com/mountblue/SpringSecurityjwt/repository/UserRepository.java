package com.mountblue.SpringSecurityjwt.repository;

import com.mountblue.SpringSecurityjwt.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserData , Long> {
    UserData findByUsername(String username);
    UserData findByEmailIgnoreCase(String email);


}
