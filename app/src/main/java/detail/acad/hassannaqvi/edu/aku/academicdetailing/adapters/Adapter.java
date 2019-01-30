package detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PlayerActivity;

public class Adapter extends PagerAdapter {

    Context context;
    int[] list;
    View view;

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

        view = LayoutInflater.from(context).inflate(R.layout.item, null);
        FrameLayout fl = view.findViewById(R.id.frameLayout);
        final VideoView vv = view.findViewById(R.id.videoPlayer);
        ImageView img = view.findViewById(R.id.image);
        container.addView(view, 0);
        img.setBackgroundResource(list[position]);


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
