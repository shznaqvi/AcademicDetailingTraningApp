package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentModuleBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.CDB;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.Diarrhea;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.GDS;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.PSBI;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.childModule;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.maternalModule;


public class ModuleFragment extends Fragment {

    View view;
    FragmentModuleBinding bi;
    boolean isChildClicked = false;
    boolean isMaternalClicked = false;
    boolean isChildSubClicked = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bi = DataBindingUtil.inflate(inflater, R.layout.fragment_module, container, false);
        view = bi.getRoot();

        MainApp.hideKeyboard(getActivity());


        setOnClickListener();

        return view;
    }

    private void setOnClickListener() {


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

        for (int i = 0; i < maternalModule.length; i++) {

            View v = LayoutInflater.from(getContext()).inflate(R.layout.single_module_item, null);
            TextView moduleName = v.findViewById(R.id.moduleName);
            moduleName.setText(maternalModule[i]);
            bi.maternalModule.addView(v);
            v.animate().translationY(v.getHeight());


            final int finalI = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainApp.moduleName = "maternalHealth";
                    MainApp.isChild = false;
                    MainApp.isMaternal = true;
                    MainApp.moduleSession = maternalModule[finalI];
                    if(Utils.selectTest(finalI,MainApp.moduleName) != null){
                        Utils.showPreDialogue(getActivity(), finalI, MainApp.moduleName);
                        MainApp.maternalIndex = finalI;
                    }else{
                        Toast.makeText(getActivity(), "Module is under Development", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
        isMaternalClicked = true;

    }

    private void showChildModule() {

        for (int i = 0; i < childModule.length; i++) {

            View v = LayoutInflater.from(getContext()).inflate(R.layout.single_module_item, null);
            TextView moduleName = v.findViewById(R.id.moduleName);
            final LinearLayout subModule = v.findViewById(R.id.childSubModule);
            moduleName.setText(childModule[i]);
            bi.childModule.addView(v);
            final int finalI = i;
            v.animate().translationY(v.getHeight());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!isChildSubClicked) {
                        for (int i = 0; i < selectSubModule(finalI).length; i++) {
                            View vi = LayoutInflater.from(getContext()).inflate(R.layout.single_sub_module_item, null);
                            TextView moduleName = vi.findViewById(R.id.subModuleName);
                            moduleName.setText(selectSubModule(finalI)[i]);
                            subModule.addView(vi);


                            final int finalI1 = i;
                            vi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    MainApp.moduleName = "childHealth";
                                    MainApp.moduleSession = selectSubModule(finalI)[finalI1];
                                    MainApp.isChild = true;
                                    MainApp.isMaternal = false;
                                    if(Utils.selectTest(finalI1,MainApp.subModuleName) != null){
                                        Utils.showPreDialogue(getActivity(), finalI1,  MainApp.subModuleName);
                                        MainApp.childlIndex = finalI1;
                                    }else{
                                        Toast.makeText(getActivity(), "Module is under Development", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }
                        isChildSubClicked = true;
                    } else {
                        subModule.removeAllViews();
                        isChildSubClicked = false;
                    }


                }
            });
        }
        isChildClicked = true;

    }

    public String[] selectSubModule(int finalI) {
        if (finalI == 0) {
            MainApp.subModuleName = "gds";
            return GDS;
        } else if (finalI == 1) {
            MainApp.subModuleName = "cdb";
            return CDB;
        } else if (finalI == 2) {
            MainApp.subModuleName = "dia";
            return Diarrhea;
        } else {
            MainApp.subModuleName = "psbi";
            return PSBI;
        }
    }


}





