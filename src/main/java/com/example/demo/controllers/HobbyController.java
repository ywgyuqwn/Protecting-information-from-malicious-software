package com.example.demo.controllers;

import com.example.demo.entity.Hobby;
import com.example.demo.service.HobbyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hobbies")
public class HobbyController {

    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Hobby> createHobby(@RequestBody Hobby hobby) {
        Hobby created = hobbyService.createHobby(hobby);
        return ResponseEntity.ok(created);
    }

    // READ - все
    @GetMapping
    public ResponseEntity<List<Hobby>> getAllHobbies() {
        List<Hobby> hobbies = hobbyService.getAllHobbies();
        return ResponseEntity.ok(hobbies);
    }

    // READ - один по ID
    @GetMapping("/{id}")
    public ResponseEntity<Hobby> getHobbyById(@PathVariable Long id) {
        Hobby hobby = hobbyService.getHobbyById(id);
        if (hobby != null) {
            return ResponseEntity.ok(hobby);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Hobby> updateHobby(@PathVariable Long id, @RequestBody Hobby newData) {
        Hobby updated = hobbyService.updateHobby(id, newData);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHobby(@PathVariable Long id) {
        boolean deleted = hobbyService.deleteHobby(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();  // 404
        }
    }
}
