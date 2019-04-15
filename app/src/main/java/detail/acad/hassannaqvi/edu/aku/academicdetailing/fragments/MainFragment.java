package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.RetrofitClient.RetrofitClient;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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


        isAdmin = getArguments().getBoolean("isAdmin");
        if (!isAdmin) {
            bi.openDB.setVisibility(View.GONE);
        }
        db = new DatabaseHelper(getContext());


        onClickListener();
        setupViews();
        return view;
    }

    private void setupViews() {
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

        bi.syncAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                syncingAppointment();
            }
        });

    }

    private void syncingAppointment() {
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
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }


}
