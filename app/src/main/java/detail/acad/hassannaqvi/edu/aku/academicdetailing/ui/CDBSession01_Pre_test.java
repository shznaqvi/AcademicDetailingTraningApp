package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityCdbsession01PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Result;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static android.support.constraint.Constraints.TAG;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass.correct;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.JSON.GeneratorClass.wrong;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.pre_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.type;

public class CDBSession01_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityCdbsession01PreTestBinding bi;
    Data.SubMenu subMenuDT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_cdbsession01__pre_test);
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
            GeneratorClass.comparingResult(bi.fldGrpPreCdb01, true, subMenuDT.getAnswers());
            pre_result = GeneratorClass.getResults("pre", subMenuDT.getAnswers());
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
            GeneratorClass.comparingPostTestAndPretestResult(bi.fldGrpPreCdb01, true, subMenuDT.getAnswers());
            post_result = GeneratorClass.getResults("post", subMenuDT.getAnswers());
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
                            startActivity(new Intent(this, CDBSession01_Pre_test.class)
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
                        startActivity(new Intent(this, CDBSession01_Pre_test.class)
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
            MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreCdb01, true, type);
            MainApp.fc.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreCdb01, true);
        } else {
            MainApp.fc.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreCdb01, true, type);
            MainApp.fc.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreCdb01, true);
        }

    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.cdba01, bi.cdba01a, getString(R.string.cdb01_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdba02, bi.cdba02a, getString(R.string.cdb01_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.cdba03, bi.cdba03a, getString(R.string.cdb01_03))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.cdba04, bi.cdba04a, getString(R.string.cdb01_04));
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        //CDB-Q1  
        if (compoundButton.getId() == R.id.cdba01a
                || compoundButton.getId() == R.id.cdba01b
                || compoundButton.getId() == R.id.cdba01c
                || compoundButton.getId() == R.id.cdba01d) {

            if (bi.cdba01a.isChecked()) {
                bi.tvcdba01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>Tuberculosis</i></b></font>.";
                bi.tvcdba01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba01b.isChecked()) {
                bi.tvcdba01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>Asthma</i></b></font>.";
                bi.tvcdba01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba01c.isChecked()) {
                bi.tvcdba01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>Whooping cough</i></b></font>.";
                bi.tvcdba01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba01d.isChecked()) {
                bi.tvcdba01.clearComposingText();
                String styledText = "Cough or difficult breathing that lasts for more than 14 days may indicate <font color='yellow'><b><i>All of the below</i></b></font>.";
                bi.tvcdba01.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //CDB-Q3  
        if (compoundButton.getId() == R.id.cdba03a
                || compoundButton.getId() == R.id.cdba03b
                || compoundButton.getId() == R.id.cdba03c
                || compoundButton.getId() == R.id.cdba03d) {

            if (bi.cdba03a.isChecked()) {
                bi.tvcdba03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Wheeze</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdba03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba03b.isChecked()) {
                bi.tvcdba03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Chest in-drawing</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdba03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba03c.isChecked()) {
                bi.tvcdba03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Cough</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdba03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba03d.isChecked()) {
                bi.tvcdba03.clearComposingText();
                String styledText = "Fast breathing or <font color='yellow'><b><i>Fever</i></b></font> if post sent in a child between 2 months to 5 years of age are the clinical signs for Pneumonia.";
                bi.tvcdba03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //CDB-Q4   
        if (compoundButton.getId() == R.id.cdba04a
                || compoundButton.getId() == R.id.cdba04b) {

            if (bi.cdba04a.isChecked()) {
                bi.tvcdba04.clearComposingText();
                String styledText = "The child has chest in drawing if the lower chest wall (lower ribs) goes <font color='yellow'><b><i>IN</i></b></font> when the child breathes IN.";
                bi.tvcdba04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.cdba04b.isChecked()) {
                bi.tvcdba04.clearComposingText();
                String styledText = "The child has chest in drawing if the lower chest wall (lower ribs) goes <font color='yellow'><b><i>OUT</i></b></font> when the child breathes IN.";
                bi.tvcdba04.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }

    }

    void events_call() {

        bi.cdba01a.setOnCheckedChangeListener(this);
        bi.cdba01b.setOnCheckedChangeListener(this);
        bi.cdba01c.setOnCheckedChangeListener(this);
        bi.cdba01d.setOnCheckedChangeListener(this);

        bi.cdba03a.setOnCheckedChangeListener(this);
        bi.cdba03b.setOnCheckedChangeListener(this);
        bi.cdba03c.setOnCheckedChangeListener(this);
        bi.cdba03d.setOnCheckedChangeListener(this);

        bi.cdba04a.setOnCheckedChangeListener(this);
        bi.cdba04b.setOnCheckedChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
