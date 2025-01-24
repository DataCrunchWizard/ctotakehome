package com.automwrite.assessment.model.client.pension;

import lombok.Data;

@Data
public class PensionPlans {
    private GuaranteedPlan guaranteedPlan;
    private MarketBasedPlan marketBasedPlan;
}
