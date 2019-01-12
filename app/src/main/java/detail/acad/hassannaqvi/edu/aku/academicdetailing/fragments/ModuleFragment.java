package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentModuleBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;


public class ModuleFragment extends Fragment {

    View view;
    FragmentModuleBinding bi;
    boolean isChildClicked = false;
    boolean isMaternalClicked = false;
    boolean isChildSubClicked = false;
    String subModuleName;

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


        setOnClickListener(bi);

        return view;
    }

    private void setOnClickListener(final FragmentModuleBinding bi) {


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

                    MainApp.isChild = false;
                    MainApp.isMaternal = true;
                    MainApp.moduleName = "maternalHealth";
                    MainApp.moduleSession = Utils.maternalModule[finalI];
                    Utils.showPreDialogue(getActivity(), finalI, MainApp.moduleName);
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
            final LinearLayout subModule = v.findViewById(R.id.childSubModule);
            moduleName.setText(Utils.childModule[i]);
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
                                    Utils.showPreDialogue(getActivity(), finalI, subModuleName);
                                    MainApp.childlIndex = finalI1;
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
            subModuleName = "gds";
            return Utils.GDS;
        } else if (finalI == 1) {
            subModuleName = "cdb";
            return Utils.CDB;
        } else if (finalI == 2) {
            subModuleName = "dia";
            return Utils.Diarrhea;
        } else {
            subModuleName = "psbi";
            return Utils.PSBI;
        }
    }


}





