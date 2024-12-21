  package com.example.demo.model;

  import jakarta.persistence.*;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

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
      @OneToMany(mappedBy = "tour")
      private List<ImageUrl> imageUrls;
      @Enumerated(EnumType.STRING)
      private TourType type; // Тип тура (пляжный, культурный и т.д.)
      private Integer duration; // Продолжительность тура (в днях)
      private Double price; // Цена
      @Lob
      private String description; // Описание тура
      private Boolean isHot;

      public enum TourType {
          ОТДЫХ, ЭКСКУРСИЯ, ШОПИНГ, ГАСТРОНОМИЧЕСКИЙ_ТУР
      }
  }
