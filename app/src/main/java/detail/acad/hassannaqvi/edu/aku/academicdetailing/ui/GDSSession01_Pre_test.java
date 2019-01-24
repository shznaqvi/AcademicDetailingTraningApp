package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession01PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.slides;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class GDSSession01_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityGdssession01PreTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession01__pre_test);
        bi.setCallback(this);
        events_call();

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
            GeneratorClass.comparingResult(bi.fldGrpPreGds01, true, Data.correctAnswers);
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnContinue.setVisibility(View.GONE);
        } else if (type.equals("post") && !isComplete) {
            bi.heading.setText("POST TEST");
            MainApp.fc.setPostTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);

        } else if (type.equals("post") && isComplete) {
            bi.heading.setText(" POST TEST & PRETEST RESULT");
            GeneratorClass.comparingPostTestAndPretestResult(bi.fldGrpPreGds01,true,Data.correctAnswers);
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnContinue.setVisibility(View.GONE);
        }
        if (MainApp.isSlideStart) {
            bi.btnContinue.setText("Start Training");
        } else {
            bi.btnContinue.setText("Finish Training");
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

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    if(type.equals("pre")){
                        if (MainApp.isSlideStart) {
                            startActivity(new Intent(this, GDSSession01_Pre_test.class).putExtra("type", type));
                            isComplete = true;
                            finish();
                        } else {
                            Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else if(type.equals("post")) {
                        startActivity(new Intent(this, GDSSession01_Pre_test.class).putExtra("type", type));
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
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreGds01, true,type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreGds01, true);
        }else{
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreGds01, true,type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreGds01, true);
        }
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gdsa01, bi.gdsa01a, getString(R.string.gds01_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gdsa02, bi.gdsa02a, getString(R.string.gds01_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gdsa03, bi.gdsa03a, getString(R.string.gds01_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gdsa04, bi.gdsa04a, getString(R.string.gds01_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gdsa05, bi.gdsa05a, getString(R.string.gds01_05));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //GDS-Q1
        if (compoundButton.getId() == R.id.gdsa01a
                || compoundButton.getId() == R.id.gdsa01b
                || compoundButton.getId() == R.id.gdsa01c
                || compoundButton.getId() == R.id.gdsa01d) {

            if (bi.gdsa01a.isChecked()) {
                bi.tvgdsa01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Mild illness</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgdsa01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);


                /*Entity entity;
                entity = (Entity) Document.getDoctype().getEntities().item(i);
                String path = entity.getSystemId();*/

                /*txt_gdspretestq1.setText(getString(R.string.gdspretestq1));*/

            } else if (bi.gdsa01b.isChecked()) {
                bi.tvgdsa01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Pneumonia</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgdsa01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa01c.isChecked()) {
                bi.tvgdsa01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Dehydration</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgdsa01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa01d.isChecked()) {
                bi.tvgdsa01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Severe disease/classification</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgdsa01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q2
        if (compoundButton.getId() == R.id.gdsa02a
                || compoundButton.getId() == R.id.gdsa02b
                || compoundButton.getId() == R.id.gdsa02c
                || compoundButton.getId() == R.id.gdsa02d) {

            if (bi.gdsa02a.isChecked()) {
                bi.tvgdsa02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Persistent diarrhea</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgdsa02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa02b.isChecked()) {
                bi.tvgdsa02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe dehydration</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgdsa02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa02c.isChecked()) {
                bi.tvgdsa02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe persistent diarrhea</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgdsa02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa02d.isChecked()) {
                bi.tvgdsa02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe pneumonia</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgdsa02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q3
        if (compoundButton.getId() == R.id.gdsa03a
                || compoundButton.getId() == R.id.gdsa03b
                || compoundButton.getId() == R.id.gdsa03c
                || compoundButton.getId() == R.id.gdsa03d) {

            if (bi.gdsa03a.isChecked()) {
                bi.tvgdsa03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>One</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgdsa03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa03b.isChecked()) {
                bi.tvgdsa03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>Two</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgdsa03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa03c.isChecked()) {
                bi.tvgdsa03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>Three</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgdsa03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa03d.isChecked()) {
                bi.tvgdsa03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>None</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgdsa03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q4
        if (compoundButton.getId() == R.id.gdsa04a
                || compoundButton.getId() == R.id.gdsa04b
                || compoundButton.getId() == R.id.gdsa04c
                || compoundButton.getId() == R.id.gdsa04d) {

            if (bi.gdsa04a.isChecked()) {
                bi.tvgdsa04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>One</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvgdsa04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa04b.isChecked()) {
                bi.tvgdsa04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Two</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvgdsa04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa04c.isChecked()) {
                bi.tvgdsa04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Three</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvgdsa04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsa04d.isChecked()) {
                bi.tvgdsa04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Three</i></b></font> questions and LOOK for <font color='yellow'><b><i>One</i></b></font> observations to observe the child's actions.";
                bi.tvgdsa04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.gdsa01a.setOnCheckedChangeListener(this);
        bi.gdsa01b.setOnCheckedChangeListener(this);
        bi.gdsa01c.setOnCheckedChangeListener(this);
        bi.gdsa01d.setOnCheckedChangeListener(this);

        bi.gdsa02a.setOnCheckedChangeListener(this);
        bi.gdsa02b.setOnCheckedChangeListener(this);
        bi.gdsa02c.setOnCheckedChangeListener(this);
        bi.gdsa02d.setOnCheckedChangeListener(this);

        bi.gdsa03a.setOnCheckedChangeListener(this);
        bi.gdsa03b.setOnCheckedChangeListener(this);
        bi.gdsa03c.setOnCheckedChangeListener(this);
        bi.gdsa03d.setOnCheckedChangeListener(this);

        bi.gdsa04a.setOnCheckedChangeListener(this);
        bi.gdsa04b.setOnCheckedChangeListener(this);
        bi.gdsa04c.setOnCheckedChangeListener(this);
        bi.gdsa04d.setOnCheckedChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}