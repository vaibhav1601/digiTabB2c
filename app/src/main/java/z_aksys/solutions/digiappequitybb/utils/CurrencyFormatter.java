package z_aksys.solutions.digiappequitybb.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {

    public static String format(long currency) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        return currencyFormat.format(currency);
    }
}
