package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;


import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityPlayerNewBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;

public class PlayerActivityNew extends AppCompatActivity {


    private static final String TAG = "PlayerActivityNew";
    ActivityPlayerNewBinding bi;
    ExoPlayer player;
    Data.SubMenu subMenu;
    Long playbackPosition = new Long(0);
    int currentWindow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_player_new);
       // bi.setCallback(this);

        initializingComponents();
    }

    void initializingComponents() {

        // Get intent
        subMenu = (Data.SubMenu) getIntent().getSerializableExtra("submenu");

        //Video
        player = new ExoPlayer.Builder(this).build();
        // Bind the player to the view.
        bi.videoPlayer.setPlayer(player);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, DatabaseHelper.PROJECT_NAME));
        playingVideo(player, dataSourceFactory, subMenu);
     /*   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        bi.videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        //bi.videoPlayer.hideController();
        bi.videoPlayer.setUseController(false);

        bi.videoPlayer.setKeepScreenOn(true);*/

    }


/*    void playingVideo(SimpleExoPlayer videoPlayer, DataSource.Factory dataSourceFactory, Data.SubMenu data) {
        String Directory = Environment.getExternalStorageDirectory() + File.separator
                + DatabaseHelper.PROJECT_NAME + File.separator + data.getModuleName().toUpperCase()
                + File.separator;*/
    //   void playingVideo(SimpleExoPlayer videoPlayer, DataSource.Factory dataSourceFactory, Data.SubMenu data) {
    private void playingVideo(ExoPlayer videoPlayer, DataSource.Factory dataSourceFactory, Data.SubMenu data) {
            String Directory = Environment.getExternalStorageDirectory() + File.separator
                    + DatabaseHelper.PROJECT_NAME + File.separator + data.getModuleName().toUpperCase()
                    + File.separator;

        // This is the MediaSource representing the media to be played.
        MediaSource[] mediaSources = new MediaSource[data.getVideosName().length];
        String[] videos = data.getVideosName();

       /* // Listener
        videoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                bi.txtVideoName.setText(data.getModuleName().toUpperCase() + " --- " + videos[videoPlayer.getCurrentWindowIndex()].toUpperCase());
            }
        });*/
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                Log.d(TAG, "onPlaybackStateChanged1: " + playbackState);

            }
        });

        player.addListener(new Player.Listener() {

            @Override
            public void onPlaybackStateChanged(int state) {
                Log.d(TAG, "onPlaybackStateChanged2: " + state);
                if (state == Player.STATE_ENDED) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    bi.videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                    bi.videoPlayer.setUseController(true);
                    bi.videoPlayer.setKeepScreenOn(false);
                    //bi.videoPlayer.showController();
                    player.pause();
                } /*else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    bi.videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                    //bi.videoPlayer.hideController();
                    bi.videoPlayer.setUseController(false);

                    bi.videoPlayer.setKeepScreenOn(true);
                }*/


            }

        });


        // Select and play video
/*        for (byte i = 0; i < videos.length; i++) {
            mediaSources[i] = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(Directory + videos[i]));
        }
        // Concatenate the videos
        ConcatenatingMediaSource concatenatedSource = new ConcatenatingMediaSource(mediaSources);
        // Prepare the player with the source.
        videoPlayer.prepare(concatenatedSource);*/
        Uri videoUri = null;
        MediaItem mediaItem = null;
        for (byte i = 0; i < videos.length; i++) {
            // videoUri = RawResourceDataSource.buildRawResourceUri(R.raw.gds01);
            mediaItem = MediaItem.fromUri(Uri.parse(Directory + videos[i] + ".mp4"));
        }

        //MediaItem mediaItem = MediaItem.fromUri(videoUri);
// Set the media item to be played.
        player.setMediaItem(mediaItem);
        //player.setRepeatMode(Player.REPEAT_MODE_OFF);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        bi.videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        //bi.videoPlayer.hideController();
        bi.videoPlayer.setUseController(false);

        bi.videoPlayer.setKeepScreenOn(true);
// Prepare the player.
        player.prepare();

// Start the playback.
        player.play();

        bi.videoPlayer.setKeepScreenOn(true);
        DefaultTimeBar dtBar = new DefaultTimeBar(this);
        // dtBar.setEnabled(false);

    }

    public void btnOk(View view) {
        Utils.showPostDialoge(this, subMenu, 0);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        if (player != null) {
            restartPlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }



    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
          //  currentWindow = player.getCurrentMediaItemIndex();
            player.setPlayWhenReady(false);
            bi.videoPlayer.onResume();
        }
    }

    private void restartPlayer() {
        bi.videoPlayer.setPlayer(player);
        player.seekTo(playbackPosition);
    }

    @Override
    public void onStop(){
        super.onStop();
        player.release();
    }*/


    public void openFullScreen(View view) {

        bi.videoPlayer.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}
