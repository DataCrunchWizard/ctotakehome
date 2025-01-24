package com.automwrite.assessment.model.organization;

import lombok.Data;

@Data
public class OrganizationInfo {
    private OrganizationDetails organizationDetails;
    private Platforms platforms;
    private ServiceProposition serviceProposition;
    private Fees fees;

    @Data
    public static class Fees {
        private Fee initialAdviceFee;
        private Fee ongoingAdviceFee;
        private Fee platformFee;
    }
}
