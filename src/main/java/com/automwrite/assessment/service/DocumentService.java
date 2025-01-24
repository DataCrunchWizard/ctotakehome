package com.automwrite.assessment.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface DocumentService {
    /**
     * Process the user intent and template files to generate a recommendation document
     * @param userIntent The user intent text file
     * @param template The Word document template
     * @return The processed document as a byte array
     * @throws IOException If there's an error processing the files
     */
    byte[] processDocument(MultipartFile userIntent, MultipartFile template) throws IOException;
}
