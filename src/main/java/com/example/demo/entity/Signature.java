package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;                           // GUID (PK)

    @Column(name = "threat_name", nullable = false)
    private String threatName;                // VARCHAR / TEXT

    @Column(name = "first_bytes", nullable = false, columnDefinition = "BYTEA")
    private byte[] firstBytes;                // BINARY(8)

    @Column(name = "remainder_hash", nullable = false)
    private String remainderHash;             // VARCHAR

    @Column(name = "remainder_length", nullable = false)
    private Integer remainderLength;          // INT

    @Column(name = "file_type", nullable = false)
    private String fileType;                  // VARCHAR

    @Column(name = "offset_start", nullable = false)
    private Integer offsetStart;              // INT

    @Column(name = "offset_end", nullable = false)
    private Integer offsetEnd;                // INT

    @Column(name            = "digital_signature",
            columnDefinition = "BYTEA",
            nullable        = true
    )
    private byte[] digitalSignature;
    // BLOB

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;          // DATETIME / TIMESTAMP

    @Column(name = "status", nullable = false)
    private String status;                    // VARCHAR (ACTUAL, DELETED, CORRUPTED)

    // Новые поля для результатов сканирования
    @Transient
    private long offsetFromStart;  // Смещение начала сигнатуры в файле
    @Transient
    private long offsetFromEnd;    // Смещение конца сигнатуры в файле
    @Transient
    private boolean matched;       // Флаг, совпала ли сигнатура

    public Signature() {
    }

    // id
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    // threatName
    public String getThreatName() {
        return threatName;
    }
    public void setThreatName(String threatName) {
        this.threatName = threatName;
    }

    // firstBytes
    public byte[] getFirstBytes() {
        return firstBytes;
    }
    public void setFirstBytes(byte[] firstBytes) {
        this.firstBytes = firstBytes;
    }

    public long getFirstBytesHash() throws NoSuchAlgorithmException {
        // Используем алгоритм SHA-256 для вычисления хэша
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Вычисляем хэш от первых байт (firstBytes)
        byte[] hash = md.digest(firstBytes);

        // Преобразуем первые 8 байт хэша в число long
        return bytesToLong(hash);
    }

    private long bytesToLong(byte[] bytes) {
        long result = 0;
        // Переводим первые 8 байтов в число типа long
        for (int i = 0; i < 8 && i < bytes.length; i++) {
            result = (result << 8) | (bytes[i] & 0xFF);  // Сдвигаем на 8 бит и добавляем байт
        }
        return result;
    }

    // remainderHash
    public String getRemainderHash() {
        return remainderHash;
    }
    public void setRemainderHash(String remainderHash) {
        this.remainderHash = remainderHash;
    }

    // remainderLength
    public Integer getRemainderLength() {
        return remainderLength;
    }
    public void setRemainderLength(Integer remainderLength) {
        this.remainderLength = remainderLength;
    }

    // fileType
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    // offsetStart
    public Integer getOffsetStart() {
        return offsetStart;
    }
    public void setOffsetStart(Integer offsetStart) {
        this.offsetStart = offsetStart;
    }

    // offsetEnd
    public Integer getOffsetEnd() {
        return offsetEnd;
    }
    public void setOffsetEnd(Integer offsetEnd) {
        this.offsetEnd = offsetEnd;
    }

    // digitalSignature
    public byte[] getDigitalSignature() {
        return digitalSignature;
    }
    public void setDigitalSignature(byte[] digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    // updatedAt
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Новые геттеры и сеттеры для transient полей
    public long getOffsetFromStart() {
        return offsetFromStart;
    }
    public void setOffsetFromStart(long offsetFromStart) {
        this.offsetFromStart = offsetFromStart;
    }

    public long getOffsetFromEnd() {
        return offsetFromEnd;
    }
    public void setOffsetFromEnd(long offsetFromEnd) {
        this.offsetFromEnd = offsetFromEnd;
    }

    public boolean isMatched() {
        return matched;
    }
    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "id=" + id +
                ", threatName='" + threatName + '\'' +
                ", firstBytes=" + firstBytes.length +
                ", remainderHash='" + remainderHash + '\'' +
                ", remainderLength=" + remainderLength +
                ", fileType='" + fileType + '\'' +
                ", offsetStart=" + offsetStart +
                ", offsetEnd=" + offsetEnd +
                ", status='" + status + '\'' +
                ", offsetFromStart=" + offsetFromStart +
                ", offsetFromEnd=" + offsetFromEnd +
                ", matched=" + matched +
                '}';
    }

}
