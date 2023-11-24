package com.hoangtien2k3.TaxCalculator.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

import com.hoangtien2k3.TaxCalculator.model.dto.InsuranceDTO;
import com.hoangtien2k3.TaxCalculator.model.dto.PersonalTaxDTO;
import com.hoangtien2k3.TaxCalculator.model.dto.TaxDetailDTO;
import com.hoangtien2k3.TaxCalculator.model.record.Insurance;
import com.hoangtien2k3.TaxCalculator.model.record.PersonalTax;
import com.hoangtien2k3.TaxCalculator.model.record.TaxDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaxMapper {

    TaxMapper INSTANCE = Mappers.getMapper(TaxMapper.class);

    // comvert double to BigDecimal (làm tròn đến 2 chữ số thập phân)
    static BigDecimal toBigDecimal(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.CEILING);
    }

    static Map<String, BigDecimal> toBigDecimalMap(Map<String, Double> values) {
        return values.entrySet().stream()
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey, entry -> toBigDecimal(entry.getValue())
                        )
                );
    }

    Insurance toInsurance(InsuranceDTO insuranceDTO);

    PersonalTax toPersonalTax(PersonalTaxDTO personalTaxDTO);

    TaxDetail toTaxDetail(TaxDetailDTO taxDetailDTO);
}
