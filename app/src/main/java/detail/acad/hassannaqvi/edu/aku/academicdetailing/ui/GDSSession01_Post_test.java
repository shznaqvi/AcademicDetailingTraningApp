package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession01PostTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession01_Post_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityGdssession01PostTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession01__post_test);
        bi.setCallback(this);

        events_call();

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
            } catch (Exception e) {
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
        JSONObject sGds01post = GeneratorClass.getContainerJSON(bi.fldGrpPostGds01, true);
        MainApp.fc.setPre_test(String.valueOf(sGds01post));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gds01post01, bi.gds01post01a, getString(R.string.gds01_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds01post02, bi.gds01post02a, getString(R.string.gds01_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds01post03, bi.gds01post03a, getString(R.string.gds01_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds01post04, bi.gds01post04a, getString(R.string.gds01_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gds01post05, bi.gds01post05a, getString(R.string.gds01_05));
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
        if (compoundButton.getId() == R.id.gds01post01a
                || compoundButton.getId() == R.id.gds01post01b
                || compoundButton.getId() == R.id.gds01post01c
                || compoundButton.getId() == R.id.gds01post01d) {

            if (bi.gds01post01a.isChecked()) {
                bi.tvgds01post01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Mild illness</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgds01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);


                /*Entity entity;
                entity = (Entity) Document.getDoctype().getEntities().item(i);
                String path = entity.getSystemId();*/

                /*txt_gdspretestq1.setText(getString(R.string.gdspretestq1));*/

            } else if (bi.gds01post01b.isChecked()) {
                bi.tvgds01post01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Pneumonia</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgds01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post01c.isChecked()) {
                bi.tvgds01post01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Dehydration</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgds01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post01d.isChecked()) {
                bi.tvgds01post01.clearComposingText();
                String styledText = "Children with <font color='yellow'><b><i>Severe disease/classification</i></b></font> on the IMNCI chart usually need urgent referral to hospital.";
                bi.tvgds01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q2
        if (compoundButton.getId() == R.id.gds01post02a
                || compoundButton.getId() == R.id.gds01post02b
                || compoundButton.getId() == R.id.gds01post02c
                || compoundButton.getId() == R.id.gds01post02d) {

            if (bi.gds01post02a.isChecked()) {
                bi.tvgds01post02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Persistent diarrhea</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgds01post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post02b.isChecked()) {
                bi.tvgds01post02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe dehydration</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgds01post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post02c.isChecked()) {
                bi.tvgds01post02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe persistent diarrhea</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgds01post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post02d.isChecked()) {
                bi.tvgds01post02.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Severe pneumonia</i></b></font> is the exception for severe classification or severe disease where referral to the hospital is needed but not urgent.";
                bi.tvgds01post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q3
        if (compoundButton.getId() == R.id.gds01post03a
                || compoundButton.getId() == R.id.gds01post03b
                || compoundButton.getId() == R.id.gds01post03c
                || compoundButton.getId() == R.id.gds01post03d) {

            if (bi.gds01post03a.isChecked()) {
                bi.tvgds01post03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>One</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgds01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post03b.isChecked()) {
                bi.tvgds01post03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>Two</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgds01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post03c.isChecked()) {
                bi.tvgds01post03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>Three</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgds01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post03d.isChecked()) {
                bi.tvgds01post03.clearComposingText();
                String styledText = "Presence of even <font color='yellow'><b><i>None</i></b></font> danger sign indicates that underlying disease is severe.";
                bi.tvgds01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS-Q4
        if (compoundButton.getId() == R.id.gds01post04a
                || compoundButton.getId() == R.id.gds01post04b
                || compoundButton.getId() == R.id.gds01post04c
                || compoundButton.getId() == R.id.gds01post04d) {

            if (bi.gds01post04a.isChecked()) {
                bi.tvgds01post04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>One</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvgds01post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post04b.isChecked()) {
                bi.tvgds01post04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Two</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvgds01post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post04c.isChecked()) {
                bi.tvgds01post04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Three</i></b></font> questions and LOOK for <font color='yellow'><b><i>Two</i></b></font> observations to observe the child's actions.";
                bi.tvgds01post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds01post04d.isChecked()) {
                bi.tvgds01post04.clearComposingText();
                String styledText = "For assessing a general danger sign, a healthcare provider needs to ASK <font color='yellow'><b><i>Three</i></b></font> questions and LOOK for <font color='yellow'><b><i>One</i></b></font> observations to observe the child's actions.";
                bi.tvgds01post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.gds01post01a.setOnCheckedChangeListener(this);
        bi.gds01post01b.setOnCheckedChangeListener(this);
        bi.gds01post01c.setOnCheckedChangeListener(this);
        bi.gds01post01d.setOnCheckedChangeListener(this);

        bi.gds01post02a.setOnCheckedChangeListener(this);
        bi.gds01post02b.setOnCheckedChangeListener(this);
        bi.gds01post02c.setOnCheckedChangeListener(this);
        bi.gds01post02d.setOnCheckedChangeListener(this);

        bi.gds01post03a.setOnCheckedChangeListener(this);
        bi.gds01post03b.setOnCheckedChangeListener(this);
        bi.gds01post03c.setOnCheckedChangeListener(this);
        bi.gds01post03d.setOnCheckedChangeListener(this);

        bi.gds01post04a.setOnCheckedChangeListener(this);
        bi.gds01post04b.setOnCheckedChangeListener(this);
        bi.gds01post04c.setOnCheckedChangeListener(this);
        bi.gds01post04d.setOnCheckedChangeListener(this);
    }
}
