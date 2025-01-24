package com.automwrite.assessment.model.client.pension;

import lombok.Data;

@Data
public class GuaranteedBenefits {
    private double monthlyPension;
    private String guaranteedPeriod;
    private double survivalBenefit;
    private InflationProtection inflationProtection;
}
