package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
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
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityGdssession02PreTestBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class GDSSession02_Pre_test extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityGdssession02PreTestBinding bi;
    private static final String TAG = "GDSSession02_Pre_test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_gdssession02__pre_test);
        bi.setCallback(this);

        events_call();

        MainApp.fc.setPreTestStartTime(MainApp.getCurrentTime());
        if(MainApp.isSlideStart){
            bi.btnContinue.setText("Start Training");
        }else{
            bi.btnContinue.setText("Finish Training");
        }

    }



    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
                if (UpdateDB()) {
                    if (MainApp.isSlideStart) {
                        startActivity(new Intent(this, ViewPagerActivity.class).putExtra("slides", getIntent().getIntArrayExtra("slides")));
                        finish();
                    } else {
                        Toast.makeText(this, "Training Completed", Toast.LENGTH_SHORT).show();
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
        int count = db.updatePreTest();
        if (count == 1) {
            return true;
        } else {
            Toast.makeText(this, "Error in update DB", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() {

        MainApp.fc.setPreTestEndTime(MainApp.getCurrentTime());
        JSONObject sCdb01pre = GeneratorClass.getContainerJSON(bi.fldGrpPreGds02, true);
        MainApp.fc.setPre_test(String.valueOf(sCdb01pre));
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre01, bi.gds02pre01a, getString(R.string.gds02_01))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre02, bi.gds02pre02a, getString(R.string.gds02_02))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre03, bi.gds02pre03a, getString(R.string.gds02_03))) {
            return false;
        }
        if (!validatorClass.EmptyRadioButton(this, bi.gds02pre04, bi.gds02pre04a, getString(R.string.gds02_04))) {
            return false;
        }
        return validatorClass.EmptyRadioButton(this, bi.gds02pre05, bi.gds02pre05a, getString(R.string.gds02_05));
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
        if (compoundButton.getId() == R.id.gds02pre03a
                || compoundButton.getId() == R.id.gds02pre03b
                || compoundButton.getId() == R.id.gds02pre03c
                || compoundButton.getId() == R.id.gds02pre03d) {

            if (bi.gds02pre03a.isChecked()) {
                bi.tvgds02pre03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Not able to drink or breastfeed</i></b></font>.";
                bi.tvgds02pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02pre03b.isChecked()) {
                bi.tvgds02pre03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Vomits everything</i></b></font>.";
                bi.tvgds02pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02pre03c.isChecked()) {
                bi.tvgds02pre03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Lethargic</i></b></font>.";
                bi.tvgds02pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02pre03d.isChecked()) {
                bi.tvgds02pre03.clearComposingText();
                String styledText = "A child who is not able to hold anything down at all has the sign <font color='yellow'><b><i>Unconscious</i></b></font>.";
                bi.tvgds02pre03.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }


        //GDS02-Q05
        if (compoundButton.getId() == R.id.gds02pre05a
                || compoundButton.getId() == R.id.gds02pre05b
                || compoundButton.getId() == R.id.gds02pre05c
                || compoundButton.getId() == R.id.gds02pre05d) {

            if (bi.gds02pre05a.isChecked()) {
                bi.tvgds02pre05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>100-ml</i></b></font> cup of clean water.";
                bi.tvgds02pre05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02pre05b.isChecked()) {
                bi.tvgds02pre05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>150-ml</i></b></font> cup of clean water.";
                bi.tvgds02pre05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02pre05c.isChecked()) {
                bi.tvgds02pre05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>200-ml</i></b></font> cup of clean water.";
                bi.tvgds02pre05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            } else if (bi.gds02pre05d.isChecked()) {
                bi.tvgds02pre05.clearComposingText();
                String styledText = "To make sugar water: Dissolve 4 level teaspoons of sugar (20 grams) in a <font color='yellow'><b><i>250-ml</i></b></font> cup of clean water.";
                bi.tvgds02pre05.setText(Html.fromHtml(styledText), TextView.BufferType.SPANNABLE);
            }
        }
    }

    void events_call() {

        bi.gds02pre03a.setOnCheckedChangeListener(this);
        bi.gds02pre03b.setOnCheckedChangeListener(this);
        bi.gds02pre03c.setOnCheckedChangeListener(this);
        bi.gds02pre03d.setOnCheckedChangeListener(this);

        bi.gds02pre05a.setOnCheckedChangeListener(this);
        bi.gds02pre05b.setOnCheckedChangeListener(this);
        bi.gds02pre05c.setOnCheckedChangeListener(this);
        bi.gds02pre05d.setOnCheckedChangeListener(this);
    }
}
