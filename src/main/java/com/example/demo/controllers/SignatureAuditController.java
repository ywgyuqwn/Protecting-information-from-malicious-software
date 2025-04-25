package com.example.demo.controllers;

import com.example.demo.entity.Audit;
import com.example.demo.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/audits")
public class SignatureAuditController {

    private final AuditService auditService;

    @Autowired
    public SignatureAuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    // Получение всех записей аудита по конкретной подписи
    @GetMapping("/{signatureId}")
    public ResponseEntity<List<Audit>> getAuditBySignatureId(@PathVariable UUID signatureId) {
        List<Audit> audits = auditService.getAuditsBySignatureId(signatureId);
        if (audits.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(audits);
    }

    // Получение всех записей аудита
    @GetMapping
    public ResponseEntity<List<Audit>> getAllAudits() {
        return ResponseEntity.ok(auditService.getAllAudits());
    }
}
