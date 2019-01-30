package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FragmentMainBinding bi;
    View view;
    Callbacks callbacks;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bi = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        view = bi.getRoot();


        onClickListener();
        return view;
    }

    private void onClickListener() {

        bi.startTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callbacks.loadInfoFragment();

            }
        });

        bi.openDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callbacks.loadDatabaseManager();
            }
        });

        bi.uploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callbacks.uploadDataToServer();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }
}
