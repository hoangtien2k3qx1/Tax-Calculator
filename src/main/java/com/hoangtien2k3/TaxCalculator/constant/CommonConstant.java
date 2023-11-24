package com.hoangtien2k3.TaxCalculator.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Getter
@Accessors(fluent = true)
public enum CommonConstant {
    NON_TAXABLE_INCOME(11_000_000),
    DEDUCTION_PER_DEPENDANTS(4_400_000);

    private final double value;
}
