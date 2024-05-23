package com.example.geektrust.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundValuesGenerator {
    public static double roundValue(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
