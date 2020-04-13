package com.mountblue.SpringSecurityjwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="roles")
public class Role {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false,unique = true)
    @NotEmpty
    private String name;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public List<UserData> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserData> users) {
//        this.users = users;
//    }
}
