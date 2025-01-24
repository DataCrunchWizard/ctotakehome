package com.automwrite.assessment.model.organization;

import com.automwrite.assessment.model.client.Address;
import lombok.Data;

@Data
public class OrganizationDetails {
    private String organizationName;
    private String websiteUrl;
    private String email;
    private String phoneNumber;
    private Address address;
}
