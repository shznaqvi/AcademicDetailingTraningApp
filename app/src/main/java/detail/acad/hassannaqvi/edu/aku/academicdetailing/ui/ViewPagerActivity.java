package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters.Adapter;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityViewPagerBinding;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils.vb_imgs;

public class ViewPagerActivity extends AppCompatActivity {

    ActivityViewPagerBinding bi;
    Adapter adapter;
    int lastItemPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this,R.layout.activity_view_pager);


        final int[] slides = getIntent().getIntArrayExtra("slides");

//
        adapter = new Adapter(this, slides);
        bi.viewPager.setAdapter(adapter);
        bi.viewPager.beginFakeDrag();


        bi.nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bi.viewPager.setCurrentItem(getItem(+1), true);
                if (lastItemPosition == slides.length - 1) {
                    bi.viewPager.setCurrentItem(0);
                } else {

                    bi.viewPager.setCurrentItem(getItem(+1),true);
                }


            }
        });
    }
    private int getItem(int i) {
        return bi.viewPager.getCurrentItem() + i;
    }

}
