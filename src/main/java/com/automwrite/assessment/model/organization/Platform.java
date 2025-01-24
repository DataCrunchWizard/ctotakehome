package com.automwrite.assessment.model.organization;

import lombok.Data;
import java.util.List;

@Data
public class Platform {
    private String name;
    private String description;
    private Fees fees;
    private List<String> features;

    @Data
    public static class Fees {
        private List<StructuredCharge> structuredCharge;
    }
}
