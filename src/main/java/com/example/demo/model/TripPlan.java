package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "trip_plans")
public class TripPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private LocalDate startDate;

    @Setter
    private Integer days;

    @Setter
    private String placesSelected;  // Список мест, выбранных пользователем

    @Setter
    private String additionalNotes;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne // Связь с пользователем
    @JoinColumn(name = "user_id") // Поле в таблице trip_plans, которое будет хранить id пользователя
    private Person user;

    // Геттеры
    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Integer getDays() {
        return days;
    }

    public String getPlacesSelected() {
        return placesSelected;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Person getUser() {
        return user;
    }
}
