package com.automwrite.assessment.service.impl;

import com.automwrite.assessment.model.Client;
import com.automwrite.assessment.model.Organisation;
import com.automwrite.assessment.service.LlmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LlmServiceImpl implements LlmService {
    
    @Override
    public String processUserIntent(String userIntent, Client client, Organisation organisation) {
        String prompt = buildPrompt(userIntent, client, organisation);
        // For assessment purposes, return a mock recommendation
        return generateMockRecommendation(client, organisation);
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

    private String generateMockRecommendation(Client client, Organisation organisation) {
        String clientName = client.getClientInfo().getPersonalDetails().getFirstName() + " " + 
                           client.getClientInfo().getPersonalDetails().getLastName();
        String riskTolerance = client.getClientInfo().getFinancialProfile().getRiskTolerance();
        String platform = organisation.getOrganizationInfo().getPlatforms().getItems().get(0).getName();
        
        return String.format("""
            Dear %s,
            
            Thank you for considering our investment advisory services. Based on our analysis of your financial situation and goals, we have prepared the following recommendation.
            
            Current Situation:
            - Risk Tolerance: %s
            - Current Investment Strategy: Requires review
            
            Recommendation:
            We recommend transitioning your investments to our %s platform, which offers:
            - Comprehensive investment options
            - Competitive fee structure
            - Advanced portfolio management tools
            
            Next Steps:
            1. Review this recommendation
            2. Schedule a follow-up meeting
            3. Complete necessary paperwork
            4. Begin portfolio transition
            
            Please let us know if you have any questions about this recommendation.
            
            Best regards,
            Your Financial Advisory Team
            """, 
            clientName,
            riskTolerance,
            platform
        );
    }
}
