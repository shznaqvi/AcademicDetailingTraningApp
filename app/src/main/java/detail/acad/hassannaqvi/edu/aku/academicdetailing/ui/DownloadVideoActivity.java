package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;


import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.util.EventLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityDownloadVideoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.VideoItemLayoutBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;

public class DownloadVideoActivity extends AppCompatActivity {

    ActivityDownloadVideoBinding bi;
    ExoPlayer player;

    VideoItemsAdapter mAdapter;
    List<String> existVideos;
    DownloadManager downloadManager;
    Long refID;
    AlertDialog dialog;
    TextView progressText;
    ProgressBar progressBar;
    Button cancelButton;
    int modulePosition;
    List<String> modules;
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                DownloadManager.Query query = new DownloadManager.Query();
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int colIndex = cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {
                        dialog.dismiss();

                        // Populate recycler_view
                        new populateRecyclerView(DownloadVideoActivity.this, modulePosition).execute();

                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_download_video);

        modules = new ArrayList<>();

        setupModules();
        setListeners();

    }

    private void setupModules() {
        modules.add("-Select Module-");
        modules.add("CHILD MODULE");
        modules.add("MATERNAL MODULE");
        modules.add("NEWBORN MODULE");

        modules.remove(2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DownloadVideoActivity.this,
                android.R.layout.simple_spinner_item, modules);

        bi.modNSpinner.setAdapter(adapter);


       // bi.modNSpinner.attachDataSource(modules);
     /*   if (!MainApp.admin && !MainApp.userName.equals("test1234")) {
            switch (MainApp.districtCode) {
                case 113:
                case 123:
                case 432:
                case 234:
                    modules.add("-Select Module-");
                    modules.add("CHILD MODULE");
                    bi.modNSpinner.attachDataSource(modules);
                    break;
                case 252:
                    modules.add("-Select Module-");
                    modules.add("MATERNAL MODULE");
                    bi.modNSpinner.attachDataSource(modules);
                    break;
                case 434:
                    modules.add("-Select Module-");
                    modules.add("NEWBORN MODULE");
                    bi.modNSpinner.attachDataSource(modules);
                    break;
                case 211:
                case 414:
                    modules.add("-Select Module-");
                    modules.add("MATERNAL MODULE");
                    modules.add("NEWBORN MODULE");
                    bi.modNSpinner.attachDataSource(modules);
                    break;
            }
        } else {
            modules.add("-Select Module-");
            modules.add("CHILD MODULE");
            modules.add("MATERNAL MODULE");
            modules.add("NEWBORN MODULE");
            bi.modNSpinner.attachDataSource(modules);
        }*/

        existVideos = new ArrayList<>();


        // Populate recycler_view
        if (modules.size() > 0) {

            new populateRecyclerView(DownloadVideoActivity.this, 0).execute();
        }
    }

    private void setListeners() {

        bi.modNSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // populate recycler_view
                if (i != 0)
                    modulePosition = i;
                new populateRecyclerView(DownloadVideoActivity.this, i).execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Click listener recycler_view
        bi.modVidRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, final int position) {

                        for (String item : existVideos) {
                            if (item.equals(getStringArray(modulePosition)[position])) {
                                Toast.makeText(DownloadVideoActivity.this, "Video Already Downloaded!!", Toast.LENGTH_SHORT).show();
                                //   return;

                                String videoFile = Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME + File.separator + modules.get(modulePosition) + File.separator + item;

                                showVideo(videoFile);
                            }
                        }

                        // Downloading video
                        startDownloadingVideo(DownloadVideoActivity.this,
                                MainApp.getModuleName(modulePosition),
                                getStringArray(modulePosition)[position]);

                    }

                    @Override
                    public void onItemLongClick(final View view, final int position) {

                    }
                })
        );

    }

    private void startDownloadingVideo(Context mContext, String moduleName, String videosName) {

        String Directrory = Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME;
        File folder = new File(Directrory);
        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (!success) return;

        folder = new File(Directrory + File.separator + moduleName);
        if (!folder.exists())
            success = folder.mkdirs();
        if (!success) return;

        NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(CONSTANTS.Video_URL + videosName + ".mp4");
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setDestinationInExternalPublicDir(DatabaseHelper.PROJECT_NAME + File.separator + moduleName, videosName)
                    .setTitle("Downloading " + videosName);
            refID = downloadManager.enqueue(request);

            showVideodownloadDialoge(mContext, getVideoItemName(videosName));

        } else {
            Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private void showVideodownloadDialoge(Context mContext, String videosName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        View view = LayoutInflater.from(mContext).inflate(R.layout.downloading_dialog_layout, null);
        progressText = view.findViewById(R.id.progressTextView);
        progressText.setText("Downloading: " + videosName);
        progressBar = view.findViewById(R.id.progressBar);

        cancelButton = view.findViewById(R.id.cancelButton);
        builder.setView(view);
        progressBar.setMax(100);
        dialog = builder.create();
        dialog.show();


        cancelButton.setOnClickListener(v -> cancelAllVideos());
    }

    private void cancelAllVideos() {

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.remove(refID);
        unregisterReceiver(broadcastReceiver);
        dialog.dismiss();
    }

    private String getVideoItemName(String moduleName) {
        String[] nameSplit = moduleName.split("_");
        if (nameSplit.length != 3) return moduleName;
        return getSubModuleName(nameSplit[0]) + " " + nameSplit[2];
    }

    private String getSubModuleName(String startChar) {
        switch (startChar) {
            case "dia":
                return "Diarrhoea";
            case "gds":
                return "General Danger Sign";
            case "psbi":
                return "PSBI";
            case "cdb":
                return "Cough & Difficult Breathing";
            case "eceb":
                return "Newborn";
            default:
                return startChar;
        }
    }

    private String[] getStringArray(int position) {
        switch (position) {
            case 1:
                return Data.childVideos;
            case 2:
            case 3:
                return Data.newBornVideos;

            default:
                return new String[]{};
        }
    }

    private void showVideo(String videoFile) {
        player = new ExoPlayer.Builder(this).build();

        // Bind the player to the view.
        bi.playerView.setPlayer(player);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);

        player.addAnalyticsListener(new EventLogger(trackSelector));
        // Build the media item.
        // Uri videoUri = RawResourceDataSource.buildRawResourceUri(R.raw.gds01);


        MediaItem mediaItem = MediaItem.fromUri(videoFile);
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
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.release();
        }
    }

    public void openFullScreen(View view) {

        bi.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    //    Recycler classes
    private static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private final OnItemClickListener mListener;
        GestureDetector mGestureDetector;
        private RecyclerView viewRecycle;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;

            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = viewRecycle.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onItemLongClick(child, viewRecycle.getChildAdapterPosition(child));
                    }

                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            viewRecycle = view;
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }


    }

    private class VideoItemsAdapter extends RecyclerView.Adapter<VideoItemsAdapter.MyViewHolder> {

        private final String[] videosList;
        MyViewHolder holder;

        public VideoItemsAdapter(String[] videosList) {
            this.videosList = videosList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
            return new MyViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.video_item_layout, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            this.holder = holder;
            this.holder.bindUser(this.videosList[position]);
        }

        @Override
        public int getItemCount() {
            return videosList.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            VideoItemLayoutBinding videoItemBinding;

            public MyViewHolder(View itemView) {
                super(itemView);
                videoItemBinding = DataBindingUtil.bind(itemView);
            }

            public void bindUser(String mname) {
                videoItemBinding.movieName.setText(getVideoItemName(mname));
                if (MainApp.checkVideoExist(bi.modNSpinner.getSelectedItemPosition(), mname)) {
                    videoItemBinding.downloadImage.setImageResource(R.drawable.ic_check);
                    videoItemBinding.vdoStatus.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        videoItemBinding.movieName.setTextColor(getColor(R.color.colorPrimary));
                    else
                        videoItemBinding.movieName.setTextColor(getResources().getColor(R.color.colorPrimary));

                    existVideos.add(mname);
                }
            }
        }
    }

    private class populateRecyclerView extends AsyncTask<String, String, String> {
        private final Context mContext;
        private final int position;
        private final ProgressDialog pd;
        private String moduleName;

        public populateRecyclerView(Context mContext, int position) {
            this.mContext = mContext;
            this.position = position;
            pd = new ProgressDialog(mContext);
            pd.setTitle("LOADING VIDEOS");
            pd.setMessage("Processing...");
            pd.setCancelable(false);
            pd.setProgressStyle(R.layout.kprogresshud_hud);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//              Set Recycler View
                    mAdapter = new VideoItemsAdapter(getStringArray(position));
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 1);
                    bi.modVidRecycler.setLayoutManager(mLayoutManager);
                    bi.modVidRecycler.setItemAnimator(new DefaultItemAnimator());

                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    bi.modVidRecycler.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                    pd.dismiss();
                }
            }, 2000);
        }
    }


}
