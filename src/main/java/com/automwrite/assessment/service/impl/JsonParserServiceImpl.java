package com.automwrite.assessment.service.impl;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import com.automwrite.assessment.service.JsonParserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class JsonParserServiceImpl implements JsonParserService {
    private final Gson gson;
    private static final String CLIENT_JSON_PATH = "clientinfo/client.json";
    private static final String ORGANIZATION_JSON_PATH = "orginfo/org.json";

    @Override
    public Client loadClientData() throws IOException {
        return parseClientJson(new ClassPathResource(CLIENT_JSON_PATH).getFile().getPath());
    }

    @Override
    public Organisation loadOrganizationData() throws IOException {
        return parseOrganisationJson(new ClassPathResource(ORGANIZATION_JSON_PATH).getFile().getPath());
    }


    @Override
    public Client parseClientJson(String filePath) throws IOException {
        String jsonContent = Files.readString(Path.of(filePath));
        return gson.fromJson(jsonContent, Client.class);
    }

    @Override
    public Organisation parseOrganisationJson(String filePath) throws IOException {
        String jsonContent = Files.readString(Path.of(filePath));
        return gson.fromJson(jsonContent, Organisation.class);
    }
}
