package com.automwrite.assessment.service.impl;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import com.automwrite.assessment.service.LlmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LlmServiceImpl implements LlmService {
    
    @Value("${claude.api.key}")
    private String apiKey;
    
    @Value("${claude.api.url}")
    private String apiUrl;
    
    private final WebClient.Builder webClientBuilder;

    @Override
    public String processUserIntent(String userIntent, Client client, Organisation organisation) {
        WebClient webClient = webClientBuilder
            .baseUrl(apiUrl)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        String prompt = buildPrompt(userIntent, client, organisation);
        
        return webClient.post()
            .bodyValue(createRequestBody(prompt))
            .retrieve()
            .bodyToMono(String.class)
            .map(this::extractRecommendationText)
            .block();
    }

    private String buildPrompt(String userIntent, Client client, Organisation organisation) {
        return String.format("""
            As a financial advisor, generate a professional recommendation letter based on the following:
            
            User Intent: %s
            
            Client Details:
            - Name: %s %s
            - Current Provider: %s
            - Risk Tolerance: %s
            - Investment Horizon: %s
            
            Target Platform Details:
            %s
            
            Please provide a detailed recommendation that:
            1. Acknowledges the client's request
            2. Evaluates their current situation
            3. Provides a clear recommendation with justification
            4. Includes relevant fee information
            5. Outlines next steps
            
            Use a professional tone and format suitable for a formal recommendation letter.
            """,
            userIntent,
            client.getClientInfo().getPersonalDetails().getFirstName(),
            client.getClientInfo().getPersonalDetails().getLastName(),
            client.getClientInfo().getPensionPlans().getMarketBasedPlan() != null ? 
                client.getClientInfo().getPensionPlans().getMarketBasedPlan().getProvider() :
                client.getClientInfo().getPensionPlans().getGuaranteedPlan().getProvider(),
            client.getClientInfo().getFinancialProfile().getRiskTolerance(),
            client.getClientInfo().getFinancialProfile().getInvestmentHorizon(),
            extractPlatformDetails(organisation)
        );
    }

    private String extractPlatformDetails(Organisation organisation) {
        StringBuilder details = new StringBuilder();
        organisation.getOrganizationInfo().getPlatforms().getItems().forEach(platform -> {
            details.append("Platform: ").append(platform.getName()).append("\n");
            details.append("Description: ").append(platform.getDescription()).append("\n");
            details.append("Features:\n");
            platform.getFeatures().forEach(feature -> details.append("- ").append(feature).append("\n"));
            details.append("\n");
        });
        return details.toString();
    }

    private Object createRequestBody(String prompt) {
        // Create the appropriate request body structure for Claude API
        // This would need to be adjusted based on the actual Claude API requirements
        return new Object(); // Placeholder
    }

    private String extractRecommendationText(String response) {
        // Extract the recommendation text from Claude API response
        // This would need to be adjusted based on the actual Claude API response structure
        return response; // Placeholder
    }
}
