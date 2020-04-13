package com.mountblue.SpringSecurityjwt.services;

import com.mountblue.SpringSecurityjwt.models.Role;
import  com.mountblue.SpringSecurityjwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleService {


    @Autowired
    private RoleRepository roleRepository;

    public Role get(String name){
        return roleRepository.findByName(name);
    }

}
