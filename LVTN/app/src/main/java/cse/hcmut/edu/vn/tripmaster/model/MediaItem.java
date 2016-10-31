package cse.hcmut.edu.vn.tripmaster.model;

import com.devbrackets.android.playlistcore.manager.IPlaylistItem;

/**
 * Created by AnTD on 10/31/2016.
 */

public class MediaItem implements IPlaylistItem {
    @Override
    public long getId() {
        return 0;
    }

    @Override
    public long getPlaylistId() {
        return 0;
    }

    @Override
    public int getMediaType() {
        return 0;
    }

    @Override
    public String getMediaUrl() {
        return null;
    }

    @Override
    public String getDownloadedMediaUri() {
        return null;
    }

    @Override
    public String getThumbnailUrl() {
        return null;
    }

    @Override
    public String getArtworkUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getAlbum() {
        return null;
    }

    @Override
    public String getArtist() {
        return null;
    }
}
