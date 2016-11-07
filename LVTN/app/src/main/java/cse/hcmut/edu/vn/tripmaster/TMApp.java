package cse.hcmut.edu.vn.tripmaster;

import android.app.Application;
import android.content.Context;

import com.orm.SugarContext;

import cse.hcmut.edu.vn.tripmaster.business.Constants;

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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _INSTANCE = this;
        //initialize Sugar
        SugarContext.init(this);
        mPref = new TMPref(this, Constants.PREFERENCES_NAME);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }
}
