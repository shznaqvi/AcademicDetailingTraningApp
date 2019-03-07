package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityEndingBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ScheduleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.pre_result;

public class EndingActivity extends AppCompatActivity implements Callbacks {

    ActivityEndingBinding bi;
    Data.SubMenu subMenuDT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        bi.setCallback(this);

        subMenuDT = (Data.SubMenu) getIntent().getSerializableExtra(CONSTANTS.URI_SUBMENU_DT);

        if (getIntent().getBooleanExtra("complete", false)) {
            bi.statusa.setChecked(true);
            bi.statusb.setChecked(false);
            bi.statusb.setEnabled(false);
            bi.statusc.setChecked(false);
            bi.statusc.setEnabled(false);
            bi.statusd.setChecked(false);
            bi.statusd.setEnabled(false);
            bi.status96.setChecked(false);
            bi.status96.setEnabled(false);
            bi.status96x.setText(null);
        } else {
            bi.statusa.setChecked(false);
            bi.statusa.setEnabled(false);

        }

        bi.finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formValidation()) {
                    SaveDraft();
                    if (UpdateDB()) {
                        loadScheduleFragment();
                        isComplete = false;
                        resetAll();
                    } else {
                        Toast.makeText(EndingActivity.this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void resetAll() {
        MainApp.pre_result = null;
        MainApp.post_result = null;
    }


    private boolean UpdateDB() {

        DatabaseHelper db = new DatabaseHelper(this);
        int count = db.updateEnding();
        if (count == 1) {

            return true;
        } else {
            Toast.makeText(this, "Error in update DB", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() {

        MainApp.fc.setIstatus(bi.statusa.isChecked() ? "1" : bi.statusb.isChecked() ? "2"
                : bi.statusc.isChecked() ? "3" : bi.statusd.isChecked() ? "4" : bi.status96.isChecked() ? "96" : "0");
        MainApp.fc.setIstatus88x(bi.status96x.getText().toString());
        MainApp.fc.setEndingdatetime(MainApp.getCurrentTime());
        MainApp.fc.setSessionEndTime(MainApp.getCurrentTime());
        MainApp.fc.setSessionCode(subMenuDT.getSessionCode());
        MainApp.fc.setScore_pre(String.valueOf(pre_result.getCorrect()));
        MainApp.fc.setWrong_pre(String.valueOf(pre_result.getWrong()));
        MainApp.fc.setPercentage_pre(String.valueOf(MainApp.round(pre_result.getPercentage(),2)));
        MainApp.fc.setTotal(String.valueOf(pre_result.getTotal()));
        MainApp.fc.setModuleCode(subMenuDT.getModuleCode());
        if(post_result != null){
            MainApp.fc.setScore_post(String.valueOf(post_result.getCorrect()));
            MainApp.fc.setPercentage_post(String.valueOf(MainApp.round(post_result.getPercentage(),2)));
            MainApp.fc.setWrong_post(String.valueOf(post_result.getWrong()));
        }
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.status, bi.statusa, getString(R.string.status))) {
            return false;
        }
        if (bi.status96.isChecked()) {
            return validatorClass.EmptyTextBox(this, bi.status96x, getString(R.string.status));
        }

        return true;
    }

    @Override
    public void loadInfoFragment() {

    }

    @Override
    public void loadModuleFragment(FormsContract fc) {

    }

    @Override
    public void loadDatabaseManager() {

    }

    @Override
    public void uploadDataToServer() {

    }

    @Override
    public void downloadData() {

    }

    @Override
    public void loadInfo() {

    }

    public void loadScheduleFragment(){
        ScheduleFragment fragment = new ScheduleFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in,R.anim.slide_out);
        transaction.replace(bi.fragmentLayout.getId(),fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void uploadAppointment() {

    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
