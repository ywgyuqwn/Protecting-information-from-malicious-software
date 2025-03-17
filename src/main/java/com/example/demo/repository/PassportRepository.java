package com.example.demo.repository;

import com.example.demo.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    // Можем добавить метод для поиска по номеру:
    // Optional<Passport> findByNumber(String number);
}
