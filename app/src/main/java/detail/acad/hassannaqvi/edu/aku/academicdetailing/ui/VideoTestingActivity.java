package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.EventLogger;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityVideoTestingBinding;


public class VideoTestingActivity extends AppCompatActivity {

    ActivityVideoTestingBinding bi;
    ExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_video_testing);
      //setContentView(R.layout.activity_video_testing);



    }

    @Override
    protected void onResume() {
        super.onResume();

        showVideo();
    }

    private void showVideo() {
        player = new ExoPlayer.Builder(this).build();

        // Bind the player to the view.
        bi.playerView.setPlayer(player);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);

        player.addAnalyticsListener(new EventLogger(trackSelector));
        // Build the media item.
        Uri videoUri = RawResourceDataSource.buildRawResourceUri(R.raw.gds01);


        MediaItem mediaItem = MediaItem.fromUri(videoUri);
// Set the media item to be played.
        player.setMediaItem(mediaItem);
// Prepare the player.
        player.prepare();
// Start the playback.
        player.play();

        player.addListener(new Player.Listener() {

            @Override
            public void onPlaybackStateChanged(@Player.State int state) {
                if (state == Player.STATE_ENDED) {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    bi.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

                } else {
                    bi.playerView.hideController();
                }
            }
        });
    }

    @Override
    public void onStop(){
        super.onStop();
        player.release();
    }


    public void openFullScreen(View view) {

        bi.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}