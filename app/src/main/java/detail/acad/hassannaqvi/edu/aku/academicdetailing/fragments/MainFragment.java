package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.File;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.VersionAppContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.MainActivity;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding bi;
    View view;
    Callbacks callbacks;
    DatabaseHelper db;
    String districtName = "";
    boolean isAdmin;
    KProgressHUD hud;

    static File file;
    int id = 1;
    DownloadManager downloadManager;
    Long refID;
    SharedPreferences sharedPrefDownload;
    SharedPreferences.Editor editorDownload;
    VersionAppContract versionAppContract;
    BroadcastReceiver broadcastReceiver;

    String preVer = "", newVer = "";

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bi = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        view = bi.getRoot();

        sharedPrefDownload = getContext().getSharedPreferences("appDownload", getContext().MODE_PRIVATE);
        editorDownload = sharedPrefDownload.edit();

        isAdmin = getArguments().getBoolean("isAdmin");
        if (MainApp.userName.equals("dmu@aku")) {
            bi.openDB.setVisibility(View.VISIBLE);
        }

        db = new DatabaseHelper(getContext());


        onClickListener();
      //  setupViews();
        return view;
    }

  /*  private void setupViews() {
        SharedPreferences syncPref = getContext().getSharedPreferences("SyncInfo", Context.MODE_PRIVATE);
        bi.userName.setText("Hello! " + MainApp.userName);
        bi.lastDownload.setText(syncPref.getString("LastDownSyncServer", "Never Downloaded"));
        bi.lastUpdated.setText(syncPref.getString("LastUpSyncServer", "Never Synced"));
        bi.syncedForm.setText(String.valueOf(db.getUnsyncedForms().size()));
        bi.formsToday.setText(String.valueOf(db.getTodayForms().size()));
        bi.districtName.setText(MainApp.districtName);

        hud = KProgressHUD.create(getContext()).setLabel("Syncing Appointments").setDetailsLabel("Syncing..")
                .setCancellable(false).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);


    }
*/
    private void onClickListener() {

        bi.startTraining.setOnClickListener(v -> callbacks.loadInfoFragment());

        bi.openDB.setOnClickListener(v -> callbacks.loadDatabaseManager());

        bi.uploadData.setOnClickListener(v -> {

            callbacks.uploadDataToServer();

        });

        bi.downloadVideo.setOnClickListener(v -> callbacks.downloadData());

        bi.scheduleAppointment.setOnClickListener(v -> {

            MainApp.isScheduleAppointment = true;
            callbacks.loadInfo();
        });

/*        bi.syncAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //syncingAppointment();
            }
        });*/

        //update App feature Code---------------------------------------

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {

                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(sharedPrefDownload.getLong("refID", 0));

                    downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                    Cursor cursor = downloadManager.query(query);
                    if (cursor.moveToFirst()) {
                        int colIndex = cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {

                            editorDownload.putBoolean("flag", true);
                            editorDownload.commit();

                            Toast.makeText(context, "New App downloaded!!", Toast.LENGTH_SHORT).show();
                            bi.lblAppVersion.setText("Academic Detailing Training App New Version " + newVer + "  Downloaded.");

                            ActivityManager am = (ActivityManager) getContext().getSystemService(ACTIVITY_SERVICE);
                            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                            if (taskInfo.get(0).topActivity.getClassName().equals(MainActivity.class.getName())) {
//                                InstallNewApp(newVer, preVer);
                                showDialog(newVer, preVer);
                            }
                        }
                    }
                }
            }
        };

        versionAppContract = new Gson().fromJson(getContext().getSharedPreferences("main", Context.MODE_PRIVATE).getString("appVersion", ""), VersionAppContract.class);
        if (versionAppContract != null) {

            if (versionAppContract.getVersioncode() != null) {
                preVer = MainApp.versionName + "." + MainApp.versionCode;
                newVer = versionAppContract.getVersionname() + "." + versionAppContract.getVersioncode();

                if (MainApp.versionCode < Integer.valueOf(versionAppContract.getVersioncode())) {
                    bi.lblAppVersion.setVisibility(View.VISIBLE);

                    String fileName = DatabaseHelper.DATABASE_NAME.replace(".db", "-New-Apps");
                    file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName, versionAppContract.getPathname());

                    if (file.exists()) {
                        bi.lblAppVersion.setText("Academic Detailing Training App New Version " + newVer + "  Downloaded.");
//                    InstallNewApp(newVer, preVer);
                        showDialog(newVer, preVer);
                    } else {
                        NetworkInfo networkInfo = ((ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {

                            bi.lblAppVersion.setText("Academic Detailing Training App New Version " + newVer + " Downloading..");
                            downloadManager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(MainApp._UPDATE_URL + versionAppContract.getPathname());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setDestinationInExternalPublicDir(fileName, versionAppContract.getPathname())
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setTitle("Downloading Academic Detailing Training App new App ver." + newVer);
                            refID = downloadManager.enqueue(request);

                            editorDownload.putLong("refID", refID);
                            editorDownload.putBoolean("flag", false);
                            editorDownload.commit();

                        } else {
                            bi.lblAppVersion.setText("Academic Detailing Training App New Version " + newVer + "  Available..\n(Can't download.. Internet connectivity issue!!)");
                        }
                    }

                } else {
                    bi.lblAppVersion.setVisibility(View.GONE);
                    bi.lblAppVersion.setText(null);
                }
            }
        }

        getContext().registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        //--------------------------------------------------------------------------------------------------------------------


    }

   /* private void syncingAppointment() {
        try {
            hud.show();

            Long dist_code = MainApp.dContract.getDICTRICT_CODE();
            JSONObject object = new JSONObject();
            object.put("dist_code", dist_code);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), object.toString());
            Call<ResponseBody> call = RetrofitClient.service.getAppointments(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response != null) {
                        if (response.body() != null) {
                            hud.dismiss();
                            try {
                                String data = response.body().string();
                                JSONArray array = new JSONArray(data);
                                db.syncAppointments(array);

                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    hud.dismiss();
                    Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/


    void showDialog(String newVer, String preVer) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        DialogFragment newFragment = MyDialogFragment.newInstance(newVer, preVer);
        newFragment.show(ft, "dialog");

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }

    public static class MyDialogFragment extends DialogFragment {

        String newVer, preVer;

        static MyDialogFragment newInstance(String newVer, String preVer) {
            MyDialogFragment f = new MyDialogFragment();

            Bundle args = new Bundle();
            args.putString("newVer", newVer);
            args.putString("preVer", preVer);
            f.setArguments(args);

            return f;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            newVer = getArguments().getString("newVer");
            preVer = getArguments().getString("preVer");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Academic Detailing Training App is available!")
                    .setMessage("Academic Detailing Training App " + newVer + " is now available. Your are currently using older version " + preVer + ".\nInstall new version to use this app.")
                    .setPositiveButton("INSTALL!!",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            }
                    )
                    .create();
        }

    }


}
