package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.NextMeetingContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentScheduleBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.MainActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static android.content.Context.MODE_PRIVATE;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.CDB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.CDBMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.DiaMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.Diarrhea;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ECEB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ECEBMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ECSB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ECSBMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.GDS;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.GDSMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.HBB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.PSBI;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.PSBIMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.childModule;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.maternalMap;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.maternalModule;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.modulesCode;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.newBornModule;

public class ScheduleFragment extends Fragment {

    public ArrayList<String> modules;
    public ArrayList<String> subModules;
    public ArrayList<String> sessions;
    public ArrayList<String> bookingType;
    FragmentScheduleBinding bi;
    View view;
    DatabaseHelper db;
    Callbacks callbacks;
    String moduleCode, sessionCode;


    public ScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentdate
        bi = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);

        db = new DatabaseHelper(getContext());
        view = bi.getRoot();
        MainApp.nmc = new NextMeetingContract();
        bi.doctorName.setText(MainApp.providerName);
        modules = new ArrayList<>();
        subModules = new ArrayList<>();
        sessions = new ArrayList<>();
        bookingType = new ArrayList<>();
        modules.add("-Select Module-");
        subModules.add("-Select Sub Module-");
        sessions.add("-Select Sessions-");
        bookingType.add("-Select Booking Type-");
        bookingType.add("Over Phone");
        bookingType.add("At Health Facility");

        populateSpinner();
        onClickListener();


        return view;
    }

    private void onClickListener() {

        bi.saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formValidate()) {
                    saveDraft();
                    if (updateDB()) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "Error in database", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public boolean updateDB() {
        DatabaseHelper db = new DatabaseHelper(getContext());
        long count = db.updateNMS();
        if (count != -1) {
            if (count > 0) {
                MainApp.nmc.set_id(String.valueOf(count));
                MainApp.nmc.set_UID((MainApp.nmc.getDeviceid() + MainApp.nmc.get_id()));
                db.updateNMCFormID(MainApp.nmc);
            }
            return true;
        } else {
            Toast.makeText(getContext(), "Error in updating DB", Toast.LENGTH_SHORT).show();
            return false;
        }


    }


    private void saveDraft() {
        SharedPreferences sharedPref = getContext().getSharedPreferences("tagName", MODE_PRIVATE);

        MainApp.nmc.setBook_date(bi.bookDate.getText().toString());
        MainApp.nmc.setBook_time(bi.bookTime.getText().toString());
        MainApp.nmc.setDoctorName(MainApp.providerName);
        MainApp.nmc.setModule(moduleCode);

        MainApp.nmc.setSession(sessionCode);
        MainApp.nmc.setBookBy(MainApp.userName);
        MainApp.nmc.setBookingtype(bi.bookingType.getSelectedItemPosition() == 0 ? "0" : "1");
        MainApp.nmc.setFormdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        MainApp.nmc.setDeviceid(MainApp.deviceId);

        MainApp.nmc.setDist_id(Long.parseLong(MainApp.fc.getDistrictID()));
        MainApp.nmc.setHf_name(MainApp.fc.getHealthFacilityName());
        MainApp.nmc.setHp_name(MainApp.fc.getProviderName());
        MainApp.nmc.setHp_code(Long.parseLong(MainApp.fc.getProviderID()));
        MainApp.nmc.setDevicetagID(sharedPref.getString("tagName", null));
        MainApp.nmc.setUsername(MainApp.userName);


        setGPS();


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

            MainApp.nmc.setGpsLat(lat);
            MainApp.nmc.setGpsLng(lang);
            MainApp.nmc.setGps_time(date); // Timestamp is converted to book_date above
            // Timestamp is converted to book_date above

            Toast.makeText(getActivity(), "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private boolean formValidate() {

        if (!validatorClass.EmptyTextBox(getContext(), bi.bookDate, getString(R.string.book_date))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(getContext(), bi.bookTime, getString(R.string.book_time))) {
            return false;
        }
        if (!validatorClass.EmptySpinner(getContext(), bi.modules, "Module")) {
            return false;
        }
        if (bi.modules.getSelectedItemPosition() == 0) {
            if (!validatorClass.EmptySpinner(getContext(), bi.subModules, "Sub Module")) {
                return false;
            }
        }
        if (!validatorClass.EmptySpinner(getContext(), bi.sessions, "Sessions")) {
            return false;
        }
        return validatorClass.EmptySpinner(getContext(), bi.bookingType, "Booking Type");
    }

    private void populateSpinner() {

        bi.bookingType.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, bookingType));
        bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
        modules.addAll(Arrays.asList(Data.modules));
        bi.modules.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, modules));
        bi.modules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    sessions.clear();
                    sessions.add("-Select Sessions-");
                    subModules.clear();
                    subModules.add("-Select Sub Module-");
                    selectModule(position);
                    moduleCode = modulesCode[position];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void selectModule(int position) {

        switch (position) {
            case 1:
                bi.fldGrpSubModule.setVisibility(View.VISIBLE);
                subModules.addAll(Arrays.asList(childModule));
                bi.subModules.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, subModules));
                bi.subModules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {

                            sessions.clear();
                            sessions.add("-Select Sessions-");
                            selectSubModule(position, "child");
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;
            case 2:
                sessions.addAll(Arrays.asList(maternalModule));
                bi.subModules.setSelection(0);
                bi.fldGrpSubModule.setVisibility(View.GONE);
                bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                bi.sessions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        sessionCode = maternalMap.get(bi.sessions.getSelectedItem().toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;

            case 3:

                bi.fldGrpSubModule.setVisibility(View.VISIBLE);
                subModules.addAll(Arrays.asList(newBornModule));
                bi.subModules.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, subModules));
                bi.subModules.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position != 0) {
                            sessions.clear();
                            sessions.add("-Select Sessions-");
                            selectSubModule(position, "newBorn");
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                break;


        }

    }

    private void selectSubModule(int position, String type) {

        if (type.equals("child")) {
            switch (position) {
                case 1:
                    sessions.addAll(Arrays.asList(GDS));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));

                    break;

                case 2:
                    sessions.addAll(Arrays.asList(CDB));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                    break;

                case 3:
                    sessions.addAll(Arrays.asList(Diarrhea));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                    break;

                case 4:
                    sessions.addAll(Arrays.asList(PSBI));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                    break;
            }

        } else if (type.equals("newBorn")) {
            switch (position) {
                case 1:
                    sessions.addAll(Arrays.asList(ECEB));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                    break;

                case 2:
                    sessions.addAll(Arrays.asList(ECSB));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                    break;

                case 3:
                    sessions.addAll(Arrays.asList(HBB));
                    bi.sessions.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sessions));
                    break;

            }
        }
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int selectedItem, long id) {
                if (type.equals("child")) {
                    switch (position) {
                        case 1:
                            sessionCode = GDSMap.get(bi.sessions.getSelectedItem().toString());
                            break;
                        case 2:
                            sessionCode = CDBMap.get(bi.sessions.getSelectedItem().toString());
                            break;
                        case 3:
                            sessionCode = DiaMap.get(bi.sessions.getSelectedItem().toString());
                            break;
                        case 4:
                            sessionCode = PSBIMap.get(bi.sessions.getSelectedItem().toString());
                            break;
                    }
                } else if (type.equals("newBorn")) {
                    switch (position) {
                        case 1:
                            sessionCode = ECEBMap.get(bi.sessions.getSelectedItem().toString());
                            break;
                        case 2:
                            sessionCode = ECSBMap.get(bi.sessions.getSelectedItem().toString());
                            break;
                        case 3:
                            sessionCode = "30301";
                            break;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        bi.sessions.setOnItemSelectedListener(listener);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }
}
