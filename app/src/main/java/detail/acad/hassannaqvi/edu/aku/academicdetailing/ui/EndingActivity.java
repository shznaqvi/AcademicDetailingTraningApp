package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.Date;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityEndingBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.validation.validatorClass;

public class EndingActivity extends AppCompatActivity {


    ActivityEndingBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_ending);
        bi.setCallback(this);


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
                        startActivity(new Intent(EndingActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(EndingActivity.this, "Error in updating db!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
    }

    private boolean formValidation() {

        if (!validatorClass.EmptyRadioButton(this, bi.status, bi.statusa, getString(R.string.status))) {
            return false;
        }
        if (bi.status96.isChecked()) {
            if (!validatorClass.EmptyTextBox(this, bi.status96x, getString(R.string.status))) {
                return false;
            }
        }

        return true;
    }
}
