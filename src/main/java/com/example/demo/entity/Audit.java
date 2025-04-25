package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Long auditId;

    @Column(name = "signature_id")
    private UUID signatureId;

    @Column(name = "changed_by")
    private String changedBy;

    @Column(name = "change_type")
    private String changeType; // CREATED, UPDATED, DELETED, CORRUPTED и т.д.

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    @Column(name = "fields_changed")
    private String fieldsChanged;

    // Геттеры и сеттеры
    public Long getAuditId() {
        return auditId;
    }
    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }
    public UUID getSignatureId() {
        return signatureId;
    }
    public void setSignatureId(UUID signatureId) {
        this.signatureId = signatureId;
    }
    public String getChangedBy() {
        return changedBy;
    }
    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }
    public String getChangeType() {
        return changeType;
    }
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    public LocalDateTime getChangedAt() {
        return changedAt;
    }
    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
    public String getFieldsChanged() {
        return fieldsChanged;
    }
    public void setFieldsChanged(String fieldsChanged) {
        this.fieldsChanged = fieldsChanged;
    }
}
