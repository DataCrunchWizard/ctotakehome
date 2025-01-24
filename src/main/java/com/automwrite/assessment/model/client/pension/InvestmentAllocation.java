package com.automwrite.assessment.model.client.pension;

import lombok.Data;

@Data
public class InvestmentAllocation {
    private double equities;
    private double bonds;
    private double cash;
    private Double alternatives; // Optional field
}
