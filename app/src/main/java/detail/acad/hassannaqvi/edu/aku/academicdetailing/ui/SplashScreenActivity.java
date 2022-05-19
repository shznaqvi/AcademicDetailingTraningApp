package detail.acad.hassannaqvi.edu.aku.academicdetailing.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.RetrofitClient.RetrofitClient;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        RetrofitClient.createRetrofitInstance();

// Clear Old Data Irfan


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

// show login or main page
                Intent mainIntent;
                // mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                //mainIntent = new Intent(SplashScreenActivity.this, FirstCard.class);

                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, 1500);
    }

    public static void showProgress(Context context, String label, String message) {

    }

    public static void hideProgress(Context context) {
        KProgressHUD hud = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        hud.dismiss();
    }


}