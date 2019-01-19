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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityCdbsession02PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.slides;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class CDBSession02_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityCdbsession02PreTestBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_cdbsession02__pre_test);
        bi.setCallback(this);


        events_call();

        if (!isComplete) {
            type = getIntent().getStringExtra("type");
            slides = getIntent().getIntArrayExtra("slides");
            Data.correctAnswers = getIntent().getStringArrayListExtra("ans");
            if (type.equals("pre")) {
                MainApp.fc.setPreTestStartTime(MainApp.getCurrentTime());
            } else {
                MainApp.fc.setPostTestStartTime(MainApp.getCurrentTime());
            }
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);

        } else {
            GeneratorClass.comparingResult(bi.fldGrpPreCdb02,true,Data.correctAnswers);
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
                            startActivity(new Intent(this, CDBSession02_Pre_test.class));
                            isComplete = true;
                            finish();
                        } else {
                            Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }else{
                        MainApp.endActivity(this, "Are You Sure You want to Continue?", true);
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

    public void BtnOk() {
        if (type.equals("pre")) {
            if (MainApp.isSlideStart) {
                startActivity(new Intent(this, ViewPagerActivity.class).putExtra("slides", slides));
                finish();
            } else {
                Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            MainApp.endActivity(this, "Are You Sure You want to Continue?", true);
        }
    }

    private void SaveDraft() {

        if(type.equals("pre")){
            MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreCdb02, true,type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.testAnswers = GeneratorClass.getAnswers(bi.fldGrpPreCdb02, true);
        }else{
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreCdb02, true,type);
            MainApp.fc.setPost_test(String.valueOf(json));
        }

    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.cdbb01, bi.cdbb01a, getString(R.string.cdb02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdbb02, bi.cdbb02a, getString(R.string.cdb02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdbb03, bi.cdbb03a, getString(R.string.cdb02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdbb04, bi.cdbb04a, getString(R.string.cdb02_04))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdbb05, bi.cdbb05a, getString(R.string.cdb02_05))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.cdbb06, bi.cdbb06a, getString(R.string.cdb02_06));
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //CDB02-Q2
        if (compoundButton.getId() == R.id.cdbb02a
                || compoundButton.getId() == R.id.cdbb02b
                || compoundButton.getId() == R.id.cdbb02c
                || compoundButton.getId() == R.id.cdbb02d) {

            if (bi.cdbb02a.isChecked()) {
                bi.tvcdbb02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>40</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>7</i></b></font>mg/kg.";
                bi.tvcdbb02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdbb02b.isChecked()) {
                bi.tvcdbb02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>45</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>7.5</i></b></font>mg/kg.";
                bi.tvcdbb02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdbb02c.isChecked()) {
                bi.tvcdbb02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>50</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>7.5</i></b></font>mg/kg.";
                bi.tvcdbb02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdbb02d.isChecked()) {
                bi.tvcdbb02.clearComposingText();
                String styledText = "The dose for intramuscular antibiotics (urgent referral); Ampicillin <font color='yellow'><b><i>55</i></b></font>mg/kg and Gentamicin <font color='yellow'><b><i>8</i></b></font>mg/kg.";
                bi.tvcdbb02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //CDB02-Q4
        if (compoundButton.getId() == R.id.cdbb04a
                || compoundButton.getId() == R.id.cdbb02b
                || compoundButton.getId() == R.id.cdbb02c
                || compoundButton.getId() == R.id.cdbb02d) {

            if (bi.cdbb04a.isChecked()) {
                bi.tvcdbb04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>2</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4</i></b></font> L/min for older children.";
                bi.tvcdbb04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdbb04b.isChecked()) {
                bi.tvcdbb04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>3</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4</i></b></font> L/min for older children.";
                bi.tvcdbb04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdbb04c.isChecked()) {
                bi.tvcdbb04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>3.5</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4.5</i></b></font> L/min for older children.";
                bi.tvcdbb04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdbb04d.isChecked()) {
                bi.tvcdbb04.clearComposingText();
                String styledText = "Standard flow rates for oxygen through nasal prongs or nasal catheters are up to <font color='yellow'><b><i>4</i></b></font> L/min for infants, up to <font color='yellow'><b><i>4.5</i></b></font> L/min for older children.";
                bi.tvcdbb04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.cdbb02a.setOnCheckedChangeListener(this);
        bi.cdbb02b.setOnCheckedChangeListener(this);
        bi.cdbb02c.setOnCheckedChangeListener(this);
        bi.cdbb02d.setOnCheckedChangeListener(this);

        bi.cdbb04a.setOnCheckedChangeListener(this);
        bi.cdbb04b.setOnCheckedChangeListener(this);
        bi.cdbb04c.setOnCheckedChangeListener(this);
        bi.cdbb04d.setOnCheckedChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
