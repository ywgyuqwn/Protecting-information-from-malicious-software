package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // CREATE
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    // READ - все
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // READ - один по ID
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Company updateCompany(Long id, Company newData) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();
            existingCompany.setCompanyName(newData.getCompanyName());
            // при необходимости обновляйте и другие поля

            return companyRepository.save(existingCompany);
        }
        return null;
    }

    // DELETE
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
