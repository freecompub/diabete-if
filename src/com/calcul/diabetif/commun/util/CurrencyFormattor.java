package com.calcul.diabetif.commun.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

import com.calcul.diabetif.commun.constant.SingleAppConfig;

public class CurrencyFormattor {

    private static CurrencyFormattor instance;
    private DecimalFormat decimalFormat;

    public static CurrencyFormattor getInstance() {
        if (instance == null) {
            instance = new CurrencyFormattor();
        }
        return instance;
    }

    public CurrencyFormattor() {
        SingleAppConfig singleAppConfig = SingleAppConfig.getSingleAppConfig();

        decimalFormat = (DecimalFormat) NumberFormat.getCurrencyInstance();
        decimalFormat.setCurrency(Currency.getInstance(singleAppConfig.getCurrencySymbol()));

        decimalFormat.setMinimumFractionDigits(singleAppConfig.getMinimumFractionDigits());
        decimalFormat.setMaximumFractionDigits(singleAppConfig.getMaximumFractionDigits());

        String nagativePrefix = decimalFormat.getNegativePrefix();
        decimalFormat.setNegativePrefix(nagativePrefix.replace('(', '-'));
        decimalFormat.setNegativeSuffix("");
    }

    public String format(Number number) {
        return decimalFormat.format(number);
    }
}
