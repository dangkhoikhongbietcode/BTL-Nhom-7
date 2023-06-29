package com.example.btl_nhom_7.until;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

public class Utils {
    public static String unitPrase(int priceOld) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String price = decimalFormat.format(priceOld);
        String priceNew = price.replace(",", ".");
        Locale vn = new Locale("vi", "VN");
        Currency vnd = Currency.getInstance(vn);
        String priceVn = priceNew ;
        return priceVn;
    }

}
