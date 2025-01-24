package com.automwrite.assessment.model.client;

import lombok.Data;

@Data
public class EmploymentStatus {
    private String status;
    private String employer;
    private double annualIncome;
    private int yearsWithEmployer;
}
