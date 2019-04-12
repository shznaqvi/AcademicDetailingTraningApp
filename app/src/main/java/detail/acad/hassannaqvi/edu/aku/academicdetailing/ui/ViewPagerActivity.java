package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters.Adapter;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.SessionContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityViewPagerBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;

//import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.slides;


public class ViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "ViewPagerActivity";
    ActivityViewPagerBinding bi;
    Adapter adapter;
    int lastItemPosition;
    boolean isClicked = false;
    Data.SubMenu subMenuDT;

    boolean viewPagerFlag = false;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_view_pager);

        db = new DatabaseHelper(this);
        subMenuDT = (Data.SubMenu) getIntent().getSerializableExtra(CONSTANTS.URI_SUBMENU_DT);


        settingupViewPager();

        viewPagerFlag = getIntent().getBooleanExtra(CONSTANTS.URI_DIRECT_VIEWPAGER, false);


    }

    private void settingupViewPager() {

        this.setTitle(subMenuDT.getName());
        adapter = new Adapter(this, subMenuDT.getSession());
        bi.viewPager.setAdapter(adapter);
        bi.viewPager.beginFakeDrag();

        bi.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                lastItemPosition = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        bi.nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastItemPosition == subMenuDT.getSession().length - 1) {

                    /*if (MainApp.isMaternal) {
                        Utils.showPostDialoge(ViewPagerActivity.this, MainApp.maternalIndex,MainApp.subModuleName);
                        isComplete = false;


                    } else if (MainApp.isChild) {
                        Utils.showPostDialoge(ViewPagerActivity.this, MainApp.childlIndex,MainApp.subModuleName);
                        isComplete = false;
                    }else if(MainApp.isNBorn){
                        Utils.showPostDialoge(ViewPagerActivity.this, MainApp.childlIndex,MainApp.subModuleName);
                        isComplete = false;
                    }*/

                    if (viewPagerFlag)
                        Utils.showViewPagerDialoge(ViewPagerActivity.this, subMenuDT);
                    else {
                        Utils.showPostDialoge(ViewPagerActivity.this, subMenuDT, (subMenuDT.getVideosName().length > 0 ? 1 : 0));
                        isComplete = false;
                    }


                } else {
                    bi.viewPager.setCurrentItem(getItem(+1), true);
                    saveDB();


                }


            }
        });

        bi.backSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lastItemPosition >= 0) {
                    bi.viewPager.setCurrentItem(getItem(-1), true);

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            saveDB();
                        }
                    });


                }
            }
        });

        bi.exitSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainApp.showDialog(ViewPagerActivity.this, "Do You Want To Exit?", "end", false, subMenuDT);

            }
        });
    }

    private void saveDB() {

        SharedPreferences sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        SessionContract sC = new SessionContract();
        sC.setModule(subMenuDT.getModuleCode());
        sC.setSession(subMenuDT.getSessionCode());
        sC.setSlideNumber(lastItemPosition);
        sC.setDeviceid(MainApp.deviceId);
        sC.setFormdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        sC.setSessionTime(MainApp.getCurrentTime());
        sC.set_UUID(MainApp.formsUID);
        sC.setDevicetagID(sharedPref.getString("tagName", null));
        sC.setUsername(MainApp.userName);

        long rowId = db.addSessionData(sC);
        if (rowId > 0) {
            sC.set_id(String.valueOf(rowId));
            sC.set_UID((sC.getDeviceid() + sC.get_id()));
            db.updateSessionFormID(sC);
        }
    }


    private int getItem(int i) {
        return bi.viewPager.getCurrentItem() + i;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}