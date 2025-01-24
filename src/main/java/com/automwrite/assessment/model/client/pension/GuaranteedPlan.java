package com.automwrite.assessment.model.client.pension;

import lombok.Data;

@Data
public class GuaranteedPlan {
    private String planId;
    private String provider;
    private String planType;
    private double planValue;
    private GuaranteedBenefits guaranteedBenefits;
    private String vestingStatus;
    private String normalRetirementDate;
}
