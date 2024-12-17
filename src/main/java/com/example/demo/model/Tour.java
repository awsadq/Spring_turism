  package com.example.demo.model;

  import jakarta.persistence.*;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  @Data
  @Entity
  @Table(name = "tours")
  @NoArgsConstructor
  @AllArgsConstructor
  public class Tour {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id; // Уникальный идентификатор тура
      private String country; // Страна
      @Enumerated(EnumType.STRING)
      private TourType type; // Тип тура (пляжный, культурный и т.д.)
      private Integer duration; // Продолжительность тура (в днях)
      private Double price; // Цена
      @Lob
      private String description; // Описание тура
      private Boolean is_hot;

      public enum TourType {
          ОТДЫХ, ЭКСКУРСИЯ, ШОПИНГ, ГАСТРОНОМИЧЕСКИЙ_ТУР
      }
  }
