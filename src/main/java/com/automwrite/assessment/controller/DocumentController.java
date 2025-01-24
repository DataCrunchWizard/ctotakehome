package com.automwrite.assessment.controller;

import com.automwrite.assessment.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/user-request")
    public ResponseEntity<byte[]> processUserRequest(
            @RequestParam("userIntent") MultipartFile userIntent,
            @RequestParam("template") MultipartFile template) throws IOException {
        
        byte[] document = documentService.processDocument(userIntent, template);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "processed_document.docx");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(document);
    }
} 