package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "signatures")
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detectedObjectName;

    // Можно хранить как строку (например, HEX формат первых 8 байт)
    private String first8Bytes;

    // Хэш остатка сигнатуры
    private String hashOfRest;

    // Длина остатка сигнатуры
    private int restLength;

    // Тип файла
    private String fileType;

    // Смещение начала
    private int startOffset;

    // Смещение конца
    private int endOffset;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public String getDetectedObjectName() {
        return detectedObjectName;
    }
    public void setDetectedObjectName(String detectedObjectName) {
        this.detectedObjectName = detectedObjectName;
    }

    public String getFirst8Bytes() {
        return first8Bytes;
    }
    public void setFirst8Bytes(String first8Bytes) {
        this.first8Bytes = first8Bytes;
    }

    public String getHashOfRest() {
        return hashOfRest;
    }
    public void setHashOfRest(String hashOfRest) {
        this.hashOfRest = hashOfRest;
    }

    public int getRestLength() {
        return restLength;
    }
    public void setRestLength(int restLength) {
        this.restLength = restLength;
    }

    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getStartOffset() {
        return startOffset;
    }
    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    public int getEndOffset() {
        return endOffset;
    }
    public void setEndOffset(int endOffset) {
        this.endOffset = endOffset;
    }
}
