package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityPlayerBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;

public class PlayerActivity extends AppCompatActivity {


    ActivityPlayerBinding bi;
    String path;
    Data.SubMenu subMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_player);
        bi.setCallback(this);

        /*String fileName = "gds01";
        String filePlace = "android.resource://" + getPackageName() + "/raw/" + fileName;
        bi.videoPlayer.setVideoURI(Uri.parse(filePlace));
        bi.videoPlayer.requestFocus();
        MediaController mediaController = new MediaController(this);
        bi.videoPlayer.setMediaController(mediaController);
        mediaController.setAnchorView(bi.videoPlayer);
        bi.videoPlayer.start();*/

        initializingComponents();

    }

    void initializingComponents() {

        // Get intent
        subMenu = (Data.SubMenu) getIntent().getSerializableExtra("submenu");

        //Video
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(this);
        // Bind the player to the view.
        bi.videoPlayer.setPlayer(player);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, DatabaseHelper.PROJECT_NAME));
        playingVideo(player, dataSourceFactory, subMenu);

    }

    void playingVideo(SimpleExoPlayer videoPlayer, DataSource.Factory dataSourceFactory, Data.SubMenu data) {

        String Directory = Environment.getExternalStorageDirectory() + File.separator
                + DatabaseHelper.PROJECT_NAME + File.separator + data.getModuleName().toUpperCase()
                + File.separator;

        // This is the MediaSource representing the media to be played.
        MediaSource[] mediaSources = new MediaSource[data.getVideosName().length];
        String[] videos = data.getVideosName();

        // Listener
        videoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                bi.txtVideoName.setText(data.getModuleName().toUpperCase() + " --- " + videos[videoPlayer.getCurrentWindowIndex()].toUpperCase());
            }
        });


        for (byte i = 0; i < videos.length; i++) {
            mediaSources[i] = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(Directory + videos[i]));
        }

        // Concatenate the videos
        ConcatenatingMediaSource concatenatedSource = new ConcatenatingMediaSource(mediaSources);
        // Prepare the player with the source.
        videoPlayer.prepare(concatenatedSource);

    }


    public void BtnOK() {

//        Utils.forcePostTest(this, subMenu, 0);
        Utils.showPostDialoge(this, subMenu, 0);

    }

    @Override
    protected void onPause() {
        super.onPause();
        bi.videoPlayer.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bi.videoPlayer.onPause();
    }
}
