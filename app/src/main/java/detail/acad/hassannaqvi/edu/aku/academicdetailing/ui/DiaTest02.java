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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityDiaTest02Binding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class DiaTest02 extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityDiaTest02Binding bi;

    Data.SubMenu subMenuDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_dia_test02);
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
            MainApp.fc.setPreTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);
        } else if (type.equals("pre") && isComplete) {
            bi.heading.setText("PRETEST RESULT");
            GeneratorClass.comparingResult(bi.llDiaTestB, true, subMenuDT.getAnswers());
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnOk.setText("Start Training");
            bi.btnContinue.setVisibility(View.GONE);
        } else if (type.equals("post") && !isComplete) {
            bi.heading.setText("POST TEST");
            MainApp.fc.setPostTestStartTime(MainApp.getCurrentTime());
            bi.btnOk.setVisibility(View.GONE);
            bi.btnContinue.setVisibility(View.VISIBLE);
        } else if (type.equals("post") && isComplete) {
            bi.heading.setText(" POST TEST & PRETEST RESULT");
            GeneratorClass.comparingPostTestAndPretestResult(bi.llDiaTestB, true, subMenuDT.getAnswers());
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnOk.setText("Finish Training");
            bi.btnContinue.setVisibility(View.GONE);
        }


    }

    public void BtnOk() {
        if (type.equals("pre")) {
            if (MainApp.isSlideStart) {
                MainApp.showDialog(this, getString(R.string.readyForTrain), "pre", null, subMenuDT);
            } else {
                Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            MainApp.showDialog(this, getString(R.string.areYouSure), "end", true, null);
        }
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    if (type.equals("pre")) {
                        if (MainApp.isSlideStart) {
                            startActivity(new Intent(this, DiaTest02.class)
                                    .putExtra(CONSTANTS.URI_FORM_TYPE, type)
                                    .putExtra(CONSTANTS.URI_SUBMENU_DT, subMenuDT)
                            );
                            isComplete = true;
                            GeneratorClass.incr = 0;
                            finish();
                        } else {
                            Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else if (type.equals("post")) {
                        startActivity(new Intent(this, DiaTest02.class)
                                .putExtra(CONSTANTS.URI_FORM_TYPE, type)
                                .putExtra(CONSTANTS.URI_SUBMENU_DT, subMenuDT)
                        );
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //DiaTest02-Q1
        if (compoundButton.getId() == R.id.DiaTestB01a
                || compoundButton.getId() == R.id.DiaTestB01b
                || compoundButton.getId() == R.id.DiaTestB01c
                || compoundButton.getId() == R.id.DiaTestB01d) {

            if (bi.DiaTestB01a.isChecked()) {
                bi.tvDiaTestB01.clearComposingText();
                String styledText = "A child with persistent diarrhea needs Zinc DT for <font color='yellow'><b><i>10</i></b></font> days";
                bi.tvDiaTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB01b.isChecked()) {
                bi.tvDiaTestB01.clearComposingText();
                String styledText = "A child with persistent diarrhea needs Zinc DT for <font color='yellow'><b><i>12</i></b></font> days";
                bi.tvDiaTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB01c.isChecked()) {
                bi.tvDiaTestB01.clearComposingText();
                String styledText = "A child with persistent diarrhea needs Zinc DT for <font color='yellow'><b><i>14</i></b></font> days";
                bi.tvDiaTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB01d.isChecked()) {
                bi.tvDiaTestB01.clearComposingText();
                String styledText = "A child with persistent diarrhea needs Zinc DT for <font color='yellow'><b><i>16</i></b></font> days";
                bi.tvDiaTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //DiaTest02-Q2
        if (compoundButton.getId() == R.id.DiaTestB02a
                || compoundButton.getId() == R.id.DiaTestB02b
                || compoundButton.getId() == R.id.DiaTestB02c
                || compoundButton.getId() == R.id.DiaTestB02d) {

            if (bi.DiaTestB02a.isChecked()) {
                bi.tvDiaTestB02.clearComposingText();
                String styledText = "The antibiotic prescribed for dysentery is <font color='yellow'><b><i>Ciprofloxacin</i></b></font> for 3 days.";
                bi.tvDiaTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB02b.isChecked()) {
                bi.tvDiaTestB02.clearComposingText();
                String styledText = "The antibiotic prescribed for dysentery is <font color='yellow'><b><i>Amoxicillin</i></b></font> for 3 days.";
                bi.tvDiaTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB02c.isChecked()) {
                bi.tvDiaTestB02.clearComposingText();
                String styledText = "The antibiotic prescribed for dysentery is  <font color='yellow'><b><i>Ampicillin</i></b></font> for 3 days.";
                bi.tvDiaTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB02d.isChecked()) {
                bi.tvDiaTestB02.clearComposingText();
                String styledText = "The antibiotic prescribed for dysentery is <font color='yellow'><b><i>Diazepam</i></b></font> for 3 days.";
                bi.tvDiaTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //DiaTest02-Q3
        if (compoundButton.getId() == R.id.DiaTestB03a
                || compoundButton.getId() == R.id.DiaTestB03b
                || compoundButton.getId() == R.id.DiaTestB03c
                || compoundButton.getId() == R.id.DiaTestB03d) {

            if (bi.DiaTestB03a.isChecked()) {
                bi.tvDiaTestB03.clearComposingText();
                String styledText = "Children with diarrhea who come to a health worker with NO <font color='yellow'><b><i>Dehydration</i></b></font> are put on plan A.";
                bi.tvDiaTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB03b.isChecked()) {
                bi.tvDiaTestB03.clearComposingText();
                String styledText = "Children with diarrhea who come to a health worker with NO <font color='yellow'><b><i>Dysentery</i></b></font> are put on plan A.";
                bi.tvDiaTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB03c.isChecked()) {
                bi.tvDiaTestB03.clearComposingText();
                String styledText = "Children with diarrhea who come to a health worker with NO <font color='yellow'><b><i>Hydration</i></b></font> are put on plan A.";
                bi.tvDiaTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB03d.isChecked()) {
                bi.tvDiaTestB03.clearComposingText();
                String styledText = "Children with diarrhea who come to a health worker with NO <font color='yellow'><b><i>Pneumonia</i></b></font> are put on plan A.";
                bi.tvDiaTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //DiaTest02-Q5
        if (compoundButton.getId() == R.id.DiaTestB05a
                || compoundButton.getId() == R.id.DiaTestB05b
                || compoundButton.getId() == R.id.DiaTestB05c
                || compoundButton.getId() == R.id.DiaTestB05d) {

            if (bi.DiaTestB05a.isChecked()) {
                bi.tvDiaTestB05.clearComposingText();
                String styledText = "What is the dose of Zinc DT for home treatment for a child 6 months or older <font color='yellow'><b><i>½ tablet per day for 12 days</i></b></font> .";
                bi.tvDiaTestB05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB05b.isChecked()) {
                bi.tvDiaTestB05.clearComposingText();
                String styledText = "What is the dose of Zinc DT for home treatment for a child 6 months or older <font color='yellow'><b><i>1 tablet per day for 10 days</i></b></font> .";
                bi.tvDiaTestB05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB05c.isChecked()) {
                bi.tvDiaTestB05.clearComposingText();
                String styledText = "What is the dose of Zinc DT for home treatment for a child 6 months or older <font color='yellow'><b><i>1.5 tablets per day for 11 days</i></b></font> .";
                bi.tvDiaTestB05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestB05d.isChecked()) {
                bi.tvDiaTestB05.clearComposingText();
                String styledText = "What is the dose of Zinc DT for home treatment for a child 6 months or older <font color='yellow'><b><i>2 tablets per day for 10 days</i></b></font> .";
                bi.tvDiaTestB05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }


    void events_call() {

        bi.DiaTestB01a.setOnCheckedChangeListener(this);
        bi.DiaTestB01b.setOnCheckedChangeListener(this);
        bi.DiaTestB01c.setOnCheckedChangeListener(this);
        bi.DiaTestB01d.setOnCheckedChangeListener(this);

        bi.DiaTestB02a.setOnCheckedChangeListener(this);
        bi.DiaTestB02b.setOnCheckedChangeListener(this);
        bi.DiaTestB02c.setOnCheckedChangeListener(this);
        bi.DiaTestB02d.setOnCheckedChangeListener(this);

        bi.DiaTestB03a.setOnCheckedChangeListener(this);
        bi.DiaTestB03b.setOnCheckedChangeListener(this);
        bi.DiaTestB03c.setOnCheckedChangeListener(this);
        bi.DiaTestB03d.setOnCheckedChangeListener(this);

        bi.DiaTestB05a.setOnCheckedChangeListener(this);
        bi.DiaTestB05b.setOnCheckedChangeListener(this);
        bi.DiaTestB05c.setOnCheckedChangeListener(this);
        bi.DiaTestB05d.setOnCheckedChangeListener(this);
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
            MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.llDiaTestB, true, type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.llDiaTestB, true);
        } else {
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.llDiaTestB, true, type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.llDiaTestB, true);
        }

    }


    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.llDiaTestB);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
