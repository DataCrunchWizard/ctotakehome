package com.automwrite.assessment.model.organization;

import lombok.Data;

@Data
public class StructuredCharge {
    private String startAmount;
    private String endAmount; // Optional for highest tier
    private String fee;
}
