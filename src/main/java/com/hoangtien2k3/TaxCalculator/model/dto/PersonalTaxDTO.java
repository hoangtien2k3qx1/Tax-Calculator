package com.hoangtien2k3.TaxCalculator.model.dto;

import lombok.Builder;
import lombok.With;

import java.util.Map;

@Builder
@With
public record PersonalTaxDTO(
        double pretaxSalary,
        double taxableIncome,
        double taxAmount,
        Map<String, Double> progressiveTax,
        double netIncome) {
}
