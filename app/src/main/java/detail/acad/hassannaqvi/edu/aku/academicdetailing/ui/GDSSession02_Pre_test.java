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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession02PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession02_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityGdssession02PreTestBinding bi;
    Data.SubMenu subMenuDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession02__pre_test);
        bi.setCallback(this);

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
            GeneratorClass.comparingResult(bi.fldGrpPreGds02, true, subMenuDT.getAnswers());
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
            GeneratorClass.comparingPostTestAndPretestResult(bi.fldGrpPreGds02, true, subMenuDT.getAnswers());
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
                            startActivity(new Intent(this, GDSSession02_Pre_test.class)
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
                        startActivity(new Intent(this, GDSSession02_Pre_test.class)
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

    private void SaveDraft() {

        if(type.equals("pre")){
            MainApp.forms.setPreTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreGds02, true,type);
            MainApp.forms.setPre_test(String.valueOf(json));
            Data.pretestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreGds02, true);
        }else{
            MainApp.forms.setPostTestEndTime(MainApp.getCurrentTime());
            JSONObject json = GeneratorClass.getContainerJSON(bi.fldGrpPreGds02, true,type);
            MainApp.forms.setPost_test(String.valueOf(json));
            Data.posttestAnswers = GeneratorClass.getAnswers(bi.fldGrpPreGds02, true);
        }
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gdsb01, bi.gdsb01a, getString(R.string.gds02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gdsb02, bi.gdsb02a, getString(R.string.gds02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gdsb03, bi.gdsb03a, getString(R.string.gds02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gdsb04, bi.gdsb04a, getString(R.string.gds02_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gdsb05, bi.gdsb05a, getString(R.string.gds02_05));
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        //GDS02-Q03
        if (compoundButton.getId() == R.id.gdsb03a
                || compoundButton.getId() == R.id.gdsb03b
                || compoundButton.getId() == R.id.gdsb03c
                || compoundButton.getId() == R.id.gdsb03d) {

            if (bi.gdsb03a.isChecked()) {
                bi.tvgdsb03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Not able to drink or breastfeed</i></b></font>.";
                bi.tvgdsb03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsb03b.isChecked()) {
                bi.tvgdsb03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Vomits everything</i></b></font>.";
                bi.tvgdsb03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsb03c.isChecked()) {
                bi.tvgdsb03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Lethargic</i></b></font>.";
                bi.tvgdsb03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsb03d.isChecked()) {
                bi.tvgdsb03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Unconscious</i></b></font>.";
                bi.tvgdsb03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS02-Q05
        if (compoundButton.getId() == R.id.gdsb05a
                || compoundButton.getId() == R.id.gdsb05b
                || compoundButton.getId() == R.id.gdsb05c
                || compoundButton.getId() == R.id.gdsb05d) {

            if (bi.gdsb05a.isChecked()) {
                bi.tvgdsb05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>100-ml</i></b></font> cup of clean water.";
                bi.tvgdsb05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsb05b.isChecked()) {
                bi.tvgdsb05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>150-ml</i></b></font> cup of clean water.";
                bi.tvgdsb05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsb05c.isChecked()) {
                bi.tvgdsb05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>200-ml</i></b></font> cup of clean water.";
                bi.tvgdsb05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gdsb05d.isChecked()) {
                bi.tvgdsb05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>250-ml</i></b></font> cup of clean water.";
                bi.tvgdsb05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }
    }

    void events_call() {

        bi.gdsb03a.setOnCheckedChangeListener(this);
        bi.gdsb03b.setOnCheckedChangeListener(this);
        bi.gdsb03c.setOnCheckedChangeListener(this);
        bi.gdsb03d.setOnCheckedChangeListener(this);

        bi.gdsb05a.setOnCheckedChangeListener(this);
        bi.gdsb05b.setOnCheckedChangeListener(this);
        bi.gdsb05c.setOnCheckedChangeListener(this);
        bi.gdsb05d.setOnCheckedChangeListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
