package detail.acad.hassannaqvi.edu.aku.academicdetailing;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.util.Log;
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


    private static final String TAG = "MainActivity";
    ActivityMainBinding bi;
    Adapter adapter;
    int lastItemPosition;
    int[] vb_imgs = new int[]{R.drawable.vb_img_1, R.drawable.vb_img_2, R.drawable.vb_img_3,
            R.drawable.vb_img_4, R.drawable.vb_img_5, R.drawable.vb_img_6, R.drawable.vb_img_7,
            R.drawable.vb_img_8, R.drawable.vb_img_9, R.drawable.vb_img_10, R.drawable.vb_img_11,
            R.drawable.vb_img_12, R.drawable.vb_img_13, R.drawable.vb_img_14, R.drawable.vb_img_15,
            R.drawable.vb_img_16, R.drawable.vb_img_17, R.drawable.vb_img_18, R.drawable.vb_img_19,
            R.drawable.vb_img_20, R.drawable.vb_img_21, R.drawable.vb_img_22, R.drawable.vb_img_23,
            R.drawable.vb_img_24, R.drawable.vb_img_25};

    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    boolean pageChanged = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);


        adapter = new Adapter(this, vb_imgs);
        bi.viewPager.setAdapter(adapter);
        bi.viewPager.beginFakeDrag();

        bi.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

//                pageChanged = true;


            }

            @Override
            public void onPageSelected(int i) {

                lastItemPosition = i;
//                pageChanged = false;


            }

            @Override
            public void onPageScrollStateChanged(int i) {

                Log.d(TAG, "onPageScrollStateChanged: " + i);

            }
        });

        bi.nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                bi.viewPager.setCurrentItem(getItem(+1), true);
                if (lastItemPosition == vb_imgs.length - 1) {
                    bi.viewPager.setCurrentItem(0);
                } else {

                    bi.viewPager.setCurrentItem(getItem(+1),true);
                }


            }
        });


    }

//    private void stopTimer() {
//
//        timeSwapBuff += timeInMilliseconds;
//        customHandler.removeCallbacks(updateTimerThread);
//
//    }
//
//    private void startTimer() {
//
//        startTime = SystemClock.uptimeMillis();
//        customHandler.postDelayed(updateTimerThread, 0);
//
//    }
//
//    private Runnable updateTimerThread = new Runnable() {
//        public void run() {
//
//            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//
//            updatedTime = timeSwapBuff + timeInMilliseconds;
//
//            int secs = (int) (updatedTime / 1000);
//            int mins = secs / 60;
//            secs = secs % 60;
//            int milliseconds = (int) (updatedTime % 1000);
//
//            Log.d(TAG, "run: total Time " + mins + " " + secs);
////            timerValue.setText("" + mins + ":"
////
////                            + String.format("%02d", secs) + ":"
////
////                            + String.format("%03d", milliseconds));
//            customHandler.postDelayed(this, 0);
//
//
//        }
//
//
//    };


    private int getItem(int i) {
        return bi.viewPager.getCurrentItem() + i;
    }

}
