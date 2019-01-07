package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession02PreTestBinding;

public class GDSSession02_Pre_test extends AppCompatActivity {

    ActivityGdssession02PreTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession02__pre_test);
        bi.setCallback(this);
    }

    public void BtnContinue() {
        if (formValidation()) {
//            try {
//                SaveDraft();
//                if (UpdateDB()) {
//                    startActivity(new Intent(getApplicationContext(), Form02HHPart_1.class));
//                } else {
//                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }

    private boolean UpdateDB() {

        return true;
    }

    private void SaveDraft() {
    }

    private boolean formValidation() {
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
