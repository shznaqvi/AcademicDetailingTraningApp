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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityPsbiTest02Binding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class PsbiTest02 extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityPsbiTest02Binding bi;
    Data.SubMenu subMenuDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_psbi_test02);
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
            GeneratorClass.comparingResult(bi.llPsbiTestB, true, subMenuDT.getAnswers());
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
            GeneratorClass.comparingPostTestAndPretestResult(bi.llPsbiTestB, true, subMenuDT.getAnswers());
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
            MainApp.showDialog(this, getString(R.string.areYouSure), "end", true, subMenuDT);
        }
    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    if (type.equals("pre")) {
                        if (MainApp.isSlideStart) {
                            startActivity(new Intent(this, PsbiTest02.class)
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
                        startActivity(new Intent(this, PsbiTest02.class)
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

        //PsbiTest02-Q1
        if (compoundButton.getId() == R.id.PsbiTestB01a
                || compoundButton.getId() == R.id.PsbiTestB01b
                || compoundButton.getId() == R.id.PsbiTestB01c
                || compoundButton.getId() == R.id.PsbiTestB01d) {

            if (bi.PsbiTestB01a.isChecked()) {
                bi.tvPsbiTestB01.clearComposingText();
                String styledText = "The follow-up time for sick young infants with diarrhea and pneumonia is <font color='yellow'><b><i>2nd day</i></b></font> .";
                bi.tvPsbiTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB01b.isChecked()) {
                bi.tvPsbiTestB01.clearComposingText();
                String styledText = "The follow-up time for sick young infants with diarrhea and pneumonia is <font color='yellow'><b><i>3rd day</i></b></font> .";
                bi.tvPsbiTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB01c.isChecked()) {
                bi.tvPsbiTestB01.clearComposingText();
                String styledText = "The follow-up time for sick young infants with diarrhea and pneumonia is <font color='yellow'><b><i>4th day</i></b></font> .";
                bi.tvPsbiTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB01d.isChecked()) {
                bi.tvPsbiTestB01.clearComposingText();
                String styledText = "The follow-up time for sick young infants with diarrhea and pneumonia is <font color='yellow'><b><i>5th day</i></b></font> .";
                bi.tvPsbiTestB01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //PsbiTest02-Q2
        if (compoundButton.getId() == R.id.PsbiTestB02a
                || compoundButton.getId() == R.id.PsbiTestB02b
                || compoundButton.getId() == R.id.PsbiTestB02c
                || compoundButton.getId() == R.id.PsbiTestB02d) {

            if (bi.PsbiTestB02a.isChecked()) {
                bi.tvPsbiTestB02.clearComposingText();
                String styledText = "Persistent vomiting is defined as vomiting following three attempts to feed the infant within <font color='yellow'><b><i>10</i></b></font> minutes, and the infant vomits after each attempt.";
                bi.tvPsbiTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB02b.isChecked()) {
                bi.tvPsbiTestB02.clearComposingText();
                String styledText = "Persistent vomiting is defined as vomiting following three attempts to feed the infant within <font color='yellow'><b><i>20</i></b></font> minutes, and the infant vomits after each attempt.";
                bi.tvPsbiTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB02c.isChecked()) {
                bi.tvPsbiTestB02.clearComposingText();
                String styledText = "Persistent vomiting is defined as vomiting following three attempts to feed the infant within <font color='yellow'><b><i>30</i></b></font> minutes, and the infant vomits after each attempt.";
                bi.tvPsbiTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB02d.isChecked()) {
                bi.tvPsbiTestB02.clearComposingText();
                String styledText = "Persistent vomiting is defined as vomiting following three attempts to feed the infant within <font color='yellow'><b><i>40</i></b></font> minutes, and the infant vomits after each attempt.";
                bi.tvPsbiTestB02.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //PsbiTest02-Q3
        if (compoundButton.getId() == R.id.PsbiTestB03a
                || compoundButton.getId() == R.id.PsbiTestB03b
                || compoundButton.getId() == R.id.PsbiTestB03c
                || compoundButton.getId() == R.id.PsbiTestB03d) {

            if (bi.PsbiTestB03a.isChecked()) {
                bi.tvPsbiTestB03.clearComposingText();
                String styledText = "Pre referral treatment for severe Pneumonia or very severe disease in young infant up to 2 months require first dose of an appropriate <font color='yellow'><b><i>Oral</i></b></font> antibiotic .";
                bi.tvPsbiTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB03b.isChecked()) {
                bi.tvPsbiTestB03.clearComposingText();
                String styledText = "Pre referral treatment for severe Pneumonia or very severe disease in young infant up to 2 months require first dose of an appropriate <font color='yellow'><b><i>Intravenous</i></b></font> antibiotic .";
                bi.tvPsbiTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB03c.isChecked()) {
                bi.tvPsbiTestB03.clearComposingText();
                String styledText = "Pre referral treatment for severe Pneumonia or very severe disease in young infant up to 2 months require first dose of an appropriate <font color='yellow'><b><i>Intramuscular</i></b></font> antibiotic .";
                bi.tvPsbiTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestB03d.isChecked()) {
                bi.tvPsbiTestB03.clearComposingText();
                String styledText = "Pre referral treatment for severe Pneumonia or very severe disease in young infant up to 2 months require first dose of an appropriate <font color='yellow'><b><i>None of the above</i></b></font> antibiotic .";
                bi.tvPsbiTestB03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.PsbiTestB01a.setOnCheckedChangeListener(this);
        bi.PsbiTestB01b.setOnCheckedChangeListener(this);
        bi.PsbiTestB01c.setOnCheckedChangeListener(this);
        bi.PsbiTestB01d.setOnCheckedChangeListener(this);

        bi.PsbiTestB02a.setOnCheckedChangeListener(this);
        bi.PsbiTestB02b.setOnCheckedChangeListener(this);
        bi.PsbiTestB02c.setOnCheckedChangeListener(this);
        bi.PsbiTestB02d.setOnCheckedChangeListener(this);

        bi.PsbiTestB03a.setOnCheckedChangeListener(this);
        bi.PsbiTestB03b.setOnCheckedChangeListener(this);
        bi.PsbiTestB03c.setOnCheckedChangeListener(this);
        bi.PsbiTestB03d.setOnCheckedChangeListener(this);
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
            JSONObject json = GeneratorClass.getContainerJSON(bi.llPsbiTestB, true, type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.llPsbiTestB, true);
        } else {
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.llPsbiTestB, true, type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.llPsbiTestB, true);
        }

    }


    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.llPsbiTestB);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
