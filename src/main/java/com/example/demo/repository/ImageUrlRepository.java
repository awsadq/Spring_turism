package com.example.demo.repository;

import com.example.demo.model.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageUrlRepository extends JpaRepository<ImageUrl, Long> {
    List<ImageUrl> findByTourId(Long tourId);
}

