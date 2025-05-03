package com.example.demo.entity;

import java.util.UUID;

public class SignatureScanResult {
    private UUID signatureId;
    private String threatName;
    private long offsetFromStart;
    private long offsetFromEnd;
    private boolean matched;

    // Jackson требует конструктор без аргументов
    public SignatureScanResult() {
    }

    // Ваш прежний конструтор для удобства
    public SignatureScanResult(UUID signatureId, String threatName, long offsetFromStart, long offsetFromEnd, boolean matched) {
        this.signatureId = signatureId;
        this.threatName = threatName;
        this.offsetFromStart = offsetFromStart;
        this.offsetFromEnd = offsetFromEnd;
        this.matched = matched;
    }

    // Геттеры (Jackson увидит эти методы и будет сериализовать поля)
    public UUID getSignatureId() {
        return signatureId;
    }

    public String getThreatName() {
        return threatName;
    }

    public long getOffsetFromStart() {
        return offsetFromStart;
    }

    public long getOffsetFromEnd() {
        return offsetFromEnd;
    }

    public boolean isMatched() {
        return matched;
    }

    // Сеттеры (необязательно, но могут потребоваться при десериализации)
    public void setSignatureId(UUID signatureId) {
        this.signatureId = signatureId;
    }

    public void setThreatName(String threatName) {
        this.threatName = threatName;
    }

    public void setOffsetFromStart(long offsetFromStart) {
        this.offsetFromStart = offsetFromStart;
    }

    public void setOffsetFromEnd(long offsetFromEnd) {
        this.offsetFromEnd = offsetFromEnd;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
