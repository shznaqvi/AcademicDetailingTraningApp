package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityVbPreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.slides;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class VB_Pre_test extends AppCompatActivity {

    ActivityVbPreTestBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_vb__pre_test);
        bi.setCallback(this);


        type = getIntent().getStringExtra("type");
        if (type.equals("pre") && !isComplete) {
            bi.heading.setText("PRETEST");
            slides = getIntent().getIntArrayExtra("slides");
            Data.correctAnswers = getIntent().getStringArrayListExtra("ans");
            MainApp.fc.setPreTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);
        } else if (type.equals("pre") && isComplete) {
            bi.heading.setText("PRETEST RESULT");
            GeneratorClass.comparingResult(bi.fldGrpVbPre, true, Data.correctAnswers);
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnContinue.setVisibility(View.GONE);
        } else if (type.equals("post") && !isComplete) {
            bi.heading.setText("POST TEST");
            MainApp.fc.setPostTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);

        } else if (type.equals("post") && isComplete) {
            bi.heading.setText(" POST TEST & PRETEST RESULT");
            GeneratorClass.comparingPostTestAndPretestResult(bi.fldGrpVbPre,true,Data.correctAnswers);
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnContinue.setVisibility(View.GONE);
        }

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
                    if(type.equals("pre")){
                        if (MainApp.isSlideStart) {
                            startActivity(new Intent(this, VB_Pre_test.class).putExtra("type", type));
                            isComplete = true;
                            finish();
                        } else {
                            Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else if(type.equals("post")) {
                        startActivity(new Intent(this, VB_Pre_test.class).putExtra("type", type));
                        isComplete = true;
                        GeneratorClass.incr = 0;
                        finish();

                    }
                } else {
                    Toast.makeText(this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void BtnOk() {
        if (type.equals("pre")) {
            if (MainApp.isSlideStart) {
                MainApp.showDialog(this, getString(R.string.readyForTrain), "pre", false);
            } else {
                Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            MainApp.showDialog(this, getString(R.string.areYouSure), "end", true);
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        int count;
        if(type.equals("pre")){
             count = db.updatePreTest();
        }else{
             count = db.updatePostTest();
        }
        if (count == 1) {
            return true;
        } else {
            Toast.makeText(this, "Error in update DB", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() {

        if(type.equals("pre")){
            MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpVbPre, true,type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.fldGrpVbPre, true);
        }else{

            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpVbPre, true,type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.fldGrpVbPre, true);
        }

    }

    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.fldGrpVbPre);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
