package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.exoplayer.DummyTrackRenderer;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.upstream.BandwidthMeter;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.PlayerControl;
import com.google.android.exoplayer.util.Util;

import java.util.concurrent.TimeUnit;

import cse.hcmut.edu.vn.tripmaster.R;
import cse.hcmut.edu.vn.tripmaster.ui.widget.HpLib_RendererBuilder;

public class VideoPlayer extends AppCompatActivity implements View.OnClickListener {
    private ExoPlayer exoPlayer;
    private SurfaceView surface;
    private int minBufferMs =    250000;
    private final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private final int BUFFER_SEGMENT_COUNT = 256;
    public static final int TYPE_VIDEO = 0;
    public static final int RENDERER_COUNT = 2;
    public static final int TYPE_AUDIO = 1;
    private RelativeLayout loadingPanel;
    private Handler mainHandler;
    private Runnable updatePlayer,hideControls;
    private HpLib_RendererBuilder hpLibRendererBuilder;
    private PlaybackState mPlaybackState;

    private TrackRenderer videoRenderer;
    private LinearLayout root,top_controls, middle_panel, unlock_panel, bottom_controls;
    private int currentTrackIndex;
    private String[] video_url, video_type, video_title;

    //Implementing the top bar
    private ImageButton btn_back;
    private TextView txt_title;

    private TextView txt_ct,txt_td;
    private SeekBar seekBar;
    private PlayerControl playerControl;
    private ImageButton btn_play;
    private ImageButton btn_pause;
    private ImageButton btn_fwd;
    private ImageButton btn_rev;
    private ImageButton btn_next;
    private ImageButton btn_prev;

    private ImageButton btn_lock;
    private ImageButton btn_unlock;
    private ImageButton btn_settings;

