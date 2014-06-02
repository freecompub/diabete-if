package com.calcul.diabetif.commun.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class NumberUtil {

    public static final NumberFormat decimalFormat = NumberFormat.getInstance(Locale.US);

	private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat();
	
	static {
		DECIMAL_FORMAT.setGroupingSize(0);
		DECIMAL_FORMAT.setMaximumFractionDigits(0);
	}
    private NumberUtil() {
    }
    
	public static String toString(Number number, int maxFractionDigits, int minFractionDigits, String currencySymbol) {
		DecimalFormat df = new DecimalFormat();
		df.setGroupingSize(0);
		df.setMaximumFractionDigits(maxFractionDigits);
		df.setMinimumFractionDigits(minFractionDigits);

		return currencySymbol + df.format(number);
	}

    public static String format(Number number) {
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        return decimalFormat.format(number);
    }

    public static boolean isInteger(String str) {
        return Pattern.matches("\\d+", str);
    }
    
    public static String toDistanceFormat(BigDecimal distance) {
		StringBuilder result = new StringBuilder();
		if(distance != null) {
			final BigDecimal kilometer = BigDecimal.valueOf(1000);

			if(distance.compareTo(kilometer) > 0) {
				result.append(DECIMAL_FORMAT.format(distance.divide(kilometer, 0, RoundingMode.DOWN)));
				result.append(" km");
			} else {
				result.append(DECIMAL_FORMAT.format(distance));
				result.append(" m");
			}
		}
		return result.toString();
	}
}
