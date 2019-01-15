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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession02PostTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession02_Post_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityGdssession02PostTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession02__post_test);
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
        JSONObject sCdb01post = GeneratorClass.getContainerJSON(bi.fldGrpPostGds02, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01post));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gds02post01, bi.gds02post01a, getString(R.string.gds02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02post02, bi.gds02post02a, getString(R.string.gds02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02post03, bi.gds02post03a, getString(R.string.gds02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02post04, bi.gds02post04a, getString(R.string.gds02_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gds02post05, bi.gds02post05a, getString(R.string.gds02_05));
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

        //GDS02-Q03
        if (compoundButton.getId() == R.id.gds02post03a
                || compoundButton.getId() == R.id.gds02post03b
                || compoundButton.getId() == R.id.gds02post03c
                || compoundButton.getId() == R.id.gds02post03d) {

            if (bi.gds02post03a.isChecked()) {
                bi.tvgds02post03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Not able to drink or breastfeed</i></b></font>.";
                bi.tvgds02post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02post03b.isChecked()) {
                bi.tvgds02post03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Vomits everything</i></b></font>.";
                bi.tvgds02post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02post03c.isChecked()) {
                bi.tvgds02post03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Lethargic</i></b></font>.";
                bi.tvgds02post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02post03d.isChecked()) {
                bi.tvgds02post03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Unconscious</i></b></font>.";
                bi.tvgds02post03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS02-Q05
        if (compoundButton.getId() == R.id.gds02post05a
                || compoundButton.getId() == R.id.gds02post05b
                || compoundButton.getId() == R.id.gds02post05c
                || compoundButton.getId() == R.id.gds02post05d) {

            if (bi.gds02post05a.isChecked()) {
                bi.tvgds02post05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>100-ml</i></b></font> cup of clean water.";
                bi.tvgds02post05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02post05b.isChecked()) {
                bi.tvgds02post05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>150-ml</i></b></font> cup of clean water.";
                bi.tvgds02post05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02post05c.isChecked()) {
                bi.tvgds02post05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>200-ml</i></b></font> cup of clean water.";
                bi.tvgds02post05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02post05d.isChecked()) {
                bi.tvgds02post05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>250-ml</i></b></font> cup of clean water.";
                bi.tvgds02post05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }
    }

    void events_call() {

        bi.gds02post03a.setOnCheckedChangeListener(this);
        bi.gds02post03b.setOnCheckedChangeListener(this);
        bi.gds02post03c.setOnCheckedChangeListener(this);
        bi.gds02post03d.setOnCheckedChangeListener(this);

        bi.gds02post05a.setOnCheckedChangeListener(this);
        bi.gds02post05b.setOnCheckedChangeListener(this);
        bi.gds02post05c.setOnCheckedChangeListener(this);
        bi.gds02post05d.setOnCheckedChangeListener(this);
    }
}
