package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.ActivityHbbTestBinding;


public class HbbTest extends AppCompatActivity implements RadioButton.OnCheckedChangeListener {

    ActivityHbbTestBinding bi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this, R.layout.activity_hbb_test);
        bi.setCallback(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}
