package com.example.demo.model;

import lombok.Data;


import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "role_id", nullable = false)
     private Role role;

     @ManyToMany(mappedBy = "users")
     private List<TripPlan> plans;
}
