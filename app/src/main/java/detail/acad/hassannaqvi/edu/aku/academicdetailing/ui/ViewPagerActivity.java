package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters.Adapter;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils.vb_imgs;

public class ViewPagerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


//
//        adapter = new Adapter(this, vb_imgs);
//        bi.viewPager.setAdapter(adapter);
//        bi.viewPager.beginFakeDrag();
//
//
//        bi.nextSlide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                bi.viewPager.setCurrentItem(getItem(+1), true);
//                if (lastItemPosition == vb_imgs.length - 1) {
//                    bi.viewPager.setCurrentItem(0);
//                } else {
//
//                    bi.viewPager.setCurrentItem(getItem(+1),true);
//                }
//
//
//            }
//        });
    }
}
