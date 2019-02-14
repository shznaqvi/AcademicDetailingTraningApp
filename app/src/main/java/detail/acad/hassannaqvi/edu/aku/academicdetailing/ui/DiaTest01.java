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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityDiaTest01Binding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.pre_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;


public class DiaTest01 extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityDiaTest01Binding bi;
    Data.SubMenu subMenuDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_dia_test01);
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
            GeneratorClass.comparingResult(bi.llDiaTestA, true, subMenuDT.getAnswers());
            pre_result = GeneratorClass.getResults("pre",subMenuDT.getAnswers());
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
            GeneratorClass.comparingPostTestAndPretestResult(bi.llDiaTestA, true, subMenuDT.getAnswers());
            post_result = GeneratorClass.getResults("post",subMenuDT.getAnswers());
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnOk.setText("Finish Training");
            bi.btnContinue.setVisibility(View.GONE);
        }


    }

    public void BtnOk() {
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
                            startActivity(new Intent(this, DiaTest01.class)
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
                        startActivity(new Intent(this, DiaTest01.class)
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //DiaTest01-Q1
        if (compoundButton.getId() == R.id.DiaTestA01a
                || compoundButton.getId() == R.id.DiaTestA01b
                || compoundButton.getId() == R.id.DiaTestA01c
                || compoundButton.getId() == R.id.DiaTestA01d) {

            if (bi.DiaTestA01a.isChecked()) {
                bi.tvDiaTestA01.clearComposingText();
                String styledText = "The death of a child with acute diarrhea is usually due to <font color='yellow'><b><i>Dehydration</i></b></font>.";
                bi.tvDiaTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA01b.isChecked()) {
                bi.tvDiaTestA01.clearComposingText();
                String styledText = "The death of a child with acute diarrhea is usually due to <font color='yellow'><b><i>Dysentery</i></b></font>.";
                bi.tvDiaTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA01c.isChecked()) {
                bi.tvDiaTestA01.clearComposingText();
                String styledText = "The death of a child with acute diarrhea is usually due to <font color='yellow'><b><i>Hydration</i></b></font>.";
                bi.tvDiaTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA01d.isChecked()) {
                bi.tvDiaTestA01.clearComposingText();
                String styledText = "The death of a child with acute diarrhea is usually due to <font color='yellow'><b><i>Malnutrition</i></b></font>.";
                bi.tvDiaTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //DiaTest01-Q2
        if (compoundButton.getId() == R.id.DiaTestA02a
                || compoundButton.getId() == R.id.DiaTestA02b
                || compoundButton.getId() == R.id.DiaTestA02c
                || compoundButton.getId() == R.id.DiaTestA02d) {

            if (bi.DiaTestA02a.isChecked()) {
                bi.tvDiaTestA02.clearComposingText();
                String styledText = "The common cause of dysentery is <font color='yellow'><b><i>Brucella</i></b></font> bacteria.";
                bi.tvDiaTestA02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA02b.isChecked()) {
                bi.tvDiaTestA02.clearComposingText();
                String styledText = "The common cause of dysentery is <font color='yellow'><b><i>Shigella</i></b></font> bacteria.";
                bi.tvDiaTestA02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA02c.isChecked()) {
                bi.tvDiaTestA02.clearComposingText();
                String styledText = "The common cause of dysentery is <font color='yellow'><b><i>Bacillus</i></b></font> bacteria.";
                bi.tvDiaTestA02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA02d.isChecked()) {
                bi.tvDiaTestA02.clearComposingText();
                String styledText = "The common cause of dysentery is <font color='yellow'><b><i>Salmonella </i></b></font> bacteria.";
                bi.tvDiaTestA02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //DiaTest01-Q5
        if (compoundButton.getId() == R.id.DiaTestA05a
                || compoundButton.getId() == R.id.DiaTestA05b
                || compoundButton.getId() == R.id.DiaTestA05c
                || compoundButton.getId() == R.id.DiaTestA05d) {

            if (bi.DiaTestA05a.isChecked()) {
                bi.tvDiaTestA05.clearComposingText();
                String styledText = "<font color='yellow'><b><i>PCV</i></b></font> vaccination is recommended for diarrhea by WHO for children less than 5 years.";
                bi.tvDiaTestA05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA05b.isChecked()) {
                bi.tvDiaTestA05.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Hib</i></b></font> vaccination is recommended for diarrhea by WHO for children less than 5 years.";
                bi.tvDiaTestA05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA05c.isChecked()) {
                bi.tvDiaTestA05.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Pertussis</i></b></font> vaccination is recommended for diarrhea by WHO for children less than 5 years.";
                bi.tvDiaTestA05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.DiaTestA05d.isChecked()) {
                bi.tvDiaTestA05.clearComposingText();
                String styledText = "<font color='yellow'><b><i>Rotavirus</i></b></font> vaccination is recommended for diarrhea by WHO for children less than 5 years.";
                bi.tvDiaTestA05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.DiaTestA01a.setOnCheckedChangeListener(this);
        bi.DiaTestA01b.setOnCheckedChangeListener(this);
        bi.DiaTestA01c.setOnCheckedChangeListener(this);
        bi.DiaTestA01d.setOnCheckedChangeListener(this);

        bi.DiaTestA02a.setOnCheckedChangeListener(this);
        bi.DiaTestA02b.setOnCheckedChangeListener(this);
        bi.DiaTestA02c.setOnCheckedChangeListener(this);
        bi.DiaTestA02d.setOnCheckedChangeListener(this);

        bi.DiaTestA05a.setOnCheckedChangeListener(this);
        bi.DiaTestA05b.setOnCheckedChangeListener(this);
        bi.DiaTestA05c.setOnCheckedChangeListener(this);
        bi.DiaTestA05d.setOnCheckedChangeListener(this);
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
            JSONObject json = GeneratorClass.getContainerJSON(bi.llDiaTestA, true, type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.llDiaTestA, true);
        } else {
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.llDiaTestA, true, type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.llDiaTestA, true);
        }

    }


    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.llDiaTestA);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
