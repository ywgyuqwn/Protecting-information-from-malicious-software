package com.example.demo.controllers;

import com.example.demo.entity.Signature;
import com.example.demo.service.SignatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signatures")
public class SignatureController {

    private final SignatureService signatureService;

    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint is working!";
    }


    @PostMapping
    public ResponseEntity<Signature> create(@RequestBody Signature signature) {
        return ResponseEntity.ok(signatureService.createSignature(signature));
    }

    @GetMapping
    public ResponseEntity<List<Signature>> getAll() {
        return ResponseEntity.ok(signatureService.getAllSignatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Signature> getById(@PathVariable Long id) {
        Signature signature = signatureService.getSignatureById(id);
        if (signature != null) {
            return ResponseEntity.ok(signature);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Signature> update(@PathVariable Long id, @RequestBody Signature newData) {
        Signature updated = signatureService.updateSignature(id, newData);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = signatureService.deleteSignature(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
