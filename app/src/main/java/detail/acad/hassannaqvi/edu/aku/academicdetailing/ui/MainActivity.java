package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.Collection;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.RetrofitClient.RetrofitClient;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.DistrictsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.NextMeetingContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.SessionContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.AndroidDatabaseManager;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.InfoFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.MainFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ModuleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ScheduleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.sync.SyncAllData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callbacks {


    private static final String TAG = "MainActivity";
    ActivityMainBinding bi;
    boolean exit = false;
    DatabaseHelper db;
    Collection<FormsContract> dbData;
    KProgressHUD hud;
    Call<ResponseBody> call = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainApp.isScheduleAppointment = false;
        MainApp.logginTime = MainApp.getCurrentTime();
        hud = KProgressHUD.create(this).setCancellable(false).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        db = new DatabaseHelper(this);
        bi.bottomNav.home.setOnClickListener(this);
        bi.bottomNav.vidDownload.setOnClickListener(this);
        bi.bottomNav.settings.setOnClickListener(this);
        DistrictsContract dst = db.getDistrict(MainApp.districtCode);
        if (dst != null) {
            MainApp.districtName = dst.getDistrict_name();
        }
        loadHomeFragment();


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.home:

                loadHomeFragment();
                break;

            case R.id.vidDownload:

                startActivity(new Intent(this, DownloadVideoActivity.class));
                break;

        }
    }

    public void loadHomeFragment() {
        loadFragment(new MainFragment());
    }

    @Override
    public void loadInfoFragment() {

        if (db.getDistrictCount() > 0) {
            loadFragment(new InfoFragment());
        } else {

            Toast.makeText(this, "Please Download Data First", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void loadModuleFragment(FormsContract fc) {
        loadFragment(fc, new ModuleFragment());
    }

    @Override
    public void loadDatabaseManager() {
        startActivity(new Intent(MainActivity.this, AndroidDatabaseManager.class));
    }

    @Override
    public void uploadDataToServer() {

        DatabaseHelper db = new DatabaseHelper(this);

        Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
        new SyncAllData(
                this,
                "Forms",
                "updateSyncedForms",
                FormsContract.class,
                MainApp._HOST_URL + FormsContract.FormsTable.Form_Url,
                db.getUnsyncedForms()
        ).execute();

        new SyncAllData(
                this,
                "Sessions ",
                "updateSyncedSessionForms",
                SessionContract.class,
                MainApp._HOST_URL + SessionContract.SessionTable.SESSION_URL,
                db.getUnsyncedSessions()
        ).execute();

        new SyncAllData(
                this,
                "Next Meeting Schedule",
                "updateSyncedNMSForms",
                NextMeetingContract.class,
                MainApp._HOST_URL + NextMeetingContract.NMCTable.nms_Url,
                db.getUnsyncedNextMeetingForm()
        ).execute();


    }

    @Override
    public void downloadData() {

        getHfDataFromServer();

    }

    @Override
    public void loadInfo() {

        if (db.getDistrictCount() > 0) {
            loadFragment(new InfoFragment());
        } else {
            Toast.makeText(this, "Please Download Data First", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void loadScheduleFragment() {

        loadFragment(new ScheduleFragment());
    }

    @Override
    public void uploadAppointment() {

        new SyncAllData(
                this,
                "Appointment",
                "updateSyncedNMSForms",
                NextMeetingContract.class,
                MainApp._HOST_URL + CONSTANTS.appointmentURL,
                db.getUnsyncedNextMeetingForm()
        ).execute();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (exit) {
            finish(); // finish activity
            startActivity(new Intent(this, LoginActivity.class));

        } else {

            loadHomeFragment();
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

    private void loadFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment.getClass().getName().equals(MainFragment.class.getName())) {
            clearAllFragments();
            transaction.add(bi.mainLayout.getId(), fragment);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
            transaction.replace(bi.mainLayout.getId(), fragment);
        }
        Bundle bundle = new Bundle();
        bundle.putString("district_name", MainApp.districtName);
        bundle.putBoolean("isAdmin", MainApp.admin);
        fragment.setArguments(bundle);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadFragment(FormsContract fc, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment.getClass().getName().equals(MainFragment.class.getName())) {
            clearAllFragments();
            transaction.add(bi.mainLayout.getId(), fragment);
        } else {
            transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
            transaction.replace(bi.mainLayout.getId(), fragment);
        }
        Bundle bundle = new Bundle();
        bundle.putString("district_name", MainApp.districtName);
        bundle.putBoolean("isAdmin", MainApp.admin);
        bundle.putParcelable("fc", fc);
        fragment.setArguments(bundle);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void clearAllFragments() {
        for (Fragment fragment : this.getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    private void getHfDataFromServer() {

        hud.setLabel("Getting Health Facility Data");
        call = RetrofitClient.service.synHfData();
        syncingData("hf");
        hud.setLabel("Getting Providers Data");
        call = RetrofitClient.service.synHPData();
        syncingData("hp");

    }

    private void syncingData(final String dataType) {
        hud.show();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        String data = response.body().string();
                        final JSONArray array = new JSONArray(data);

                        if (dataType.equals("hf")) {
                            db.syncHF(array);
                        } else {
                            db.syncHP(array);
                        }
                        Toast.makeText(MainActivity.this, "Successfully Downloaded", Toast.LENGTH_SHORT).show();
                        hud.dismiss();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                hud.dismiss();
                Toast.makeText(MainActivity.this, "Server connection Error", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
