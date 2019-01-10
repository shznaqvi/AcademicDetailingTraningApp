package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentInfoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;


public class InfoFragment extends Fragment {


    FragmentInfoBinding bi;
    View view;
    Callbacks callbacks;



    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bi = DataBindingUtil.inflate(inflater,R.layout.fragment_info,container,false);
        view = bi.getRoot();

        onCLickListener(bi);
        return view;

    }

    private void onCLickListener(FragmentInfoBinding bi) {

        bi.proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callbacks.loadModuleFragment();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }
}
