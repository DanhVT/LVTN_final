package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.devbrackets.android.playlistcore.listener.PlaylistListener;
import com.devbrackets.android.playlistcore.service.PlaylistServiceCore;

import cse.hcmut.edu.vn.tripmaster.model.MediaItem;

/**
 * Created by AnTD on 10/31/2016.
 */

public class VideoPlayer extends Activity implements PlaylistListener<MediaItem> {
    @Override
    public boolean onPlaylistItemChanged(@Nullable MediaItem currentItem, boolean hasNext, boolean hasPrevious) {
        return false;
    }

    @Override
    public boolean onPlaybackStateChanged(@NonNull PlaylistServiceCore.PlaybackState playbackState) {
        return false;
    }
}
