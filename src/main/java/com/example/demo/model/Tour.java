package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tours")
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tourist_place_id", nullable = false)
    private TouristPlace touristPlace; // Страна

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ImageUrl> imageUrls; // Изображения тура

    @Enumerated(EnumType.STRING)
    private TourType type; // Тип тура (пляжный, культурный и т.д.)

    private Integer duration; // Продолжительность тура (в днях)
    private Double price; // Цена
    @Lob
    private String description; // Описание тура
    private Boolean isHot; // Является ли тур горящим

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)  // Добавляем связь с пользователем, который забронировал тур
    private Person user;

    private Boolean isBooked = false;  // Флаг для бронирования тура

    private LocalDate bookingDate; // Дата бронирования

    // Перечисление для типа тура
    public enum TourType {
        ОТДЫХ, ЭКСКУРСИЯ, ШОПИНГ, ГАСТРОНОМИЧЕСКИЙ_ТУР
    }

    public Boolean isBooked() {
        return isBooked;
    }

    // Метод для бронирования тура
    public void bookTour(Person user) {
        if (this.isBooked) {
            throw new IllegalStateException("Тур уже забронирован!");
        }
        this.isBooked = true;
        this.user = user;
        this.bookingDate = LocalDate.now(); // Устанавливаем текущую дату бронирования
    }

    public void cancelBooking() {
        this.isBooked = false;
        this.user = null;
        this.bookingDate = null; // Сбрасываем дату бронирования
    }
}
