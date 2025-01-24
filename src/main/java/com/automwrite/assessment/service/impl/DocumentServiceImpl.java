package com.automwrite.assessment.service.impl;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import com.automwrite.assessment.service.DocumentService;
import com.automwrite.assessment.service.JsonParserService;
import com.automwrite.assessment.service.LlmService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final JsonParserService jsonParserService;
    private final LlmService llmService;

    @Value("${client.json.path}")
    private String clientJsonPath;

    @Value("${organisation.json.path}")
    private String organisationJsonPath;

    @Override
    public byte[] processDocument(MultipartFile userIntent, MultipartFile template) throws IOException {
        // Parse JSON files
        Client client = jsonParserService.parseClientJson(clientJsonPath);
        Organisation organisation = jsonParserService.parseOrganisationJson(organisationJsonPath);

        // Get user intent text
        String intentText = new String(userIntent.getBytes(), StandardCharsets.UTF_8);

        // Process intent with LLM
        String recommendationText = llmService.processUserIntent(intentText, client, organisation);

        // Load template
        XWPFDocument doc = new XWPFDocument(template.getInputStream());

        // Process template placeholders
        processTemplate(doc, recommendationText, client, organisation);

        // Convert to bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        doc.write(outputStream);
        doc.close();

        return outputStream.toByteArray();
    }

    private void processTemplate(XWPFDocument doc, String recommendationText, Client client, Organisation organisation) {
        for (XWPFParagraph paragraph : doc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null) {
                    // Replace placeholders with actual content
                    text = replacePlaceholders(text, recommendationText, client, organisation);
                    run.setText(text, 0);
                }
            }
        }
    }

    private String replacePlaceholders(String text, String recommendationText, Client client, Organisation organisation) {
        if (text.contains("{{date}}")) {
            text = text.replace("{{date}}", LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
        }
        if (text.contains("{{client_name}}")) {
            text = text.replace("{{client_name}}", 
                client.getClientInfo().getPersonalDetails().getFirstName() + " " + 
                client.getClientInfo().getPersonalDetails().getLastName());
        }
        if (text.contains("{{client_address}}")) {
            text = text.replace("{{client_address}}", formatAddress(client));
        }
        if (text.contains("{{advisor_name}}")) {
            text = text.replace("{{advisor_name}}", client.getClientInfo().getAdvisorDetails().getName());
        }
        if (text.contains("{{advisor_email}}")) {
            text = text.replace("{{advisor_email}}", client.getClientInfo().getAdvisorDetails().getEmail());
        }
        if (text.contains("{{advisor_phone}}")) {
            text = text.replace("{{advisor_phone}}", client.getClientInfo().getAdvisorDetails().getPhone());
        }
        if (text.contains("{{recommendation}}")) {
            text = text.replace("{{recommendation}}", recommendationText);
        }
        if (text.contains("{{organisation_name}}")) {
            text = text.replace("{{organisation_name}}", 
                organisation.getOrganizationInfo().getOrganizationDetails().getOrganizationName());
        }
        return text;
    }

    private String formatAddress(Client client) {
        var address = client.getClientInfo().getAddress();
        return String.format("%s\n%s\n%s %s\n%s",
            address.getStreet(),
            address.getCity(),
            address.getState(),
            address.getZipCode(),
            address.getCountry()
        );
    }
}
