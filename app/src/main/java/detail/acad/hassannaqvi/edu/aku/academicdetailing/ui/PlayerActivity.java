package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity {


    ActivityPlayerBinding bi;
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_player);

        String fileName = "gds01";
        String filePlace = "android.resource://" + getPackageName() + "/raw/" + fileName;
        bi.videoPlayer.setVideoURI(Uri.parse(filePlace));
        bi.videoPlayer.requestFocus();
        MediaController mediaController = new MediaController(this);
        bi.videoPlayer.setMediaController(mediaController);
        mediaController.setAnchorView(bi.videoPlayer);
        bi.videoPlayer.start();


    }
}
