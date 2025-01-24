package com.automwrite.assessment.service;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;

public interface LlmService {
    /**
     * Process user intent using LLM to generate appropriate recommendation text
     * @param userIntent Raw user intent text
     * @param client Client information
     * @param organisation Organisation information
     * @return Processed recommendation text
     */
    String processUserIntent(String userIntent, Client client, Organisation organisation);
}
