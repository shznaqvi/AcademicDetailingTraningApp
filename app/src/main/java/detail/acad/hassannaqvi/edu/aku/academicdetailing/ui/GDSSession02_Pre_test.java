package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession02PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession02_Pre_test extends AppCompatActivity {

    ActivityGdssession02PreTestBinding bi;
    private static final String TAG = "GDSSession02_Pre_test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession02__pre_test);
        bi.setCallback(this);

        MainApp.fc.setPreTestStartTime(MainApp.getCurrentTime());
        if(MainApp.isSlideStart){
            bi.btnContinue.setText("Start Training");
        }else{
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

    private void SaveDraft() {

        MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
        JSONObject sCdb01pre = GeneratorClass.getContainerJSON(bi.fldGrpPreGds02, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01pre));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre01, bi.gds02pre01a, getString(R.string.gds02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre02, bi.gds02pre02a, getString(R.string.gds02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre03, bi.gds02pre03a, getString(R.string.gds02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre04, bi.gds02pre04a, getString(R.string.gds02_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gds02pre05, bi.gds02pre05a, getString(R.string.gds02_05));
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
