package com.example.demo.repository;

import com.example.demo.entity.Signature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface SignatureRepository extends JpaRepository<Signature, UUID> {
    List<Signature> findByStatus(String status);
    List<Signature> findByUpdatedAtAfter(LocalDateTime date);
}
