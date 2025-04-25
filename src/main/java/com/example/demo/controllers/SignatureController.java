package com.example.demo.controllers;

import com.example.demo.entity.Signature;
import com.example.demo.service.SignatureService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/signatures")
public class SignatureController {

    private final SignatureService signatureService;

    public SignatureController(SignatureService signatureService) {
        this.signatureService = signatureService;
    }

    @GetMapping
    public List<Signature> getAll() {
        return signatureService.getAllSignatures();
    }

    @GetMapping("/{id}")
    public Optional<Signature> getById(@PathVariable UUID id) {
        return signatureService.getSignatureById(id);
    }

    @GetMapping("/since/{date}")
    public List<Signature> getSince(@PathVariable String date) {
        return signatureService.getSignaturesSince(LocalDateTime.parse(date));
    }

    @PostMapping("/by-ids")
    public List<Signature> getByIds(@RequestBody List<UUID> ids) {
        return signatureService.getSignaturesByIds(ids);
    }

    @PostMapping
    public Signature create(@RequestBody Signature signature) {
        return signatureService.createSignature(signature, "system");
    }

    @PutMapping("/{id}")
    public Optional<Signature> update(@PathVariable UUID id, @RequestBody Signature signature) {
        return signatureService.updateSignature(id, signature, "system");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        signatureService.deleteSignature(id, "system");
    }
}
