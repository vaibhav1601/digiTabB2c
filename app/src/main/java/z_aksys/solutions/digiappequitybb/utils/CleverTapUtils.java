package z_aksys.solutions.digiappequitybb.utils;

import android.content.Context;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;

import java.util.HashMap;

public class CleverTapUtils {

    private static CleverTapUtils instance;
    private Context context;
    private CleverTapAPI ct;

    private CleverTapUtils(Context context) {
        this.context = context;
    }

    public static CleverTapUtils getInstance(Context context) {
        if (instance == null) {
            instance = new CleverTapUtils(context);
        }

        return instance;
    }

    public boolean initialize() {

        try {
            ct = CleverTapAPI.getInstance(context);
            return true;
        } catch (CleverTapMetaDataNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (CleverTapPermissionsNotSatisfied e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Push user details
     * TODO: Add user object as param
     *
     * @return
     */
    public boolean pushUser() {
        HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

        //Update pre-defined profile properties
        profileUpdate.put("Name", "Jack Montana");
        profileUpdate.put("Email", "jack@gmail.com");

        //Update custom profile properties
        profileUpdate.put("Plan Type", "Silver");
        profileUpdate.put("Favorite Food", "Pizza");

        if (ct != null) {
            ct.profile.push(profileUpdate);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Log custom event
     *
     * @param eventName Event name
     * @return is event pushed
     */
    public boolean logCustomEvent(String eventName, HashMap<String, Object> eventPropperties) {
        if (ct != null && eventName != null) {
            ct.event.push(eventName, eventPropperties);
            return true;
        } else {
            return false;
        }
    }

    public boolean logCustomEvent(String eventName) {
        if (ct != null && eventName != null) {
            ct.event.push(eventName);
            return true;
        } else {
            return false;
        }
    }
}
