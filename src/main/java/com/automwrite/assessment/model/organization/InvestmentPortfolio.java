package com.automwrite.assessment.model.organization;

import lombok.Data;

@Data
public class InvestmentPortfolio {
    private String name;
    private String description;
    private int riskLevel;
    private TargetAllocation targetAllocation;
    private Fee annualManagementCharge;
    private double minimumInvestment;

    @Data
    public static class TargetAllocation {
        private double bonds;
        private double equities;
        private double cash;
        private Double alternatives; // Optional
    }
}
