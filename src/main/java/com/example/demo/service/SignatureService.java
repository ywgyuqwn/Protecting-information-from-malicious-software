package com.example.demo.service;

import com.example.demo.entity.Signature;
import com.example.demo.repository.SignatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignatureService {

    private final SignatureRepository signatureRepository;

    public SignatureService(SignatureRepository signatureRepository) {
        this.signatureRepository = signatureRepository;
    }

    public Signature createSignature(Signature signature) {
        return signatureRepository.save(signature);
    }

    public List<Signature> getAllSignatures() {
        return signatureRepository.findAll();
    }

    public Signature getSignatureById(Long id) {
        return signatureRepository.findById(id).orElse(null);
    }

    public Signature updateSignature(Long id, Signature newData) {
        return signatureRepository.findById(id).map(signature -> {
            // обновляем поля
            signature.setDetectedObjectName(newData.getDetectedObjectName());
            signature.setFirst8Bytes(newData.getFirst8Bytes());
            signature.setHashOfRest(newData.getHashOfRest());
            signature.setRestLength(newData.getRestLength());
            signature.setFileType(newData.getFileType());
            signature.setStartOffset(newData.getStartOffset());
            signature.setEndOffset(newData.getEndOffset());
            return signatureRepository.save(signature);
        }).orElse(null);
    }

    public boolean deleteSignature(Long id) {
        if (signatureRepository.existsById(id)) {
            signatureRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
