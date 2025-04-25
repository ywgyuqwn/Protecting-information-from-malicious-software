package com.example.demo.repository;

import com.example.demo.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditRepository extends JpaRepository<Audit, Long> {
    List<Audit> findBySignatureId(UUID signatureId);
}
