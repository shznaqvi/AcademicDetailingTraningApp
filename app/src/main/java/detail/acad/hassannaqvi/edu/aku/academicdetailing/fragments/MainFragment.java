package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.MainActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ViewPagerActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;


public class MainFragment extends Fragment {

    View view;
    FragmentMainBinding bi;
    boolean isChildClicked = false;
    boolean isMaternalClicked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bi = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        view = bi.getRoot();


        setOnClickListener(bi);

        return view;
    }

    private void setOnClickListener(final FragmentMainBinding bi) {


        bi.childHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isChildClicked) {
                    showChildModule();

                } else {
                    bi.childModule.animate().translationY(0);
                    bi.childModule.removeAllViews();


                    isChildClicked = false;
                }

            }
        });

        bi.maternalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isMaternalClicked) {

                    showMaternalModule();
                } else {
                    bi.maternalModule.animate().translationY(0);
                    bi.maternalModule.removeAllViews();
                    isMaternalClicked = false;
                }

            }
        });
    }


    private void showMaternalModule() {

        for (int i = 0; i < Utils.maternalModule.length; i++) {

            View v = LayoutInflater.from(getContext()).inflate(R.layout.single_module_item, null);
            TextView moduleName = v.findViewById(R.id.moduleName);
            moduleName.setText(Utils.maternalModule[i]);
            bi.maternalModule.addView(v);
            v.animate().translationY(v.getHeight());


            final int finalI = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Utils.showMaternalPreDialogue(getActivity(), finalI);
                    MainApp.maternalIndex = finalI;

                }
            });
        }
        isMaternalClicked = true;

    }

    private void showChildModule() {

        for (int i = 0; i < Utils.childModule.length; i++) {

            View v = LayoutInflater.from(getContext()).inflate(R.layout.single_module_item, null);
            TextView moduleName = v.findViewById(R.id.moduleName);
            moduleName.setText(Utils.childModule[i]);
            bi.childModule.addView(v);
            v.animate().translationY(v.getHeight());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(), "Module is under development", Toast.LENGTH_SHORT).show();

                }
            });
        }
        isChildClicked = true;

    }


}





