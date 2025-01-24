package com.automwrite.assessment.service;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class DocumentServiceTest {

    @Autowired
    private DocumentService documentService;

    @MockBean
    private JsonParserService jsonParserService;

    @MockBean
    private LlmService llmService;

    @Test
    void processDocument_ShouldReturnProcessedDocument() throws IOException {
        // Given
        String userIntentText = "I want to transfer my pension from Aviva to Fidelity.";
        MockMultipartFile userIntent = new MockMultipartFile(
            "userIntent",
            "userintent.txt",
            "text/plain",
            userIntentText.getBytes(StandardCharsets.UTF_8)
        );

        MockMultipartFile template = new MockMultipartFile(
            "template",
            "template.docx",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            getClass().getResourceAsStream("/templates/recommendation-template.docx")
        );

        when(jsonParserService.parseClientJson(anyString())).thenReturn(new Client());
        when(jsonParserService.parseOrganisationJson(anyString())).thenReturn(new Organisation());
        when(llmService.processUserIntent(anyString(), any(), any())).thenReturn("Test recommendation");

        // When
        byte[] result = documentService.processDocument(userIntent, template);

        // Then
        assertNotNull(result);
    }
}
