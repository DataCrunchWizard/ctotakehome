package com.automwrite.assessment.model.client;

import com.automwrite.assessment.model.client.pension.PensionPlans;
import lombok.Data;

@Data
public class ClientInfo {
    private String id;
    private PersonalDetails personalDetails;
    private Address address;
    private EmploymentStatus employmentStatus;
    private FinancialProfile financialProfile;
    private PensionPlans pensionPlans;
    private String lastUpdated;
    private AdvisorDetails advisorDetails;
}
