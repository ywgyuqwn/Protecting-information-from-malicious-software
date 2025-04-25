package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@Service
public class DigitalSignatureService {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public DigitalSignatureService() throws NoSuchAlgorithmException {
        // Генерация пары ключей (в реальном проекте ключи должны храниться в защищённом хранилище)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public String signData(String data) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] digitalSig = signature.sign();
        return Base64.getEncoder().encodeToString(digitalSig);
    }

    public boolean verifySignature(String data, String sigStr) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(data.getBytes(StandardCharsets.UTF_8));
        byte[] digitalSig = Base64.getDecoder().decode(sigStr);
        return signature.verify(digitalSig);
    }
}
