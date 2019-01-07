package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters.Adapter;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityMainBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    ActivityMainBinding bi;
    boolean isChildClicked = false;
    boolean isMaternalClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);


        bi.childHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!isChildClicked) {
                    showChildModule();
                } else {
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
                    bi.maternalModule.removeAllViews();

                    isMaternalClicked = false;
                }

            }
        });

        bi.newBornHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNewBornModule();
            }
        });


    }

    private void showNewBornModule() {
    }

    private void showMaternalModule() {

        for (int i = 0; i < Utils.maternalModule.length; i++) {

            View v = LayoutInflater.from(this).inflate(R.layout.single_module_item, null);
            TextView moduleName = v.findViewById(R.id.moduleName);
            moduleName.setText(Utils.maternalModule[i]);
            bi.maternalModule.addView(v);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
        isMaternalClicked = true;

    }

    private void showChildModule() {

        for (int i = 0; i < Utils.childModule.length; i++) {

            View v = LayoutInflater.from(this).inflate(R.layout.single_module_item, null);
            TextView moduleName = v.findViewById(R.id.moduleName);
            moduleName.setText(Utils.childModule[i]);
            bi.childModule.addView(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
        isChildClicked = true;

    }


}
