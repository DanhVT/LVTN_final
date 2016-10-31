package cse.hcmut.edu.vn.tripmaster.service.internal;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.devbrackets.android.playlistcore.api.AudioPlayerApi;
import com.devbrackets.android.playlistcore.manager.BasePlaylistManager;
import com.devbrackets.android.playlistcore.service.BasePlaylistService;
import com.squareup.picasso.Picasso;

import cse.hcmut.edu.vn.tripmaster.ui.activity.StartupActivity;

/**
 * Created by AnTD on 11/1/2016.
 */

public class MediaService extends BasePlaylistService {
    private static final int NOTIFICATION_ID = 1564; //Arbitrary
    private static final int FOREGROUND_REQUEST_CODE = 332; //Arbitrary
    private static final float AUDIO_DUCK_VOLUME = 0.1f;

    private Picasso picasso;

    @Override
    public void onCreate() {
        super.onCreate();
        picasso = Picasso.with(getApplicationContext());
    }

    @Override
    protected int getNotificationId() {
        return NOTIFICATION_ID;
    }

    @NonNull
    @Override
    protected PendingIntent getNotificationClickPendingIntent() {
        Intent intent = new Intent(getApplicationContext(), StartupActivity.class);
        return null;
    }

    @Nullable
    @Override
    protected Bitmap getDefaultLargeNotificationImage() {
        return null;
    }

    @Override
    protected int getNotificationIconRes() {
        return 0;
    }

    @Override
    protected int getRemoteViewIconRes() {
        return 0;
    }

    @NonNull
    @Override
    protected AudioPlayerApi getNewAudioPlayer() {
        return null;
    }

    @Override
    protected float getAudioDuckVolume() {
        return 0;
    }

    @NonNull
    @Override
    protected BasePlaylistManager getPlaylistManager() {
        return null;
    }
}
