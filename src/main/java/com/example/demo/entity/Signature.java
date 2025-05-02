package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

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
            columnDefinition = "BYTEA",    // <- говорим Hibernate: в БД это BYTEA (BLOB)
            nullable        = false
    )
    private byte[] digitalSignature;
    // BLOB

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;          // DATETIME / TIMESTAMP

    @Column(name = "status", nullable = false)
    private String status;                    // VARCHAR (ACTUAL, DELETED, CORRUPTED)


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

}
