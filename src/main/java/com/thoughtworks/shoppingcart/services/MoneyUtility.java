package com.thoughtworks.shoppingcart.services;

import org.springframework.stereotype.Service;

public class MoneyUtility {
    private static final double SALES_TAX_RATE = 2.0;

    public static double getSalesTax(double price) {
        double salesTax = (price * SALES_TAX_RATE) / 100;
        return format(salesTax);
    }

    public static double format(double input) {
        return Double.parseDouble(String.format("%.2f", input));
    }
}
