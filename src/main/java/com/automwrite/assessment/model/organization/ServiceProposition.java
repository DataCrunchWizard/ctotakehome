package com.automwrite.assessment.model.organization;

import lombok.Data;
import java.util.List;

@Data
public class ServiceProposition {
    private String adviceType;
    private List<InvestmentPortfolio> investmentPortfolios;
}
