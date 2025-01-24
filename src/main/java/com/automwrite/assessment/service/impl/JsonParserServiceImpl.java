package com.automwrite.assessment.service.impl;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import com.automwrite.assessment.service.JsonParserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class JsonParserServiceImpl implements JsonParserService {
    private final Gson gson;

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
