package com.example.demo.controllers;

import com.example.demo.service.FileScanService;
import com.example.demo.entity.SignatureScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class ScanController {

    @Autowired
    private FileScanService fileScanService;

    // Загрузка файла для сканирования
    @PostMapping("/upload")
    public List<SignatureScanResult> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return fileScanService.scanFile(file);
    }
}

