package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
    Person findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
