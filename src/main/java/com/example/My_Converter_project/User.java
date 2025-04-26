package com.example.My_Converter_project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

//mapped to the "users" table in the DB
@Entity //mark this class as a JPA entity
@Table(name = "users") // specifies the db table name
public class User {
    @Id //marks a field sa the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_generated the primary key value
    private Long id;
    @Column(name="username", length=100, nullable = false) //maps the field to the "username" column
    private String username;
    @Column(name="password",  nullable = false)//maps the field to the "password" column
    private String password;
    @Column(name="email", length=100, nullable = false)//maps the field to the "email" column
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ConversionHistory> conversionHistories;
// constructor to create a new User
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    //default constructor required by JPA
    public User() {

    }
    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }



}
