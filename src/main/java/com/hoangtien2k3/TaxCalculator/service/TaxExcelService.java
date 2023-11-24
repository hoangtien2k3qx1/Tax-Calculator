package com.hoangtien2k3.TaxCalculator.service;

import com.hoangtien2k3.TaxCalculator.model.dto.TaxDetailDTO;
import com.hoangtien2k3.TaxCalculator.model.record.TaxDetail;
import com.hoangtien2k3.TaxCalculator.utils.CellBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.util.concurrent.atomic.AtomicInteger;

import static com.hoangtien2k3.TaxCalculator.constant.CommonConstant.DEDUCTION_PER_DEPENDANTS;
import static com.hoangtien2k3.TaxCalculator.constant.CommonConstant.NON_TAXABLE_INCOME;


@Service
@RequiredArgsConstructor
public class TaxExcelService {

    private static final int VALUE_COLUMN_INDEX = 3;

    private final TaxService taxService;

    public void exportExcel(
            double totalSalary,
            double basicSalary,
            int numberOfDependants,
            HttpServletResponse httpServletResponse) {
        exportExcel(
                taxService.calculate(
                        TaxDetailDTO.builder()
                                .totalSalary(totalSalary)
                                .basicSalary(basicSalary)
                                .numberOfDependants(numberOfDependants)
                                .build()),
                httpServletResponse);
    }

    @SneakyThrows
    private void exportExcel(TaxDetail taxDetail, HttpServletResponse httpServletResponse) {
        try (var inputStream = new FileInputStream(ResourceUtils.getFile("classpath:template.xlsx"));
             var workbook = new XSSFWorkbook(inputStream);
             var cellBuilder = CellBuilder.init(workbook, 0)) {

            // Row count
            var count = new AtomicInteger(1);

            // Tổng thu nhập
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(taxDetail.totalSalary().doubleValue());

            // Lương cơ bản
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(taxDetail.basicSalary().doubleValue());

            // Số người phụ thuộc
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(taxDetail.numberOfDependants());

            // Giảm trừ cho người phụ thuộc
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(DEDUCTION_PER_DEPENDANTS.value() * taxDetail.numberOfDependants());

            // Thu nhập miễn thuế
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(NON_TAXABLE_INCOME.value());

            count.getAndIncrement();
            var insurance = taxDetail.insurance();

            // Tổng tiền bảo hiểm
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(insurance.totalInsurance().doubleValue());

            // BHYT
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(insurance.healthInsurance().doubleValue());

            // BHXH
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(insurance.socialInsurance().doubleValue());

            // BH thất nghiệp
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(insurance.unemploymentInsurance().doubleValue());

            count.getAndIncrement();
            var personalTax = taxDetail.personalTax();

            // Thuế TNCN
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(personalTax.taxAmount().doubleValue());

            // Thu nhập trước thuế
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(personalTax.pretaxSalary().doubleValue());

            // Thu nhập tính thuế
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(personalTax.taxableIncome().doubleValue());

            count.getAndIncrement();

            // Thực lĩnh
            cellBuilder
                    .newCell(count.getAndIncrement(), VALUE_COLUMN_INDEX)
                    .setCellValue(personalTax.netIncome().doubleValue());

            workbook.write(httpServletResponse.getOutputStream());
        }
    }
}
