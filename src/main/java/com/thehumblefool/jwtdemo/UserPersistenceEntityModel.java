package com.thehumblefool.jwtdemo;

import java.io.Serializable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//@Entity
//@Table(name = "jwt_users")
public class UserPersistenceEntityModel implements Serializable {

    private static final long serialVersionUID = 2725709517849098501L;

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @Column(name = "username", unique = true, nullable = false)
    private String username;

//    @Column(name = "email", unique = true, nullable = false)
    private String email;

//    @Column(name = "password", nullable = false)
    private String password;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPersistenceEntityModel() {
    }

}
