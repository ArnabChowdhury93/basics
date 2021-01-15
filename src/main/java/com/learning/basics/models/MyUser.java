package com.learning.basics.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "MY_USER")
@Data
public class MyUser {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "ENABLED")
    private Boolean isEnabled;

}
