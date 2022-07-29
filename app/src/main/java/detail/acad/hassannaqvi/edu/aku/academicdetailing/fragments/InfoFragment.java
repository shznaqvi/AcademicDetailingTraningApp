package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.validatorcrawler.aliazaz.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentInfoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.District;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Forms;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.HealthFacility;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.HealthProvider;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ScheduleActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.HideKeyboard;


public class InfoFragment extends Fragment {


    FragmentInfoBinding bi;
    View view;
    Callbacks callbacks;
    Collection<District> distrcitList;
    ArrayList<String> districtNames;
    ArrayList<String> districtCode;
    //Long districtCode;
    HashMap<String, String> districtMap;

    Collection<HealthFacility> hfList;
    ArrayList<String> hfNames;
    ArrayList<String> hfCodes;
    HashMap<String, String> hfMap;

    Collection<HealthProvider> healthProviderList;
    ArrayList<String> providerNames;
    ArrayList<String> providerCodes;
    HashMap<String, String> providerMap;

    String hf_uen_code ="";
    long hp_code = 0;

    private static final String TAG = "InfoFragment";
    DatabaseHelper db;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        bi = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        view = bi.getRoot();
        db = new DatabaseHelper(getContext());

        onCLickListener();

        setupViews();
        return view;

    }

    private void setupViews() {

        //bi.districtSpinner.setT(MainApp.district.getDistrict_name());


        distrcitList = db.getDistrictList();
        districtNames = new ArrayList<>();
        districtCode = new ArrayList<>();
        //districtCode = 0L;
        districtMap = new HashMap<>();
        districtNames.add("-Select District-");

        for (District dc : distrcitList) {

            districtNames.add(dc.getDistrict_name());
            districtCode.add(dc.getDICTRICT_CODE());
            //districtMap.put(dc.getDistrict_name(), dc.getDICTRICT_CODE());
        }

        bi.districtSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, districtNames));

        bi.districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (bi.districtSpinner.getSelectedItemPosition() != 0) {

                    //districtCode = districtMap.get(bi.districtSpinner.getSelectedItem().toString());
                    hfList = db.getHealthFacilityData(MainApp.user.getDist_id());
                    hfNames = new ArrayList<>();
                    hfCodes = new ArrayList<>();
                    hfNames.add("...");
                    hfCodes.add("...");

                    for (HealthFacility hf : hfList) {
                        hfNames.add(hf.getHf_name());
                        hfCodes.add(hf.getHf_uen_code());
                    }

                    bi.hfName.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, hfNames));


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*hfList = db.getHealthFacilityData(MainApp.district.getDICTRICT_CODE());
        hfMap = new HashMap<>();
        hfNames = new ArrayList<>();
        hfNames.add("...");
        hfCodes.add("...");
        for (HealthFacility hf : hfList) {
            hfNames.add(hf.getHf_name());
            hfMap.put(hf.getHf_name(), hf.getHf_uen_code());
        }

        bi.hfName.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, hfNames));
*/
        bi.hfName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (bi.hfName.getSelectedItemPosition() != 0) {

                    if (position < hfNames.size() && position < hfCodes.size()) {
                        String selectedCode = hfCodes.get(position);
                        healthProviderList = db.getHealthProviderByFacility(selectedCode);
                        providerNames = new ArrayList<>();
                        providerCodes = new ArrayList<>();
                        providerNames.add("...");
                        providerCodes.add("...");

                        for (HealthProvider hp : healthProviderList) {
                            providerNames.add(hp.getHp_name());
                            providerCodes.add(hp.getHp_uen_code());
                        }

                        bi.hpName.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, providerNames));

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /*healthProviderList = db.getHPData(MainApp.facilityName);
        providerNames = new ArrayList<>();
        providerMap = new HashMap<>();
        providerNames.add("-Select HealthProvider Name-");

        for (HealthProvider pC : healthProviderList) {

            providerNames.add(pC.getHp_name());
            providerMap.put(pC.getHp_name(), pC.getHf_code());

        }*/

        bi.hpName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (bi.hpName.getSelectedItemPosition() != 0) {

                    hp_code = bi.hpName.getSelectedItemPosition();
                    //MainApp.providerName = bi.hpName.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void onCLickListener() {

        bi.proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (formValidation()) {
                    try {
                        saveDraft();
                        if (MainApp.isScheduleAppointment) {
                            //  callbacks.loadScheduleFragment();
                            startActivity(new Intent(getActivity(), ScheduleActivity.class));
                        } else {
                            HideKeyboard.hideKeyboardFragment(getActivity(), getView());
                            callbacks.loadModuleFragment(MainApp.forms);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });

    }

    private void saveDraft() {

        MainApp.forms = new Forms();
        MainApp.forms.setAppversion(MainApp.versionName + "." + MainApp.versionCode);
        MainApp.forms.setLogginTime(MainApp.logginTime);
        MainApp.forms.setDeviceID(MainApp.deviceId);
        MainApp.forms.setFormDate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.forms.setProviderID(bi.providerId.getText().toString());
        MainApp.forms.setProviderName(bi.hpName.getSelectedItem().toString());
        MainApp.forms.setHealthFacilityCode(String.valueOf(hfMap.get(bi.hfName.getSelectedItem().toString())));
        MainApp.forms.setDistrictID(String.valueOf(MainApp.district.getDICTRICT_CODE()));
        MainApp.providerName = bi.hpName.getSelectedItem().toString();

        setGPS();
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(getActivity(), bi.fldGrpInfo);
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

            MainApp.forms.setGpsLat(lat);
            MainApp.forms.setGpsLng(lang);
            MainApp.forms.setGpsAcc(acc);
            MainApp.forms.setGpsTime(date); // Timestamp is converted to book_date above

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
