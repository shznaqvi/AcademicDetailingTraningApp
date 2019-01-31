package detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;

public class Adapter extends PagerAdapter {

    Context context;
    int[] list;
    String path;
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
        View img = view.findViewById(R.id.image);
        container.addView(view,0);
        img.setBackgroundResource(list[position]);
//        if (list[position] == 0) {
//            context.startActivity(new Intent(context, PlayerActivity.class));
//        } else if (list[position] == 1) {
//
//        } else {
//            fl.setVisibility(View.GONE);
//
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
