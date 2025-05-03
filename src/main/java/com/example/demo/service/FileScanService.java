package com.example.demo.service;

import com.example.demo.entity.Signature;
import com.example.demo.entity.SignatureScanResult;
import com.example.demo.repository.SignatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FileScanService {

    private static final int BUFFER_SIZE = 4096;
    private static final long BASE = 256L;
    private static final long MOD = 1_000_000_007L;

    @Autowired
    private SignatureRepository signatureRepository;

    public List<SignatureScanResult> scanFile(MultipartFile file) throws IOException {
        // Сохраняем файл для случайного доступа
        File tempFile = File.createTempFile("scanfile", ".tmp");
        try (InputStream in = file.getInputStream();
             FileOutputStream out = new FileOutputStream(tempFile)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int read;
            while ((read = in.read(buf)) != -1) {
                out.write(buf, 0, read);
            }
        }

        List<SignatureScanResult> results = new ArrayList<>();
        List<Signature> signatures = signatureRepository.findAll();

        for (Signature sig : signatures) {

            if (sig.getDigitalSignature() != null && sig.getDigitalSignature().length > 0) {
                continue;
            }
            scanWithRabinKarp(tempFile, sig).ifPresent(results::add);

        }

        tempFile.delete();
        return results;
    }

    private Optional<SignatureScanResult> scanWithRabinKarp(File file, Signature sig) throws IOException {
        // Подготовка шаблона
        byte[] pattern = sig.getFirstBytes();
        int m = pattern.length;
        long patternHash = computeHash(pattern);
        long power = modPow(BASE, m - 1, MOD);

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long fileLen = raf.length();
            Deque<Byte> window = new LinkedList<>();
            long rollingHash = 0;

            int raw;
            while ((raw = raf.read()) != -1) {
                byte b = (byte) raw;
                long pos = raf.getFilePointer();

                // обновление хэша
                if (window.size() < m) {
                    window.addLast(b);
                    rollingHash = (rollingHash * BASE + (b & 0xFF)) % MOD;
                    if (window.size() < m) continue;
                } else {
                    byte old = window.removeFirst();
                    window.addLast(b);
                    rollingHash = (rollingHash - (old & 0xFF) * power % MOD + MOD) % MOD;
                    rollingHash = (rollingHash * BASE + (b & 0xFF)) % MOD;
                }

                // сравнение
                if (window.size() == m && rollingHash == patternHash) {
                    long start = pos - m;
                    raf.seek(start);
                    byte[] actual = new byte[m];
                    raf.readFully(actual);
                    raf.seek(pos);
                    if (Arrays.equals(actual, pattern)) {
                        int tailLen = sig.getRemainderLength();
                        long tailPos = start + m;
                        if (fileLen >= tailPos + tailLen) {
                            raf.seek(tailPos);
                            byte[] tail = new byte[tailLen];
                            raf.readFully(tail);
                            String tailHash = computeSHA256(tail);
                            if (tailHash.equalsIgnoreCase(sig.getRemainderHash())) {
                                return Optional.of(new SignatureScanResult(
                                        sig.getId(),
                                        sig.getThreatName(),
                                        start,
                                        start + m + tailLen,
                                        true
                                ));
                            }
                            raf.seek(pos);
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }

    private long computeHash(byte[] data) {
        long h = 0;
        for (byte b : data) {
            h = (h * BASE + (b & 0xFF)) % MOD;
        }
        return h;
    }

    private long modPow(long base, long exp, long mod) {
        long result = 1;
        long cur = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * cur) % mod;
            cur = (cur * cur) % mod;
            exp >>= 1;
        }
        return result;
    }

    private String computeSHA256(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(data);
            StringBuilder sb = new StringBuilder(2 * digest.length);
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }
}
