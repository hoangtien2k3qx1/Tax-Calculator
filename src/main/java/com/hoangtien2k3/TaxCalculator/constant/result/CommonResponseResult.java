package com.hoangtien2k3.TaxCalculator.constant.result;

import com.hoangtien2k3.TaxCalculator.utils.BaseErrorResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum CommonResponseResult implements BaseErrorResult {
  OK("000", ""),
  UNKNOWN_ERROR("500", "Unknown error, please refer to log for more detail");

  private final String errorCode;
  private final String message;
}
