package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "signature_history")
public class SignatureHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "signature_id", nullable = false)
    private UUID signatureId;

    @Column(name = "version_created_at")
    private LocalDateTime versionCreatedAt;

    @Column(name = "threat_name")
    private String threatName;

    @Column(name = "first_bytes")
    private byte[] firstBytes;

    @Column(name = "remainder_hash")
    private String remainderHash;

    @Column(name = "remainder_length")
    private int remainderLength;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "offset_start")
    private int offsetStart;

    @Column(name = "offset_end")
    private int offsetEnd;

    @Column(name = "digital_signature, columnDefinition = text")
    private String digitalSignature;

    @Column(name = "status")
    private String status;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Геттеры и сеттеры

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public UUID getSignatureId() {
        return signatureId;
    }

    public void setSignatureId(UUID signatureId) {
        this.signatureId = signatureId;
    }

    public LocalDateTime getVersionCreatedAt() {
        return versionCreatedAt;
    }

    public void setVersionCreatedAt(LocalDateTime versionCreatedAt) {
        this.versionCreatedAt = versionCreatedAt;
    }

    public String getThreatName() {
        return threatName;
    }

    public void setThreatName(String threatName) {
        this.threatName = threatName;
    }

    // Добавленная часть для поля firstBytes
    public byte[] getFirstBytes() {
        return firstBytes;
    }

    public void setFirstBytes(byte[] firstBytes) {
        this.firstBytes = firstBytes;
    }

    public String getRemainderHash() {
        return remainderHash;
    }

    public void setRemainderHash(String remainderHash) {
        this.remainderHash = remainderHash;
    }

    public int getRemainderLength() {
        return remainderLength;
    }

    public void setRemainderLength(int remainderLength) {
        this.remainderLength = remainderLength;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getOffsetStart() {
        return offsetStart;
    }

    public void setOffsetStart(int offsetStart) {
        this.offsetStart = offsetStart;
    }

    public int getOffsetEnd() {
        return offsetEnd;
    }

    public void setOffsetEnd(int offsetEnd) {
        this.offsetEnd = offsetEnd;
    }

    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
