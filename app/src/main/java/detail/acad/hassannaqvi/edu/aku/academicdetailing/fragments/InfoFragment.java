package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentInfoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;


public class InfoFragment extends Fragment {


    FragmentInfoBinding bi;
    View view;
    Callbacks callbacks;

    private static final String TAG = "InfoFragment";



    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bi = DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,false);
        view = bi.getRoot();

        onCLickListener();
        return view;

    }

    private void onCLickListener() {

        bi.proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(formValidation()){
                    try {
                        saveDraft();
                        if(updateDB()){
                            callbacks.loadModuleFragment();
                        }else{
                            Toast.makeText(getActivity(), "Error in update DB", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        });

    }

    private boolean updateDB() {

        DatabaseHelper db = new DatabaseHelper(getActivity());

        long rowId = db.addForm(MainApp.fc);
        MainApp.fc.set_ID(rowId);

        return true;
    }

    private void saveDraft() {

        MainApp.fc = new FormsContract();
        MainApp.fc.setProviderID(bi.providerId.getText().toString());
        MainApp.fc.setProviderName(bi.providerNam.getText().toString());
        MainApp.fc.setHealthFacilityName(bi.info2.getText().toString());
        MainApp.fc.setDistrictID(bi.info1.getText().toString());
        MainApp.fc.setAppversion(MainApp.versionName);
        MainApp.fc.setLogginTime(MainApp.logginTime);
        MainApp.fc.setDeviceID(MainApp.deviceId);
        setGPS();
    }

    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(getActivity(), bi.fldGrpInfo);
    }

    public void setGPS() {
        SharedPreferences GPSPref = getActivity().getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);

        try {
            String lat = GPSPref.getString("Latitude", "0");
            String lang = GPSPref.getString("Longitude", "0");
            String acc = GPSPref.getString("Accuracy", "0");
            String dt = GPSPref.getString("Time", "0");

            if (lat.equals("0") && lang.equals("0")) {
                Toast.makeText(getActivity(), "Could not obtained GPS points", Toast.LENGTH_SHORT).show();
            }

            String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(dt)).toString();

            MainApp.fc.setGpsLat(lat);
            MainApp.fc.setGpsLng(lang);
            MainApp.fc.setGpsAcc(acc);
            MainApp.fc.setGpsTime(date); // Timestamp is converted to date above

            Toast.makeText(getActivity(), "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.e(TAG, "setGPS: " + e.getMessage());
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }
}
