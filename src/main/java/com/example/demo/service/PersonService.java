package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PasswordEncoder passwordEncoder;
    private PersonRepository personRepository;
    private RoleService roleService;

    @Autowired
    public PersonService(PasswordEncoder passwordEncoder,
                         PersonRepository personRepository,
                         RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
        this.roleService = roleService;
    }
    public void registerUser(Person user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setRole(roleService.getRoleByName("ROLE_USER"));
        user.setPassword(encodedPassword);

        personRepository.save(user);
    }

    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }
}