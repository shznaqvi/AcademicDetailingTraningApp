package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.AndroidDatabaseManager;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.InfoFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.MainFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ModuleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ScheduleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Forms;
/*import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callbacks {


    private static final String TAG = "MainActivity";
    ActivityMainBinding bi;
    boolean exit = false;
    DatabaseHelper db;
    Collection<Forms> dbData;
    KProgressHUD hud;
    //Call<ResponseBody> call = null;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    AlertDialog.Builder builder;
    String m_Text = "";
    String dtToday = new SimpleDateFormat("dd-MM-yy HH:mm ").format(new Date().getTime());
    boolean newDist, oldDist;

    static File file;
    DownloadManager downloadManager;
    SharedPreferences sharedPrefDownload;
    SharedPreferences.Editor editorDownload;
    String preVer = "", newVer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainApp.isScheduleAppointment = false;
        MainApp.logginTime = MainApp.getCurrentTime();
        hud = KProgressHUD.create(this).setCancellable(false).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        db = new DatabaseHelper(this);
        setSupportActionBar(bi.toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        loadHomeFragment();

        loadTagDialog();

        bi.navigation.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.home:
                    loadHomeFragment();
                    return true;
                case R.id.videos:
                    startActivity(new Intent(MainActivity.this, DownloadVideoActivity.class));
                    return true;
                case R.id.appointment:
                    startActivity(new Intent(MainActivity.this, AppointmentsActivity.class));
                    return true;
            }
            return false;
        });


    }

    private void loadTagDialog() {

        sharedPref = getSharedPreferences("tagName", MODE_PRIVATE);
        editor = sharedPref.edit();
        if (!sharedPref.contains("tagName") && sharedPref.getString("tagName", null) == null) {

            builder = new AlertDialog.Builder(MainActivity.this);
            ImageView img = new ImageView(getApplicationContext());
            img.setImageResource(R.drawable.tagimg);
            img.setPadding(0, 15, 0, 15);
            builder.setCustomTitle(img);

            final EditText input = new EditText(MainActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text = input.getText().toString();
                    if (!m_Text.equals("")) {
                        editor.putString("tagName", m_Text);
                        editor.commit();
                        dialog.dismiss();

                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }

    }


    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//
//            case R.id.home:
//
//                loadHomeFragment();
//                break;
//
//            case R.id.vidDownload:
//
//                startActivity(new Intent(this, DownloadVideoActivity.class));
//                break;
//
//            case R.id.appointment:
//                startActivity(new Intent(this, AppointmentsActivity.class));
//                break;
//
//        }
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
    public void loadModuleFragment(Forms fc) {
        loadFragment(fc, new ModuleFragment());
    }

    @Override
    public void loadDatabaseManager() {
        startActivity(new Intent(MainActivity.this, AndroidDatabaseManager.class));
    }



    @Override
    public void uploadDataToServer() {

       /* DatabaseHelper db = new DatabaseHelper(this);

        Toast.makeText(getApplicationContext(), "Syncing Forms", Toast.LENGTH_SHORT).show();
        new SyncAllData(
                this,
                "Forms",
                "updateSyncedForms",
                Forms.class,
                MainApp._HOST_URL + FormsTable.Form_Url,
                db.getUnsyncedForms()
        ).execute();

        new SyncAllData(
                this,
                "Sessions ",
                "updateSyncedSessionForms",
                Session.class,
                MainApp._HOST_URL + SessionTable.SESSION_URL,
                db.getUnsyncedSessions()
        ).execute();

        new SyncAllData(
                this,
                "Next Meeting ScheduleActivity",
                "updateSyncedNMSForms",
                NextMeeting.class,
                MainApp._HOST_URL + NextMeetingTable.nms_Url,
                db.getUnsyncedNextMeetingForm()
        ).execute();

        SharedPreferences syncPref = getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = syncPref.edit();

        editor.putString("LastUpSyncServer", dtToday);

        editor.apply();
*/

    }

    @Override
    public void downloadData() {

    //    getHfDataFromServer();

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

        //loadFragment(new ScheduleFragment());
    }

    @Override
    public void uploadAppointment() {
/*

        new SyncAllData(
                this,
                "Appointment",
                "updateSyncedNMSForms",
                NextMeeting.class,
                MainApp._HOST_URL + CONSTANTS.appointmentURL,
                db.getUnsyncedNextMeetingForm()
        ).execute();
*/


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
        bundle.putString("district_name", MainApp.district.getDistrict_name());
        bundle.putBoolean("isAdmin", MainApp.admin);
        fragment.setArguments(bundle);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadFragment(Forms fc, Fragment fragment) {
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
        bundle.putString("district_name", MainApp.district.getDistrict_name());
        bundle.putBoolean("isAdmin", MainApp.admin);
        bundle.putParcelable("forms", fc);
        fragment.setArguments(bundle);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void clearAllFragments() {
        for (Fragment fragment : this.getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
/*
    private void getHfDataFromServer() {
//
//        hud.setLabel("Getting Health Facility Data");
//        call = RetrofitClient.service.synHfData();
//        syncingData("hf");
//        hud.setLabel("Getting Providers Data");
//        call = RetrofitClient.service.synHPData();
//        syncingData("hp");

    }

  *//*  private void syncingData(final String dataType) {
        hud.show();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    try {
                        String data = response.body().string();
                        final JSONArray array = new JSONArray(data);

                        if (dataType.equals("hf")) {
                     //       db.syncHF(array);
                        } else {
                        //    db.syncHP(array);
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

    }*/



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.action_database:
                intent = new Intent(MainActivity.this, AndroidDatabaseManager.class);
                startActivity(intent);
                break;

            case R.id.onSync:
                intent = new Intent(MainActivity.this, SyncActivity.class);
                startActivity(intent);
                break;

            case R.id.changePassword:
                intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        MenuItem action_database = menu.findItem(R.id.action_database);

        action_database.setVisible(MainApp.admin);
        return true;

    }
    public void openVideo(View view) {
        Intent intent = new Intent(MainActivity.this, VideoTestingActivity.class);
        startActivity(intent);
    }
}
