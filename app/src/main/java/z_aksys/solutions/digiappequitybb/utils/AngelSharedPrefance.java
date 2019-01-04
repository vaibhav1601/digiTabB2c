package z_aksys.solutions.digiappequitybb.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AngelSharedPrefance {

    public static final String MyPREFERENCES = "AWCache";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    Context context;

    public AngelSharedPrefance(Context cntxt) {
        sharedpreferences = cntxt.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        this.context = cntxt;
    }

    public void clearAllPreferences() {
        editor.clear();
        editor.commit();
    }

    public String getCacheTime() {
        String retValue = sharedpreferences.getString("CacheDate", "");
        return retValue;
    }

    public void setCacheTime(String strKey, String value) {
        editor.putString(strKey, value);
        editor.commit();
    }

    public void setOptimizeAPI(String strKey, String value) {
        editor.putString(strKey, value);
        editor.commit();
    }

    public String getOptimizeAPI(String strKey) {
        return sharedpreferences.getString(strKey, "");
    }


    public void setHelathAPI(String strKey, String value) {
        editor.putString(strKey, value);
        editor.commit();
    }

    public String getHealthAPI(String strKey) {
        return sharedpreferences.getString(strKey, "");
    }

    public Boolean getRatingsCancelled() {
        Boolean ratingCancelled = sharedpreferences.getBoolean("boolCancelled", false);
        return ratingCancelled;
    }

    public void setRatingsCancelled(String boolCancelled, Boolean value) {
        editor.putBoolean(boolCancelled, value);
        editor.commit();
    }

    public Boolean getRatingsCancelledForever() {
        Boolean ratingCancelled = sharedpreferences.getBoolean("boolCancelledForever", false);
        return ratingCancelled;
    }

    public void setRatingsCancelledForever(String boolCancelledForever, Boolean value) {
        editor.putBoolean(boolCancelledForever, value);
        editor.commit();
    }
}
