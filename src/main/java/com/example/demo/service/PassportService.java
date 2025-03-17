package com.example.demo.service;

import com.example.demo.entity.Passport;
import com.example.demo.repository.PassportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    // CREATE
    public Passport createPassport(Passport passport) {
        return passportRepository.save(passport);
    }

    // READ - все
    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    // READ - один по ID
    public Passport getPassportById(Long id) {
        return passportRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Passport updatePassport(Long id, Passport newData) {
        Optional<Passport> optionalPassport = passportRepository.findById(id);
        if (optionalPassport.isPresent()) {
            Passport existingPassport = optionalPassport.get();
            existingPassport.setNumber(newData.getNumber());
            // если нужны ещё поля - обновляем их

            return passportRepository.save(existingPassport);
        }
        return null; // или можно выбросить exception
    }

    // DELETE
    public boolean deletePassport(Long id) {
        if (passportRepository.existsById(id)) {
            passportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
