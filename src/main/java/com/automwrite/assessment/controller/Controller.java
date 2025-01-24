package com.automwrite.assessment.controller;

import com.automwrite.assessment.service.JsonParserService;
import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import com.automwrite.assessment.service.LlmService;
import com.automwrite.assessment.service.util.DocumentService;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.automwrite.assessment.service.util.FileParserService.parseTxtFile;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class Controller {

    private final LlmService llmService;
    private final JsonParserService jsonParserService;
    private final DocumentService documentService;

    /**
     * Processes the uploaded .txt file to extract user intent, utilises JSON data and an LLM service
     * to process the intent, and generates a .docx file using a predefined template.
     *
     * @param file File to extract the user intent from
     * @return A response indicating that the processing has completed
     * @throws IOException If an error occurs while reading the file or processing the document
     */
    @PostMapping("/user-request")
    public ResponseEntity<String> handleUserRequest(@RequestParam("file") MultipartFile file) throws IOException {
        // Parse the .txt file to extract the user intent
        String userIntent = parseTxtFile(file);

        // Fetch and parse the JSON data
        Client client = jsonParserService.loadClientData();
        Organisation org = jsonParserService.loadOrganizationData();

        // Process the user intent using JSON data and the LLM service
        String processedContent = llmService.processUserIntent(userIntent, client, org);

        // Load the template document
        XWPFDocument templateDocument = documentService.loadTemplate();

        // Insert the processed content into the template
        documentService.insertContent(templateDocument, processedContent);

        // Save the modified document to a new .docx file
        documentService.saveDocument(templateDocument);

        // Return a response indicating successful processing
        return ResponseEntity.ok("User request processed, recommendation created.");
    }
}
