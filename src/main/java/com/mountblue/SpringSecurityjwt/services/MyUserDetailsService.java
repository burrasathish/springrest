package com.mountblue.SpringSecurityjwt.services;

import com.mountblue.SpringSecurityjwt.models.Post;
import com.mountblue.SpringSecurityjwt.models.UserData;
import com.mountblue.SpringSecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Component
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());


    }

    public UserData findUser(String name) {
        return userRepository.findByUsername(name);
    }

    public List<UserData> listAll() {
        return userRepository.findAll();
    }

    public void saveUsers(UserData user) {
        userRepository.save(user);
    }




}
