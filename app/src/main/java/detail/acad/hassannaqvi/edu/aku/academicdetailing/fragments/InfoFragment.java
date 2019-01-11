package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentInfoBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;


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

        return true;
    }

    private void saveDraft() throws JSONException  {

        JSONObject sInfo = new JSONObject();
    }

    private boolean formValidation() {

        if(!validatorClass.EmptyCheckingContainer(getActivity(),bi.fldGrpInfo)){
            return false;
        }
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callbacks = (Callbacks) context;
    }
}
