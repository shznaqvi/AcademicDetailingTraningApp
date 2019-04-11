package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;


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
        districtName = getArguments().getString("district_name");
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

        bi.userName.setText("Hello! " + MainApp.userName);
        bi.syncedForm.setText(String.valueOf(db.getUnsyncedForms().size()));
        bi.formsToday.setText(String.valueOf(db.getTodayForms().size()));
        bi.districtName.setText(districtName);

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

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }


}
