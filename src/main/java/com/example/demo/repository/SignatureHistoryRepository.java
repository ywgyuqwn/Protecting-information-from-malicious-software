package com.example.demo.repository;

import com.example.demo.entity.SignatureHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureHistoryRepository extends JpaRepository<SignatureHistory, Long> {
}
