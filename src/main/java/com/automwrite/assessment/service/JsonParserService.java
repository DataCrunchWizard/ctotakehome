package com.automwrite.assessment.service;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;

import java.io.IOException;

public interface JsonParserService {
    /**
     * Parse client JSON file into Client object
     * @param filePath Path to the client JSON file
     * @return Parsed Client object
     * @throws IOException If file cannot be read or parsed
     */
    Client parseClientJson(String filePath) throws IOException;

    /**
     * Parse organisation JSON file into Organisation object
     * @param filePath Path to the organisation JSON file
     * @return Parsed Organisation object
     * @throws IOException If file cannot be read or parsed
     */
    Organisation parseOrganisationJson(String filePath) throws IOException;
}
