package cse.hcmut.edu.vn.tripmaster;

import android.content.SharedPreferences;

/**
 * Created by AnTD on 10/25/2016.
 */

public class TMPref implements IPreference {
    public static final String KEY_UPLOADING = "Uploading";
    public static final String KEY_TRACKING = "Tracking";

    private SharedPreferences mPref;

    @Override
    public void setTracking(boolean v) {
        setBoolean(KEY_TRACKING, v);
    }

    @Override
    public boolean getTracking() {
        return getBoolean(KEY_TRACKING, false);
    }

    //=================================================
    private void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
//    ==================================================
    private boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }
}
