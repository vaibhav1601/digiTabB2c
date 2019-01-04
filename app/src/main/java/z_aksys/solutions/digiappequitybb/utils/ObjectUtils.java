package z_aksys.solutions.digiappequitybb.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.R;

public class ObjectUtils {

    private static final String TAG = "ObjectUtils";

    public static double getLFEarnedMoney(double bankBalance) {
        /**
         * This code is calcualting interest on bank balance.
         * 0.08 % rate of interest applied
         * Please check before remove the code
         */
        return (bankBalance * 0.08);
    }

    public static boolean indexExists(final HashMap map, final int index) {
        return index >= 0 && index < map.size();
    }

    public static boolean indexExists(final Map map, final int index) {
        return index >= 0 && index < map.size();
    }

    public static boolean indexExists(final List list, final int index) {
        return index >= 0 && index < list.size();
    }

    public static boolean arrayIndexExist(String[] data, int index) {
        try {
            String s = data[index];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static String parseString(String number) {
        return number.indexOf(".") < 0 ? number : number.replaceAll("0*$", "").replaceAll("\\.$", "");
    }

    public static boolean isNotNull(Object o) {
        if (o != null) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String s) {
        if (s != null && s.trim().length() > 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(List s) {
        if (s != null && s.size() > 0) {
            return false;
        }
        return true;
    }

    public static int getIntFromString(String number) {

        if (number != null) {
            int numberInt = 0;

            number = number.replaceAll("\\s+", "");
            number = number.replaceAll(",", "");
            number = number.replaceAll(App.getContext().getResources().getString(R.string.Rs_symbol), "");
            try {
                numberInt = Integer.parseInt(number);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return numberInt;
        } else {
            return 0;
        }
    }

    public static long getLongFromStringWithDecimal(String number) {

        if (number != null) {
            long numberInt = 0;

            number = number.replaceAll("\\s+", "");
            number = number.replaceAll(",", "");
            number = number.replaceAll(App.getContext().getResources().getString(R.string.Rs_symbol), "");
            try {
                double numberDouble = Double.parseDouble(number);
                numberInt = (long) numberDouble;
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            return numberInt;
        } else {
            return 0;
        }
    }

    public static int getIntFromStringWithDefaultValue(String number, int defaultValue) {

        if (number != null) {
            int value = getIntFromString(number);
            if (value == 0) {
                value = defaultValue;
            }
            return value;
        } else {
            return defaultValue;
        }
    }

    public static long getLongFromString(String number) {

        if (number != null) {
            long numberInt = 0;

            number = number.replaceAll("\\s+", "");
            number = number.replaceAll(",", "");
            number = number.replaceAll(App.getContext().getResources().getString(R.string.Rs_symbol), "");
            try {
                numberInt = Long.parseLong(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            return numberInt;
        } else {
            return 0;
        }
    }

    public static long getLongFromStringWithDefaultValue(String number, long defaultValue) {

        if (number != null) {
            long value = getLongFromString(number);
            if (value == 0) {
                value = defaultValue;
            }
            return value;
        } else {
            return defaultValue;
        }
    }

    public static float getFloatFromString(String number) {

        if (number != null) {
            float numberFloat = 0;

            number = number.replaceAll("\\s+", "");
            number = number.replaceAll(",", "");
            number = number.replaceAll(App.getContext().getResources().getString(R.string.Rs_symbol), "");
            try {
                numberFloat = Float.parseFloat(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            return numberFloat;
        } else {
            return 0;
        }
    }

    public static double getDoubleFromString(String number) {
        if (!TextUtils.isEmpty(number)) {
            double numberDouble = 0;
            number = number.replaceAll("\\s+", "");
            number = number.replaceAll(",", "");
            number = number.replaceAll(App.getContext().getResources().getString(R.string.Rs_symbol), "");

            try {
                numberDouble = Double.parseDouble(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }

            return numberDouble;
        } else {
            return 0;
        }
    }

    public static Double getDefinedDoubleFromString(String number) {
        if (!TextUtils.isEmpty(number)) {
            double numberDouble = 0;
            number = number.replaceAll("\\s+", "");
            number = number.replaceAll(",", "");
            number = number.replaceAll(App.getContext().getResources().getString(R.string.Rs_symbol), "");

            try {
                numberDouble = Double.parseDouble(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }

            return new Double(numberDouble);
        } else {
            return new Double(0);
        }
    }


    public static float getFloatFromStringWithDefaultValue(String number, float defaultValue) {

        if (number != null) {
            float numberInt = getFloatFromString(number);
            if (numberInt == 0) {
                numberInt = defaultValue;
            }
            return numberInt;
        } else {
            return defaultValue;
        }
    }

    public static String getStringFromInt(int number) {

        if (number != 0) {
            String numberString = "0";
            try {
                numberString = Integer.toString(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            return numberString;
        } else {
            return "0";
        }
    }

    public static <T> List<List<T>> getBatchesFromList(List<T> largeList, int chunkSize) {
        List<List<T>> chunkList = new ArrayList<>();
        for (int i = 0; i < largeList.size(); i += chunkSize) {
            chunkList.add(largeList.subList(i, i + chunkSize >= largeList.size() ? largeList.size() : i + chunkSize));
        }
        return chunkList;
    }


    public static int getMiddleElementIndex(List list) {

        if (isNotNull(list)) {
            int value = list.size() % 2;

            int middleIndex = list.size() / 2;

            int evenMid = middleIndex - 1;

            if (value == 0) {
                return evenMid;
            } else {
                return middleIndex;
            }
        }
        return 0;
    }

    public static String indianCurrencyFormatWithoutRuppes(String strValue) {
        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) format).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) format).setDecimalFormatSymbols(decimalFormatSymbols);
        String formattedAmount = format.format(new BigDecimal(strValue));
        return formattedAmount;
    }


//    public static String indianCurrencyFormatWithRuppesWithDecimal(String strValue){
//        Format format = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
//        String formattedAmount = format.format(new BigDecimal(strValue));
//        formattedAmount = AppContext.getInstance().getResources().getString(R.string.Rs_symbol) + " " + formattedAmount.substring(3);
//        return formattedAmount;
//    }


    public static float convertFloatWithDecimal(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static String get0DecimalString(String value) {
        try {
            double doubleValue = ObjectUtils.getDoubleFromString(value);
            return String.format("%d", (long) doubleValue);
        } catch (Exception e) {
            return value;
        }
    }

    public static String get2DecimalString(String value) {
        try {
            String toConvert = String.format("%.2f", (new BigDecimal(value)));
            return toConvert;
        } catch (Exception e) {
            return value;
        }
    }

    public static String get1DecimalString(String value) {
        try {
            String toConvert = String.format("%.1f", (new BigDecimal(value)));
            return toConvert;
        } catch (Exception e) {
            return value;
        }
    }

    public static String get2DecimalStringFromFloat(float fvalue) {
        String value = String.valueOf(fvalue);
        try {
            String toConvert = String.format("%.2f", (new BigDecimal(value)));
            return toConvert;
        } catch (Exception e) {
            return value;
        }
    }

    public static String get4DecimalString(String value) {
        try {
            String toConvert = String.format("%.4f", (new BigDecimal(value)));
            return toConvert;
        } catch (Exception e) {
            return value;
        }
    }


    public static boolean checkTickValue(String strPrice, String strTickSize) {
        if (!TextUtils.isEmpty(strTickSize) && strTickSize.contains(".")) {
            double doubleTick;
            int intLen = strTickSize.length() - 2;
            if (intLen < 0) {
                intLen = 1;
            }
            double doublePower = Math.pow(10, intLen);
            doubleTick = getFloatFromString(strTickSize) * doublePower;
            double dblPrice = getDoubleFromString(strPrice);
            dblPrice = dblPrice * doublePower;
            String result = String.format("%.2f", doubleTick);
            doubleTick = ObjectUtils.getDoubleFromString(result);

            result = String.format("%.2f", dblPrice);
            dblPrice = ObjectUtils.getDoubleFromString(result);

            if (dblPrice % doubleTick != 0) {
                return false;
            }
        }

        return true;
    }

    public static String dateFormatForQuote(String strDate) {
        try {
            String[] tempS = strDate.split(" ");
            String str1 = tempS[0];

            StringBuilder str2 = new StringBuilder(tempS[1]);
            str2.insert(2, ':');
            str2.insert(5, ':');

            tempS = tempS[0].split("-");
            str1 = tempS[2] + "-" + tempS[1] + "-" + tempS[0];
            return str1 + " " + str2;
        } catch (Exception e) {
            e.fillInStackTrace();
            return strDate;
        }
    }


    public static String getStringFromFloat(float number) {

        if (number != 0) {
            String numberString = "0";
            try {
                numberString = Float.toString(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            return numberString;
        } else {
            return "0";
        }
    }

    public static String getStringFromDouble(double number) {

        if (number != 0) {
            String numberString = "0";
            try {
                numberString = Double.toString(number);
            } catch (NumberFormatException e) {
                e.fillInStackTrace();
            }
            return numberString;
        } else {
            return "0";
        }
    }


}