    @Override
    public void onClick(View v) {
        int i1 = v.getId();
        if (i1 == R.id.btn_back) {
            killPlayer();
            finish();
        }
        if (i1 == R.id.btn_pause) {
            if (playerControl.isPlaying()) {
                playerControl.pause();
                btn_pause.setVisibility(View.GONE);
                btn_play.setVisibility(View.VISIBLE);
            }
        }
        if (i1 == R.id.btn_play) {
            if (!playerControl.isPlaying()) {
                playerControl.start();
                btn_pause.setVisibility(View.VISIBLE);
                btn_play.setVisibility(View.GONE);
            }
        }
        if (i1 == R.id.btn_fwd) {
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() + 30000);
        }
        if (i1 == R.id.btn_rev) {
            exoPlayer.seekTo(exoPlayer.getCurrentPosition() - 30000);
        }
        if (i1 == R.id.btn_next) {
            exoPlayer.release();
            currentTrackIndex++;
            execute();
        }
        if (i1 == R.id.btn_prev) {
            exoPlayer.release();
            currentTrackIndex--;
            execute();
        }
        if (i1 == R.id.btn_lock) {
            controlsState = ControlsMode.LOCK;
            root.setVisibility(View.GONE);
            unlock_panel.setVisibility(View.VISIBLE);
        }
        if (i1 == R.id.btn_unlock) {
            controlsState = ControlsMode.FULLCONTORLS;
            root.setVisibility(View.VISIBLE);
            unlock_panel.setVisibility(View.GONE);
        }
        if (i1 == R.id.btn_settings) {
            PopupMenu popup = new PopupMenu(VideoPlayer.this, v);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    exoPlayer.setSelectedTrack(0, (item.getItemId() - 1));
                    return false;
                }
            });
            Menu menu = popup.getMenu();
            menu.add(Menu.NONE, 0, 0, "Video Quality");
            for (int i = 0; i < exoPlayer.getTrackCount(0); i++) {
                MediaFormat format = exoPlayer.getTrackFormat(0, i);
                if (MimeTypes.isVideo(format.mimeType)) {
                    if (format.adaptive) {
                        menu.add(1, (i + 1), (i + 1), "Auto");
                    } else {
                        menu.add(1, (i + 1), (i + 1), format.width + "p");
                    }
                }
            }
            menu.setGroupCheckable(1, true, true);
            menu.findItem((exoPlayer.getSelectedTrack(0) + 1)).setChecked(true);
            popup.show();
        }
    }


    public enum PlaybackState {
        PLAYING, PAUSED, BUFFERING, IDLE
    }
    public enum ControlsMode {
        LOCK, FULLCONTORLS
    }
    private ControlsMode controlsState;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);


        initView();
    }
    private void initView(){
        loadingPanel = (RelativeLayout) findViewById(R.id.loadingVPanel);
        txt_ct = (TextView) findViewById(R.id.txt_currentTime);
        txt_td = (TextView) findViewById(R.id.txt_totalDuration);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                exoPlayer.seekTo(seekBar.getProgress());
            }
        });

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_play = (ImageButton) findViewById(R.id.btn_play);
        btn_pause = (ImageButton) findViewById(R.id.btn_pause);
        btn_fwd = (ImageButton) findViewById(R.id.btn_fwd);
        btn_rev = (ImageButton) findViewById(R.id.btn_rev);
        btn_prev = (ImageButton) findViewById(R.id.btn_prev);
        btn_next = (ImageButton) findViewById(R.id.btn_next);

        btn_lock = (ImageButton) findViewById(R.id.btn_lock);
        btn_unlock = (ImageButton) findViewById(R.id.btn_unlock);
        btn_settings = (ImageButton) findViewById(R.id.btn_settings);

        btn_back.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_fwd.setOnClickListener(this);
        btn_rev.setOnClickListener(this);
        btn_prev.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        btn_lock.setOnClickListener(this);
        btn_unlock.setOnClickListener(this);
        btn_settings.setOnClickListener(this);

        unlock_panel = (LinearLayout) findViewById(R.id.unlock_panel);

        txt_title = (TextView) findViewById(R.id.txt_title);

        currentTrackIndex=0;

        root = (LinearLayout) findViewById(R.id.root);
        root.setVisibility(View.VISIBLE);
        surface= (SurfaceView) findViewById(R.id.surface_view);

        video_url = new String[]{"http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8","http://player.hungama.com/mp3/91508493.mp4"};
        video_title = new String[]{"Big Buck Bunny","Movie Trailer"};

        txt_title.setText(video_title[currentTrackIndex]);

        mainHandler = new Handler();



        execute();
    }
    private void execute() {
        exoPlayer=ExoPlayer.Factory.newInstance(RENDERER_COUNT);
        playerControl = new PlayerControl(exoPlayer);
        if(currentTrackIndex>=video_title.length){
            currentTrackIndex=(video_title.length-1);
        }else if(currentTrackIndex<=0){
            currentTrackIndex=0;
        }
        txt_title.setText(video_title[currentTrackIndex]);
        if(exoPlayer!=null) {
            hpLibRendererBuilder = getHpLibRendererBuilder();
            hpLibRendererBuilder.buildRenderers(this);
            loadingPanel.setVisibility(View.VISIBLE);
            mainHandler.postDelayed(updatePlayer, 200);
            mainHandler.postDelayed(hideControls, 3000);
            controlsState = ControlsMode.FULLCONTORLS;
        }
    }

    private HpLib_RendererBuilder getHpLibRendererBuilder() {
        String userAgent = Util.getUserAgent(this, "HpLib");
        return new HpLib_ExtractorHpLibRendererBuilder(this,userAgent, Uri.parse(video_url[currentTrackIndex]));

    }

    private void pushSurface(boolean blockForSurfacePush) {
        if (videoRenderer == null) {return;}
        if (blockForSurfacePush) {
            exoPlayer.blockingSendMessage(
                    videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surface.getHolder().getSurface());
        } else {
            exoPlayer.sendMessage(
                    videoRenderer, MediaCodecVideoTrackRenderer.MSG_SET_SURFACE, surface.getHolder().getSurface());
        }
    }

    void onRenderers(TrackRenderer[] renderers, BandwidthMeter bandwidthMeter) {
        for (int i = 0; i < renderers.length; i++) {
            if (renderers[i] == null) {
                renderers[i] = new DummyTrackRenderer();
            }
        }
        // Complete preparation.
        this.videoRenderer = renderers[TYPE_VIDEO];
        pushSurface(false);
        exoPlayer.prepare(renderers);
        exoPlayer.setPlayWhenReady(true);
    }

    void onRenderersError(Exception e) {
    }

    private void killPlayer(){
        if (exoPlayer != null) {
            exoPlayer.release();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        killPlayer();
    }
    Handler getMainHandler() {
        return mainHandler;
    }
    {
        updatePlayer = new Runnable() {
            @Override
            public void run() {
                switch (exoPlayer.getPlaybackState()) {
                    case ExoPlayer.STATE_BUFFERING:
                        loadingPanel.setVisibility(View.VISIBLE);
                        break;
                    case ExoPlayer.STATE_ENDED:
                        finish();
                        break;
                    case ExoPlayer.STATE_IDLE:
                        loadingPanel.setVisibility(View.GONE);
                        break;
                    case ExoPlayer.STATE_PREPARING:
                        loadingPanel.setVisibility(View.VISIBLE);
                        break;
                    case ExoPlayer.STATE_READY:
                        loadingPanel.setVisibility(View.GONE);
                        break;
                    default:
                        break;
                }

                String totDur = String.format("%02d.%02d.%02d",
                        TimeUnit.MILLISECONDS.toHours(exoPlayer.getDuration()),
                        TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getDuration()) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(exoPlayer.getDuration())), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(exoPlayer.getDuration()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getDuration())));
                String curDur = String.format("%02d.%02d.%02d",
                        TimeUnit.MILLISECONDS.toHours(exoPlayer.getCurrentPosition()),
                        TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getCurrentPosition()) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(exoPlayer.getCurrentPosition())), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(exoPlayer.getCurrentPosition()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(exoPlayer.getCurrentPosition())));
                txt_ct.setText(curDur);
                txt_td.setText(totDur);
                seekBar.setMax((int) exoPlayer.getDuration());
                seekBar.setProgress((int) exoPlayer.getCurrentPosition());

                mainHandler.postDelayed(updatePlayer, 200);
            }
        };
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // check if the request code is same as what is passed  here it is 2
//        if(requestCode==200){
//            int currTime = data.getIntExtra("currTime",0);
//            exoPlayer.seekTo(currTime);
//        }
//    }

}
