package com.example.demo.service;

import com.example.demo.entity.Hobby;
import com.example.demo.repository.HobbyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HobbyService {

    private final HobbyRepository hobbyRepository;

    public HobbyService(HobbyRepository hobbyRepository) {
        this.hobbyRepository = hobbyRepository;
    }

    // CREATE
    public Hobby createHobby(Hobby hobby) {
        return hobbyRepository.save(hobby);
    }

    // READ - все
    public List<Hobby> getAllHobbies() {
        return hobbyRepository.findAll();
    }

    // READ - один по ID
    public Hobby getHobbyById(Long id) {
        return hobbyRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Hobby updateHobby(Long id, Hobby newData) {
        Optional<Hobby> optionalHobby = hobbyRepository.findById(id);
        if (optionalHobby.isPresent()) {
            Hobby existingHobby = optionalHobby.get();
            existingHobby.setHobbyName(newData.getHobbyName());
            // если есть ещё поля - обновляем

            return hobbyRepository.save(existingHobby);
        }
        return null;
    }

    // DELETE
    public boolean deleteHobby(Long id) {
        if (hobbyRepository.existsById(id)) {
            hobbyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
