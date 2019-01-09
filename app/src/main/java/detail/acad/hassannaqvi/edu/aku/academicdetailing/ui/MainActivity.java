package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters.Adapter;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.MainFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
    ActivityMainBinding bi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);


        loadHomeFragment();
        bi.bottomNav.home.setOnClickListener(this);
        bi.bottomNav.learning.setOnClickListener(this);
        bi.bottomNav.settings.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.home:

                loadHomeFragment();

                break;

        }
    }

    private void loadHomeFragment() {

//        bi.bottomNav.icHome.setBackgroundResource(R.drawable.ic_home_white);
//        bi.bottomNav.icBook.setBackgroundResource(R.drawable.ic_book_grey);
//        bi.bottomNav.icSetting.setBackgroundResource(R.drawable.ic_setting_grey);

        MainFragment fragment = new MainFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(bi.mainLayout.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
