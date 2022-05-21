package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.isComplete;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.post_result;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp.pre_result;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.validatorcrawler.aliazaz.Validator;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityEndingBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.DialogEndBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments.ScheduleFragment;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces.Callbacks;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Forms;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;

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
                        openDialog();

                    } else {
                        Toast.makeText(EndingActivity.this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_end, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        DialogEndBinding bi = DataBindingUtil.bind(view);
        bi.completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(EndingActivity.this, MainActivity.class));
                isComplete = false;
                resetAll();
                dialog.dismiss();
            }
        });

        bi.makeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadScheduleFragment();
                isComplete = false;
                resetAll();
                dialog.dismiss();
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

        MainApp.forms.setIstatus(bi.statusa.isChecked() ? "1" : bi.statusb.isChecked() ? "2"
                : bi.statusc.isChecked() ? "3" : bi.statusd.isChecked() ? "4" : bi.status96.isChecked() ? "96" : "0");
        MainApp.forms.setIstatus88x(bi.status96x.getText().toString());
        MainApp.forms.setEndingdatetime(MainApp.getCurrentTime());
        MainApp.forms.setSessionEndTime(MainApp.getCurrentTime());
        MainApp.forms.setSessionCode(subMenuDT.getSessionCode());
        if (pre_result != null) {
            MainApp.forms.setScore_pre(String.valueOf(pre_result.getCorrect()));
            MainApp.forms.setWrong_pre(String.valueOf(pre_result.getWrong()));
            MainApp.forms.setPercentage_pre(String.valueOf(MainApp.roundOffFigure(pre_result.getPercentage(), 2)));
            MainApp.forms.setTotal(String.valueOf(pre_result.getTotal()));
        }

        MainApp.forms.setModuleCode(subMenuDT.getModuleCode());
        if (post_result != null) {
            MainApp.forms.setScore_post(String.valueOf(post_result.getCorrect()));
            MainApp.forms.setPercentage_post(String.valueOf(MainApp.roundOffFigure(post_result.getPercentage(), 2)));
            MainApp.forms.setWrong_post(String.valueOf(post_result.getWrong()));
        }
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrp);

    }

    @Override
    public void loadInfoFragment() {

    }

    @Override
    public void loadModuleFragment(Forms fc) {

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

    public void loadScheduleFragment() {
        ScheduleFragment fragment = new ScheduleFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
        transaction.replace(bi.fragmentLayout.getId(), fragment);
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
