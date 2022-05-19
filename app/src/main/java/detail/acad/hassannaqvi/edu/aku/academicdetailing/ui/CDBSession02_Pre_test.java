package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.pre_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityCdbsession02PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class CDBSession02_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityCdbsession02PreTestBinding bi;
    Data.SubMenu subMenuDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_cdbsession02__pre_test);
        bi.setCallback(this);

        events_call();
        setupViews();

    }

    private void setupViews() {

        type = getIntent().getStringExtra(CONSTANTS.URI_FORM_TYPE);
        subMenuDT = (Data.SubMenu) getIntent().getSerializableExtra(CONSTANTS.URI_SUBMENU_DT);

        this.setTitle(subMenuDT.getName());
        if (type.equals("pre") && !isComplete) {
            bi.heading.setText("PRETEST");
//            slides = getIntent().getIntArrayExtra("slides");
//            Data.correctAnswers = getIntent().getStringArrayListExtra("ans");
            MainApp.forms.setPreTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);
        } else if (type.equals("pre") && isComplete) {
            bi.heading.setText("PRETEST RESULT");
            GeneratorClass.comparingResult(bi.fldGrpPreCdb02, true, subMenuDT.getAnswers());
            pre_result = GeneratorClass.getResults("pre",subMenuDT.getAnswers());
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnOk.setText("Start Training");
            bi.btnContinue.setVisibility(View.GONE);
        } else if (type.equals("post") && !isComplete) {
            bi.heading.setText("POST TEST");
            MainApp.forms.setPostTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);
        } else if (type.equals("post") && isComplete) {
            bi.heading.setText(" POST TEST & PRETEST RESULT");
            GeneratorClass.comparingPostTestAndPretestResult(bi.fldGrpPreCdb02, true, subMenuDT.getAnswers());
            post_result = GeneratorClass.getResults("post",subMenuDT.getAnswers());
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnOk.setText("Finish Training");
            bi.btnContinue.setVisibility(View.GONE);
        }


    }

    public void btnOk() {
        if (type.equals("pre")) {
            MainApp.showDialog(this, getString(R.string.readyForTrain), "pre", null, subMenuDT);
        } else {
            MainApp.showDialogeWithResult(this, post_result, subMenuDT);
//            MainApp.showDialog(this, getString(R.string.areYouSure), "end", true, subMenuDT);
        }
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    if (type.equals("pre")) {
                        if (MainApp.isSlideStart) {
                            startActivity(new Intent(this, CDBSession02_Pre_test.class)
                                    .putExtra(CONSTANTS.URI_FORM_TYPE, type)
                                    .putExtra(CONSTANTS.URI_SUBMENU_DT, subMenuDT)
                            );
                            isComplete = true;
                            finish();
                        } else {
                            Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else if (type.equals("post")) {
                        startActivity(new Intent(this, CDBSession02_Pre_test.class)
                                .putExtra(CONSTANTS.URI_FORM_TYPE, type)
                                .putExtra(CONSTANTS.URI_SUBMENU_DT, subMenuDT)
                        );
                        isComplete = true;
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
        if (type.equals("pre")) {
            count = db.updatePreTest();
        } else {
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

        if (type.equals("pre")) {
            MainApp.forms.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreCdb02, true, type);
            MainApp.forms.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreCdb02, true);
        } else {
            MainApp.forms.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreCdb02, true, type);
            MainApp.forms.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreCdb02, true);
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
