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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityCdbsession02PostTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class CDBSession02_Post_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityCdbsession02PostTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_cdbsession02__post_test);
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
        JSONObject sCdb01post = GeneratorClass.getContainerJSON(bi.fldGrpPostCdb02, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01post));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.cdb02post01, bi.cdb02post01a, getString(R.string.cdb02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdb02post02, bi.cdb02post02a, getString(R.string.cdb02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdb02post03, bi.cdb02post03a, getString(R.string.cdb02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdb02post04, bi.cdb02post04a, getString(R.string.cdb02_04))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdb02post05, bi.cdb02post05a, getString(R.string.cdb02_05))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.cdb02post06, bi.cdb02post06a, getString(R.string.cdb02_06));
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


        //CDB02-Q2
        if (compoundButton.getId() == R.id.cdb02post02a
                || compoundButton.getId() == R.id.cdb02post02b
                || compoundButton.getId() == R.id.cdb02post02c
                || compoundButton.getId() == R.id.cdb02post02d) {

            if (bi.cdb02post02a.isChecked()) {
                bi.tvcdb02post02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>40</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>7</i></b></font>mg/kg.";
                bi.tvcdb02post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb02post02b.isChecked()) {
                bi.tvcdb02post02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>45</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>7.5</i></b></font>mg/kg.";
                bi.tvcdb02post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb02post02c.isChecked()) {
                bi.tvcdb02post02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>50</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>7.5</i></b></font>mg/kg.";
                bi.tvcdb02post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb02post02d.isChecked()) {
                bi.tvcdb02post02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>55</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>8</i></b></font>mg/kg.";
                bi.tvcdb02post02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //CDB02-Q4
        if (compoundButton.getId() == R.id.cdb02post04a
                || compoundButton.getId() == R.id.cdb02post02b
                || compoundButton.getId() == R.id.cdb02post02c
                || compoundButton.getId() == R.id.cdb02post02d) {

            if (bi.cdb02post04a.isChecked()) {
                bi.tvcdb02post04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>2</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4</i></b></font> L/min for older children.";
                bi.tvcdb02post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb02post04b.isChecked()) {
                bi.tvcdb02post04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>3</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4</i></b></font> L/min for older children.";
                bi.tvcdb02post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb02post04c.isChecked()) {
                bi.tvcdb02post04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>3.5</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4.5</i></b></font> L/min for older children.";
                bi.tvcdb02post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdb02post04d.isChecked()) {
                bi.tvcdb02post04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>4</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4.5</i></b></font> L/min for older children.";
                bi.tvcdb02post04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.cdb02post02a.setOnCheckedChangeListener(this);
        bi.cdb02post02b.setOnCheckedChangeListener(this);
        bi.cdb02post02c.setOnCheckedChangeListener(this);
        bi.cdb02post02d.setOnCheckedChangeListener(this);

        bi.cdb02post04a.setOnCheckedChangeListener(this);
        bi.cdb02post04b.setOnCheckedChangeListener(this);
        bi.cdb02post04c.setOnCheckedChangeListener(this);
        bi.cdb02post04d.setOnCheckedChangeListener(this);
    }
}