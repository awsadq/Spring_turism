package com.example.demo.config;

import com.example.demo.service.PersonDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.PasswordManagementDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // Разрешаем доступ к этим страницам
                        .requestMatchers("/register", "/login", "/").permitAll()  // Открытый доступ для всех на /register и /login
                        .anyRequest().authenticated() // Требуется аутентификация для всех остальных запросов
                )
                .formLogin(form -> form
                        .loginPage("/login") // Страница входа (добавьте её в проект)
                        .permitAll()
                        .defaultSuccessUrl("/index", true) // Перенаправление на главную страницу после успешной авторизации
                        .loginProcessingUrl("/login")
                );
//                .logout(logout -> logout
//                        .logoutUrl("/logout")                    // URL для выхода из системы
//                        .logoutSuccessUrl("/login?logout")       // Перенаправление после выхода
//                        .permitAll()
//                );
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(PersonDetailsService personDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(personDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
