package com.hoangtien2k3.TaxCalculator.model.dto;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record TaxDetailDTO(
        double totalSalary,
        double basicSalary,
        int numberOfDependants,
        InsuranceDTO insurance,
        PersonalTaxDTO personalTax) {
}
