package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.model.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonService personService;

    @Autowired
    public PersonDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Логирование для отладки
        System.out.println("Попытка найти пользователя с именем: " + username);

        // Поиск пользователя
        Person person = personService.findByUsername(username);

        // Проверка на null
        if (person == null) {
            System.err.println("Пользователь с именем '" + username + "' не найден");
            throw new UsernameNotFoundException("Пользователь с именем '" + username + "' не найден");
        }

        // Лог успешного поиска
        System.out.println("Пользователь найден: " + person.getUsername());

        // Возврат пользовательских данных
        return new PersonDetails(person);
    }
}
