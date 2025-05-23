package com.example.demo.service;

import com.example.demo.entity.Signature;
import com.example.demo.repository.SignatureRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SignatureService {

    private final SignatureRepository repository;
    private final AuditService auditService;

    public SignatureService(SignatureRepository repository, AuditService auditService) {
        this.repository = repository;
        this.auditService = auditService;
    }

    public List<Signature> getAllSignatures() {
        return repository.findAll();
    }

    public List<Signature> getSignaturesSince(LocalDateTime date) {
        return repository.findByUpdatedAtAfter(date);
    }

    public List<Signature> getSignaturesByIds(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    public Signature createSignature(Signature signature, String createdBy) {
        // Декодируем digitalSignature, если она не пуста
        if (signature.getDigitalSignature() != null && signature.getDigitalSignature().length > 0) {
            try {
                // Преобразуем строку Base64 в byte[]
                byte[] decodedDigitalSignature = Base64.getDecoder().decode(signature.getDigitalSignature());
                signature.setDigitalSignature(decodedDigitalSignature);
            } catch (IllegalArgumentException e) {
                // Логируем ошибку, если Base64 строка некорректна
                throw new IllegalArgumentException("Invalid Base64 encoding for digitalSignature.");
            }
        }

        // Устанавливаем время обновления
        signature.setUpdatedAt(LocalDateTime.now());

        // Сохраняем сущность
        Signature saved = repository.save(signature);

        // Логируем создание записи в аудите
        auditService.logAudit(
                saved.getId(),
                createdBy,
                "CREATED",
                "threatName,firstBytes,remainderHash,remainderLength,fileType,offsetStart,offsetEnd,digitalSignature,status"
        );

        return saved;
    }



    public Optional<Signature> updateSignature(UUID id, Signature newSignature, String updatedBy) {
        return repository.findById(id).map(signature -> {
            StringBuilder changedFields = new StringBuilder();

            if (!signature.getThreatName().equals(newSignature.getThreatName())) {
                changedFields.append("threatName ");
                signature.setThreatName(newSignature.getThreatName());
            }
    // аналогично для firstBytes, remainderHash, remainderLength, fileType, offsetStart, offsetEnd, digitalSignature
            if (!signature.getStatus().equals(newSignature.getStatus())) {
                changedFields.append("status ");
                signature.setStatus(newSignature.getStatus());
            }

// обновляем временную метку
            signature.setUpdatedAt(LocalDateTime.now());
            Signature updated = repository.save(signature);
            auditService.logAudit(updated.getId(), updatedBy, "UPDATED", changedFields.toString().trim());

            return updated;
        });
    }

    public void deleteSignature(UUID id, String deletedBy) {
        Optional<Signature> signatureOpt = repository.findById(id);
        signatureOpt.ifPresent(signature -> {
            repository.delete(signature);
            auditService.logAudit(signature.getId(), deletedBy, "DELETED", "all");
        });
    }

    public Optional<Signature> getSignatureById(UUID id) {
        return repository.findById(id);
    }

    public void verifySignatures(LocalDateTime date) {
        List<Signature> signatures = repository.findByUpdatedAtAfter(date);
        for (Signature signature : signatures) {
            System.out.println("Проверка подписи: " + signature.getId() + " — статус: " + signature.getStatus());
        }
    }
}
