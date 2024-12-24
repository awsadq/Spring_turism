package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;
    private final RoleService roleService;

    @Autowired
    public PersonService(PasswordEncoder passwordEncoder,
                         PersonRepository personRepository,
                         RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
        this.roleService = roleService;
    }

    // Регистрация нового пользователя
    public void registerUser(Person user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setRole(roleService.getRoleByName("ROLE_USER")); // Устанавливаем стандартную роль
        user.setPassword(encodedPassword);

        personRepository.save(user);
    }

    // Поиск пользователя по имени
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    // Проверка уникальности имени пользователя
    public boolean isUsernameUnique(String username, String currentUsername) {
        // Имя считается уникальным, если оно совпадает с текущим или не занято
        return username.equals(currentUsername) || !personRepository.existsByUsername(username);
    }

    // Проверка уникальности email
    public boolean isEmailUnique(String email, String currentEmail) {
        // Email считается уникальным, если он совпадает с текущим или не занят
        return email.equals(currentEmail) || !personRepository.existsByEmail(email);
    }

    // Обновление данных профиля
    public void updateProfile(Person user, String username, String email) {
        user.setUsername(username);
        user.setEmail(email);
        personRepository.save(user);
    }

    // Смена пароля
    public void changePassword(Person user, String newPassword) {
        if (newPassword.length() < 8) {
            throw new IllegalArgumentException("Пароль должен содержать не менее 8 символов");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        personRepository.save(user);
    }

}
