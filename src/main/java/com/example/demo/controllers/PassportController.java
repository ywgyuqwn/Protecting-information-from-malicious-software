package com.example.demo.controllers;

import com.example.demo.entity.Passport;
import com.example.demo.service.PassportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passports")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Passport> createPassport(@RequestBody Passport passport) {
        Passport created = passportService.createPassport(passport);
        return ResponseEntity.ok(created);
    }

    // READ - все
    @GetMapping
    public ResponseEntity<List<Passport>> getAllPassports() {
        List<Passport> passports = passportService.getAllPassports();
        return ResponseEntity.ok(passports);
    }

    // READ - один по ID
    @GetMapping("/{id}")
    public ResponseEntity<Passport> getPassportById(@PathVariable Long id) {
        Passport passport = passportService.getPassportById(id);
        if (passport != null) {
            return ResponseEntity.ok(passport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Passport> updatePassport(@PathVariable Long id, @RequestBody Passport newData) {
        Passport updated = passportService.updatePassport(id, newData);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassport(@PathVariable Long id) {
        boolean deleted = passportService.deletePassport(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }
}
