package cse.hcmut.edu.vn.tripmaster;

import android.app.Application;

import com.orm.SugarContext;

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
    @Override
    public void onCreate() {
        super.onCreate();
        _INSTANCE = this;
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }
}
