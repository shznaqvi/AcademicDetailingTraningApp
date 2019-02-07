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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityPsbiTest01Binding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.slides;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class PsbiTest01 extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityPsbiTest01Binding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_psbi_test01);
        bi.setCallback(this);
        this.setTitle(getIntent().getStringExtra("mName"));

        events_call();
        setupViews();

    }


    private void setupViews() {

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
            GeneratorClass.comparingResult(bi.llPsbiTestA, true, Data.correctAnswers);
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
            GeneratorClass.comparingPostTestAndPretestResult(bi.llPsbiTestA, true, Data.correctAnswers);
            bi.btnOk.setVisibility(View.VISIBLE);
            bi.btnOk.setText("Finish Training");
            bi.btnContinue.setVisibility(View.GONE);
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //PsbiTest01-Q1
        if (compoundButton.getId() == R.id.PsbiTestA01a
                || compoundButton.getId() == R.id.PsbiTestA01b
                || compoundButton.getId() == R.id.PsbiTestA01c
                || compoundButton.getId() == R.id.PsbiTestA01d) {

            if (bi.PsbiTestA01a.isChecked()) {
                bi.tvPsbiTestA01.clearComposingText();
                String styledText = "Skin pustules among young infants are the sign of <font color='yellow'><b><i>Jaundice</i></b></font> .";
                bi.tvPsbiTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestA01b.isChecked()) {
                bi.tvPsbiTestA01.clearComposingText();
                String styledText = "Skin pustules among young infants are the sign of <font color='yellow'><b><i>Local Bacterial Infection</i></b></font> .";
                bi.tvPsbiTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestA01c.isChecked()) {
                bi.tvPsbiTestA01.clearComposingText();
                String styledText = "Skin pustules among young infants are the sign of <font color='yellow'><b><i>Thrush</i></b></font> .";
                bi.tvPsbiTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.PsbiTestA01d.isChecked()) {
                bi.tvPsbiTestA01.clearComposingText();
                String styledText = "Skin pustules among young infants are the sign of <font color='yellow'><b><i>Diarrhea</i></b></font> .";
                bi.tvPsbiTestA01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }


    void events_call() {

        bi.PsbiTestA01a.setOnCheckedChangeListener(this);
        bi.PsbiTestA01b.setOnCheckedChangeListener(this);
        bi.PsbiTestA01c.setOnCheckedChangeListener(this);
        bi.PsbiTestA01d.setOnCheckedChangeListener(this);
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
                    if (type.equals("pre")) {
                        if (MainApp.isSlideStart) {
                            startActivity(new Intent(this, PsbiTest01.class).putExtra("type", type));
                            isComplete = true;
                            GeneratorClass.incr = 0;
                            finish();
                        } else {
                            Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else if (type.equals("post")) {
                        startActivity(new Intent(this, PsbiTest01.class).putExtra("type", type));
                        isComplete = true;
                        GeneratorClass.incr = 0;
                        GeneratorClass.chkbxincr = 0;
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
            MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.llPsbiTestA, true, type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.llPsbiTestA, true);
        } else {
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.llPsbiTestA, true, type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.llPsbiTestA, true);
        }

    }


    private boolean formValidation() {

        return validatorClass.EmptyCheckingContainer(this, bi.llPsbiTestA);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }


}
