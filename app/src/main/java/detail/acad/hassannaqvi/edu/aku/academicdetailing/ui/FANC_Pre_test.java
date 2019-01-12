package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityFancPreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class FANC_Pre_test extends AppCompatActivity {

    ActivityFancPreTestBinding bi;
    String currentDateTime = new SimpleDateFormat(" dd/MM/yyyy HH:mm:ss").format(new Date().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_fanc__pre_test);
        bi.setCallback(this);

        MainApp.fc.setPreTestStartTime(currentDateTime);
        if (MainApp.isSlideStart) {
            bi.btnContinue.setText("Start Training");
        } else {
            bi.btnContinue.setText("Finish Training");
        }
    }


    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    if (MainApp.isSlideStart) {
                        startActivity(new Intent(this, ViewPagerActivity.class).putExtra("slides", getIntent().getIntArrayExtra("slides")));
                        finish();
                    } else {
                        Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        int count = db.updatePreTest();
        if (count == 1) {
            return true;
        } else {
            Toast.makeText(this, "Error in update DB", Toast.LENGTH_SHORT).show();
            return false;
        }


    }

    private void SaveDraft() throws JSONException {

        String currentDateTime = new SimpleDateFormat(" dd/MM/yyyy HH:mm:ss").format(new Date().getTime());
        MainApp.fc.setPreTestEndTime(currentDateTime);
        JSONObject sVb = GeneratorClass.getContainerJSON(bi.fldGrpPreFanc, true);
        MainApp.fc.setPre_test(String.valueOf(sVb));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.fancpre01, bi.fancpre01a, getString(R.string.fanc_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.fancpre02, bi.fancpre02a, getString(R.string.fanc_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.fancpre03, bi.fancpre03a, getString(R.string.fanc_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.fancpre04, bi.fancpre04a, getString(R.string.fanc_04))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.fancpre05, bi.fancpre05a, getString(R.string.fanc_05))) {
            return false;
        }if (!validatorClass.EmptyCardCheckBox(this, bi.fanc06,bi.fancpre06a, getString(R.string.fanc_06))) {
            return false;
        }
        return true;
    }

    public void BtnEnd() {


//        try {
//            SaveDraft();
//            if (UpdateDB()) {
////                MainApp.endActivity(this, this, EndingActivity.class, false, fc_4_5);
//            } else {
//                Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
}
