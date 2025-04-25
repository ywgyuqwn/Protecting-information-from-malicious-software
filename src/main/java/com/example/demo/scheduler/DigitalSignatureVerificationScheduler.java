package com.example.demo.scheduler;

import com.example.demo.service.SignatureService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DigitalSignatureVerificationScheduler {

    private final SignatureService signatureService;

    public DigitalSignatureVerificationScheduler(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @Scheduled(fixedRate = 60000)
    public void verify() {
        LocalDateTime date = LocalDateTime.now().minusMinutes(5);
        signatureService.verifySignatures(date);
    }
}
