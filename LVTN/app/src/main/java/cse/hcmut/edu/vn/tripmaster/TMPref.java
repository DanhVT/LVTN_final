package cse.hcmut.edu.vn.tripmaster;

import android.content.SharedPreferences;

/**
 * Created by AnTD on 10/25/2016.
 */

public class TMPref implements IPreference {
    public static final String KEY_UPLOADING = "Uploading";
    public static final String KEY_TRACKING = "Tracking";
    public static final String KEY_TOKEN = "Token";
    public static final String KEY_GRAVITY = "Gravity";

    private SharedPreferences mPref;

    @Override
    public void setTracking(boolean v) {
        setBoolean(KEY_TRACKING, v);
    }

    @Override
    public boolean getTracking() {
        return getBoolean(KEY_TRACKING, false);
    }

    @Override
    public void setToken(String v) {
        setString(KEY_TOKEN, v);
    }

    @Override
    public String getToken() {
        return getString(KEY_TOKEN, "");
    }

    @Override
    public void setGravity(String v) {
        setString(KEY_GRAVITY, v);
    }

    @Override
    public String getGravity() {
        return getString(KEY_GRAVITY, "");
    }

    //=================================================
    private void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(key, value);
        editor.commit();
    }
//    ==================================================
    private boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }
    public String getString(String key, String defValue) {
        return mPref.getString(key, defValue);
    }
}
