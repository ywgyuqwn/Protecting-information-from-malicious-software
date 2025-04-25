package com.example.demo.service;

import com.example.demo.entity.Audit;
import com.example.demo.repository.AuditRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AuditService {

    private final AuditRepository auditRepository;

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    // Получение всех записей аудита
    public List<Audit> getAllAudits() {
        return auditRepository.findAll();
    }

    // Получение записей аудита по signatureId
    public List<Audit> getAuditsBySignatureId(UUID signatureId) {
        return auditRepository.findBySignatureId(signatureId);
    }

    // Логирование действия (create, update, delete)
    public void logAudit(UUID signatureId, String changedBy, String changeType, String fieldsChanged) {
        Audit audit = new Audit();
        audit.setSignatureId(signatureId);
        audit.setChangedBy(changedBy);
        audit.setChangeType(changeType);
        audit.setFieldsChanged(fieldsChanged);
        audit.setChangedAt(LocalDateTime.now());
        auditRepository.save(audit);
    }
}
