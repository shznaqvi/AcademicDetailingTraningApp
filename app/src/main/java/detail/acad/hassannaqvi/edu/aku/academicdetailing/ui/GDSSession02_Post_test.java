package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession02PostTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession02_Post_test extends AppCompatActivity {

    ActivityGdssession02PostTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession02__post_test);
        bi.setCallback(this);

        MainApp.fc.setPostTestStartTime(MainApp.getCurrentTime());
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
                    MainApp.endActivity(this, "Are You Sure You want to Continue?", true);

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
        int count = db.updatePostTest();
        if (count == 1) {
            return true;
        } else {
            Toast.makeText(this, "Error in update DB", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() {

        MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
        JSONObject sCdb01post = GeneratorClass.getContainerJSON(bi.fldGrpPostGds02, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01post));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gds02post01, bi.gds02post01a, getString(R.string.gds02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02post02, bi.gds02post02a, getString(R.string.gds02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02post03, bi.gds02post03a, getString(R.string.gds02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02post04, bi.gds02post04a, getString(R.string.gds02_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gds02post05, bi.gds02post05a, getString(R.string.gds02_05));
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
