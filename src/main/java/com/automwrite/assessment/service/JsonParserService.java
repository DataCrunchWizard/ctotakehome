package com.automwrite.assessment.service;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;

import java.io.IOException;

public interface JsonParserService {
    /**
     * Load client data from the default client JSON file in resources
     * @return Parsed Client object
     * @throws IOException If file cannot be read or parsed
     */
    Client loadClientData() throws IOException;

    /**
     * Load organization data from the default organization JSON file in resources
     * @return Parsed Organisation object
     * @throws IOException If file cannot be read or parsed
     */
    Organisation loadOrganizationData() throws IOException;

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
