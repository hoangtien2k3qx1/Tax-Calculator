package com.hoangtien2k3.TaxCalculator.utils;

import java.io.Serializable;

public interface BaseErrorResult extends Serializable {

    String errorCode();

    String message();
}

