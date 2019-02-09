package detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;

public class SlidingImageAdapter extends PagerAdapter {


    Context context;
    int[] imageList;


    public SlidingImageAdapter(Context context, int[] images) {

        this.context = context;
        this.imageList = images;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = LayoutInflater.from(context).inflate(R.layout.slidingimages_layout, null, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout
                .findViewById(R.id.image);
        imageView.setImageResource(imageList[position]);

        container.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public int getCount() {
        return imageList.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
