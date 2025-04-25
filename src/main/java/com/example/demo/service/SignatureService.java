package com.example.demo.service;

import com.example.demo.entity.Signature;
import com.example.demo.repository.SignatureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return repository.findByCreatedDateAfter(date);
    }

    public List<Signature> getSignaturesByIds(List<UUID> ids) {
        return repository.findAllById(ids);
    }

    public Signature createSignature(Signature signature, String createdBy) {
        signature.setCreatedBy(createdBy);
        signature.setCreatedDate(LocalDateTime.now());
        Signature saved = repository.save(signature);

        auditService.logAudit(saved.getId(), createdBy, "CREATED", "data,status");
        return saved;
    }

    public Optional<Signature> updateSignature(UUID id, Signature newSignature, String updatedBy) {
        return repository.findById(id).map(signature -> {
            StringBuilder changedFields = new StringBuilder();

            if (!signature.getData().equals(newSignature.getData())) {
                changedFields.append("data ");
                signature.setData(newSignature.getData());
            }

            if (!signature.getStatus().equals(newSignature.getStatus())) {
                changedFields.append("status ");
                signature.setStatus(newSignature.getStatus());
            }

            signature.setUpdatedBy(updatedBy);
            signature.setUpdatedDate(LocalDateTime.now());
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
        List<Signature> signatures = repository.findByCreatedDateAfter(date);
        for (Signature signature : signatures) {
            System.out.println("Проверка подписи: " + signature.getId() + " — статус: " + signature.getStatus());
        }
    }
}
