package cse.hcmut.edu.vn.tripmaster;

import android.app.Application;

/**
 * Created by AnTD on 10/25/2016.
 */

public class TMApp extends Application {
    private static TMApp _INSTANCE;
    private static TMPref mPref;

    public static TMApp getInstance() {
        return _INSTANCE;
    }

    public static IPreference getPref() {
        return mPref;
    }
}
