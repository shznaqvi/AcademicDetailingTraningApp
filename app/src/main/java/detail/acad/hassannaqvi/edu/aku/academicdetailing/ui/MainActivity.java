package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.AndroidDatabaseManager;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.InfoFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.MainFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ModuleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Callbacks {


    private static final String TAG = "MainActivity";
    ActivityMainBinding bi;

    String currentDateTime = new SimpleDateFormat(" dd/MM/yyyy HH:mm:ss").format(new Date().getTime());
    boolean exit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainApp.logginTime = currentDateTime;

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

    public void loadHomeFragment() {
        MainFragment fragment = new MainFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
        transaction.replace(bi.mainLayout.getId(),fragment);
        transaction.addToBackStack(getString(R.string.mainFragment));
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (exit) {
            finish(); // finish activity
            startActivity(new Intent(this, LoginActivity.class));

        } else {

            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    public void loadInfoFragment() {
        InfoFragment fragment = new InfoFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
        transaction.replace(bi.mainLayout.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void loadModuleFragment() {
        ModuleFragment fragment = new ModuleFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
        transaction.replace(bi.mainLayout.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void loadDatabaseManager() {

        startActivity(new Intent(MainActivity.this,AndroidDatabaseManager.class));

    }
}
