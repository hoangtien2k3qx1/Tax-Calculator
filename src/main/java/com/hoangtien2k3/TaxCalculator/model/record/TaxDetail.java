package com.hoangtien2k3.TaxCalculator.model.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;

@Builder
@With
public record TaxDetail(
        @JsonProperty("Tổng thu nhập") BigDecimal totalSalary,
        @JsonProperty("Lương cơ bản") BigDecimal basicSalary,
        @JsonProperty("Số người phụ thuộc") int numberOfDependants,
        @JsonProperty("Bảo hiểm") Insurance insurance,
        @JsonProperty("Thuế TNCN") PersonalTax personalTax) {
}
