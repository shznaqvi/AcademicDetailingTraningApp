package detail.acad.hassannaqvi.edu.aku.academicdetailing;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.ArrayList;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding bi;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);



        List<String> list = new ArrayList<>();
        list.add("Hellow");
        list.add("Heyyy");
        list.add("Yayyy");
        list.add("Oops!");

        adapter = new Adapter(this,list);
        bi.viewPager.setAdapter(adapter);
        bi.viewPager.beginFakeDrag();

        bi.nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bi.viewPager.setCurrentItem(getItem(+1),true);
            }
        });




    }

    private int getItem(int i) {
        return bi.viewPager.getCurrentItem() + i;
    }

}
