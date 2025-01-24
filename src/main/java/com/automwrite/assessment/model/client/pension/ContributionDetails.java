package com.automwrite.assessment.model.client.pension;

import lombok.Data;

@Data
public class ContributionDetails {
    private double employeeContribution;
    private double employerMatch;
    private double annualTotal;
}
