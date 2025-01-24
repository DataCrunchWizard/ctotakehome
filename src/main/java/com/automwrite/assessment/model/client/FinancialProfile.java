package com.automwrite.assessment.model.client;

import lombok.Data;

@Data
public class FinancialProfile {
    private String riskTolerance;
    private String investmentHorizon;
    private double liquidAssets;
    private double totalNetWorth;
    private double monthlyExpenses;
    private OutstandingDebts outstandingDebts;
}
