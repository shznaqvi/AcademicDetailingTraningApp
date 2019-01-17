package detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;

public class Adapter extends PagerAdapter {

    Context context;
    int[] list;
    String path;

    public Adapter(Context context, int[] mList) {

        this.context = context;
        this.list = mList;
    }

    @Override
    public int getCount() {
        return list.length;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        ImageView img = view.findViewById(R.id.image);
//        final VideoView videoView = view.findViewById(R.id.videoPlayer);
        container.addView(view, 0);
        img.setImageResource(list[position]);
//        try {
//            if (list[position] == 0) {
//                videoView.setVisibility(View.VISIBLE);
//                path = "android:resource://" + context.getPackageName() +  "/raw/" + "gds01.mp4";
//                Uri uri = Uri.parse(path);
//                videoView.setVideoURI(uri);
//                videoView.requestFocus();
//                videoView.setMediaController(new MediaController(context));
//                videoView.start();
//            } else if (list[position] == 1) {
//                videoView.setVisibility(View.VISIBLE);
//                path = "android:resource://" + context.getPackageName() + "/" + R.raw.gds02;
//                Uri uri = Uri.parse(path);
//                videoView.setVideoURI(uri);
//                videoView.requestFocus();
//                videoView.setMediaController(new MediaController(context));
//                videoView.start();
//
//            }else{
//                videoView.setVisibility(View.GONE);
//
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        return view;
    }


    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        //See if object from instantiateItem is related to the given view
        //required by API
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        object = null;
    }
}
