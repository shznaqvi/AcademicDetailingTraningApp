package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession01_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityGdssession01PreTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession01__pre_test);
        bi.setCallback(this);

        events_call();

        MainApp.fc.setPreTestStartTime(MainApp.getCurrentTime());
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
            } catch (Exception e) {
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
        JSONObject sCdb01pre = GeneratorClass.getContainerJSON(bi.fldGrpPreGds01, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01pre));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gds01pre01, bi.gds01pre01a, getString(R.string.gds01_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds01pre02, bi.gds01pre02a, getString(R.string.gds01_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds01pre03, bi.gds01pre03a, getString(R.string.gds01_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds01pre04, bi.gds01pre04a, getString(R.string.gds01_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gds01pre05, bi.gds01pre05a, getString(R.string.gds01_05));
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //GDS-Q1
        if (compoundButton.getId() == R.id.gds01pre01a
                || compoundButton.getId() == R.id.gds01pre01b
                || compoundButton.getId() == R.id.gds01pre01c
                || compoundButton.getId() == R.id.gds01pre01d) {

            if (bi.gds01pre01a.isChecked()) {
                bi.tvGds01pre01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Mild illness</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvGds01pre01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);


                /*Entity entity;
                entity = (Entity) Document.getDoctype().getEntities().item(i);
                String path = entity.getSystemId();*/

                /*txt_gdspretestq1.setText(getString(R.string.gdspretestq1));*/

            } else if (bi.gds01pre01b.isChecked()) {
                bi.tvGds01pre01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Pneumonia</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvGds01pre01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre01c.isChecked()) {
                bi.tvGds01pre01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Dehydration</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvGds01pre01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre01d.isChecked()) {
                bi.tvGds01pre01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Severe disease/classification</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvGds01pre01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q2
        if (compoundButton.getId() == R.id.gds01pre02a
                || compoundButton.getId() == R.id.gds01pre02b
                || compoundButton.getId() == R.id.gds01pre02c
                || compoundButton.getId() == R.id.gds01pre02d) {

            if (bi.gds01pre02a.isChecked()) {
                bi.tvGds01pre02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Persistent diarrhea</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvGds01pre02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre02b.isChecked()) {
                bi.tvGds01pre02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe dehydration</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvGds01pre02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre02c.isChecked()) {
                bi.tvGds01pre02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe persistent diarrhea</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvGds01pre02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre02d.isChecked()) {
                bi.tvGds01pre02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe pneumonia</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvGds01pre02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q3
        if (compoundButton.getId() == R.id.gds01pre03a
                || compoundButton.getId() == R.id.gds01pre03b
                || compoundButton.getId() == R.id.gds01pre03c
                || compoundButton.getId() == R.id.gds01pre03d) {

            if (bi.gds01pre03a.isChecked()) {
                bi.tvGds01pre03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>One</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvGds01pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre03b.isChecked()) {
                bi.tvGds01pre03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>Two</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvGds01pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre03c.isChecked()) {
                bi.tvGds01pre03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>Three</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvGds01pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre03d.isChecked()) {
                bi.tvGds01pre03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>None</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvGds01pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q4
        if (compoundButton.getId() == R.id.gds01pre04a
                || compoundButton.getId() == R.id.gds01pre04b
                || compoundButton.getId() == R.id.gds01pre04c
                || compoundButton.getId() == R.id.gds01pre04d) {

            if (bi.gds01pre04a.isChecked()) {
                bi.tvGds01pre04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>One</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvGds01pre04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre04b.isChecked()) {
                bi.tvGds01pre04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Two</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvGds01pre04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre04c.isChecked()) {
                bi.tvGds01pre04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Three</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvGds01pre04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01pre04d.isChecked()) {
                bi.tvGds01pre04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Three</i></b></font> questions and LOOK for <font color='yellow'><b><i>One</i></b></font> observations to observe the child's actions.";
                bi.tvGds01pre04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.gds01pre01a.setOnCheckedChangeListener(this);
        bi.gds01pre01b.setOnCheckedChangeListener(this);
        bi.gds01pre01c.setOnCheckedChangeListener(this);
        bi.gds01pre01d.setOnCheckedChangeListener(this);

        bi.gds01pre02a.setOnCheckedChangeListener(this);
        bi.gds01pre02b.setOnCheckedChangeListener(this);
        bi.gds01pre02c.setOnCheckedChangeListener(this);
        bi.gds01pre02d.setOnCheckedChangeListener(this);

        bi.gds01pre03a.setOnCheckedChangeListener(this);
        bi.gds01pre03b.setOnCheckedChangeListener(this);
        bi.gds01pre03c.setOnCheckedChangeListener(this);
        bi.gds01pre03d.setOnCheckedChangeListener(this);

        bi.gds01pre04a.setOnCheckedChangeListener(this);
        bi.gds01pre04b.setOnCheckedChangeListener(this);
        bi.gds01pre04c.setOnCheckedChangeListener(this);
        bi.gds01pre04d.setOnCheckedChangeListener(this);
    }
}