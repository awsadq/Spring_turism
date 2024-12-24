package com.example.demo.model;

import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name = "user")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // Имя пользователя

    @Column(nullable = false)
    private String password; // Пароль пользователя

    @Column(nullable = false, unique = true)
    private String email; // Email пользователя

    // Роль пользователя
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Добавляем связь с забронированными турами
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)  // Используем "user" вместо "users"
    private List<Tour> tours; // Список забронированных туров
}
