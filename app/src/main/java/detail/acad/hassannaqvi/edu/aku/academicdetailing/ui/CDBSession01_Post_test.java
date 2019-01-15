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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityCdbsession01PostTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class CDBSession01_Post_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityCdbsession01PostTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_cdbsession01__post_test);
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
        JSONObject sCdb01post = GeneratorClass.getContainerJSON(bi.fldGrpPostCdb01, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01post));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.cdb01post01, bi.cdb01post01a, getString(R.string.cdb01_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdb01post02, bi.cdb01post02a, getString(R.string.cdb01_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdb01post03, bi.cdb01post03a, getString(R.string.cdb01_03))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.cdb01post04, bi.cdb01post04a, getString(R.string.cdb01_04));
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


        //CDB-Q1
        if (compoundButton.getId() == R.id.cdb01post01a
                || compoundButton.getId() == R.id.cdb01post01b
                || compoundButton.getId() == R.id.cdb01post01c
                || compoundButton.getId() == R.id.cdb01post01d) {

            if (bi.cdb01post01a.isChecked()) {
                bi.tvcdb01post01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>Tuberculosis</i></b></font>.";
                bi.tvcdb01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post01b.isChecked()) {
                bi.tvcdb01post01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>Asthma</i></b></font>.";
                bi.tvcdb01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post01c.isChecked()) {
                bi.tvcdb01post01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>Whooping cough</i></b></font>.";
                bi.tvcdb01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post01d.isChecked()) {
                bi.tvcdb01post01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>All of the below</i></b></font>.";
                bi.tvcdb01post01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //CDB-Q3
        if (compoundButton.getId() == R.id.cdb01post03a
                || compoundButton.getId() == R.id.cdb01post03b
                || compoundButton.getId() == R.id.cdb01post03c
                || compoundButton.getId() == R.id.cdb01post03d) {

            if (bi.cdb01post03a.isChecked()) {
                bi.tvcdb01post03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Wheeze</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdb01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post03b.isChecked()) {
                bi.tvcdb01post03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Chest in-drawing</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdb01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post03c.isChecked()) {
                bi.tvcdb01post03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Cough</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdb01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post03d.isChecked()) {
                bi.tvcdb01post03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Fever</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdb01post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //CDB-Q4
        if (compoundButton.getId() == R.id.cdb01post04a
                || compoundButton.getId() == R.id.cdb01post04b) {

            if (bi.cdb01post04a.isChecked()) {
                bi.tvcdb01post04.clearComposingText();
                String styledText = "The child has chest in drawing if the lower chest wall (lower ribs) goes <font color='yellow'><b><i>IN</i></b></font> when the child breathes IN.";
                bi.tvcdb01post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb01post04b.isChecked()) {
                bi.tvcdb01post04.clearComposingText();
                String styledText = "The child has chest in drawing if the lower chest wall (lower ribs) goes <font color='yellow'><b><i>OUT</i></b></font> when the child breathes IN.";
                bi.tvcdb01post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.cdb01post01a.setOnCheckedChangeListener(this);
        bi.cdb01post01a.setOnCheckedChangeListener(this);
        bi.cdb01post01a.setOnCheckedChangeListener(this);
        bi.cdb01post01a.setOnCheckedChangeListener(this);

        bi.cdb01post03a.setOnCheckedChangeListener(this);
        bi.cdb01post03a.setOnCheckedChangeListener(this);
        bi.cdb01post03a.setOnCheckedChangeListener(this);
        bi.cdb01post03a.setOnCheckedChangeListener(this);

        bi.cdb01post04a.setOnCheckedChangeListener(this);
        bi.cdb01post04b.setOnCheckedChangeListener(this);
    }
}
