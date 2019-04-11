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

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.CDB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.Diarrhea;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ECEB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ECSB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.GDS;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.HBB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.PSBI;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.childModule;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.maternalModule;
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
                        if (!MainApp.isScheduleAppointment) {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            getActivity().finish();
                        } else {
                            startActivity(new Intent(getContext(), MainActivity.class));
                            callbacks.uploadAppointment();
                            getActivity().finish();
                        }


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
            return true;
        } else {
            Toast.makeText(getContext(), "Error in updating DB", Toast.LENGTH_SHORT).show();
            return false;
        }


    }


    private void saveDraft() {


        MainApp.nmc = new NextMeetingContract();
        MainApp.nmc.setDate(bi.date.getText().toString());
        MainApp.nmc.setTime(bi.time.getText().toString());
        MainApp.nmc.setDoctorName(MainApp.providerName);
        MainApp.nmc.setModule(bi.modules.getSelectedItem().toString());
        if (bi.modules.getSelectedItemPosition() == 1) {
            MainApp.nmc.setSubModule(bi.subModules.getSelectedItem().toString());
        }
        MainApp.nmc.setSession(bi.sessions.getSelectedItem().toString());
        MainApp.nmc.setDoBooking(MainApp.getCurrentTime()); // Timestamp is converted to date above
        MainApp.nmc.setBookBy(MainApp.userName);
        MainApp.nmc.setBookingtype(bi.bookingType.getSelectedItem().toString());
        MainApp.nmc.setFormdate(new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));

        MainApp.nmc.setDist_id(Long.parseLong(MainApp.fc.getDistrictID()));
        MainApp.nmc.setHf_name(MainApp.fc.getHealthFacilityName());
        MainApp.nmc.setHp_name(MainApp.fc.getProviderName());
        MainApp.nmc.setHp_code(Long.parseLong(MainApp.fc.getProviderID()));
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

            MainApp.nmc.setLat(lat);
            MainApp.nmc.setLng(lang);
            MainApp.nmc.setGpsTime(date); // Timestamp is converted to date above
            // Timestamp is converted to date above

            Toast.makeText(getActivity(), "GPS set", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private boolean formValidate() {

        if (!validatorClass.EmptyTextBox(getContext(), bi.date, getString(R.string.date))) {
            return false;
        }

        if (!validatorClass.EmptyTextBox(getContext(), bi.time, getString(R.string.time))) {
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


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }
}
