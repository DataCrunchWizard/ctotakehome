package com.automwrite.assessment.model.client.pension;

import lombok.Data;

@Data
public class MarketBasedPlan {
    private String planId;
    private String provider;
    private String planType;
    private double planValue;
    private InvestmentAllocation investments;
    private ProjectedValues projectedValues;
    private ContributionDetails contributionDetails;
}
