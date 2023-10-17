package com.example.injection.tax;

import org.springframework.stereotype.Component;

@Component

public class DeveloperTax implements Taxable {

    @Override
    public double getSimpleTaxRate() {
        return 0.6;
    }

    @Override
    public double getMiddleTaxRate() {
        return 0.9;
    }

    @Override
    public double getUpperTaxRate() {
        return 0.12;
    }
}