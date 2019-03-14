package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EndingActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PlayerActivity;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS.URI_DIRECT_VIEWPAGER;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS.URI_FORM_TYPE;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS.URI_SUBMENU_DT;

public class Utils {

    public static void showPreDialogue(final Context context, final Data.SubMenu subMenu, final FormsContract fc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialoge_layout, null);
        ImageView slide = view.findViewById(R.id.slideImage);
        Button proceed = view.findViewById(R.id.proceed);
        Button cancel = view.findViewById(R.id.cancel);
        slide.setImageResource(subMenu.getPreImage());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainApp.isSlideStart = true;
                context.startActivity(new Intent(context, subMenu.getRouteClass())
                        .putExtra(URI_FORM_TYPE, "pre")
                        .putExtra(URI_DIRECT_VIEWPAGER, true)
                        .putExtra(URI_SUBMENU_DT, subMenu));
                MainApp.fc.setSessionStartTime(MainApp.getCurrentTime());

                savingDataIntoDB(context, fc);
                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }

    public static void savingDataIntoDB(Context context, FormsContract fc) {
        DatabaseHelper db = new DatabaseHelper(context);
        long rowId = db.addForm(fc);
        MainApp.fc.set_ID(String.valueOf(rowId));
    }

    public static void showPostDialoge(final Context context, final Data.SubMenu subMenu, final int type) {

        MainApp.isSlideStart = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialoge_layout, null);
        ImageView slide = view.findViewById(R.id.slideImage);
        Button proceed = view.findViewById(R.id.proceed);
        if (MainApp.isSlideStart) {
            proceed.setText("Proceed To Pre Test");
        } else {
            proceed.setText("Proceed To Post Test");
        }
        Button cancel = view.findViewById(R.id.cancel);
        slide.setImageResource(R.drawable.eclam1051);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                if (type == 1) {
                    Intent intent = new Intent(context, PlayerActivity.class).putExtra("submenu", subMenu);
                    context.startActivity(intent);
                } else
                    forcePostTest(context, subMenu, 0);

                /*MediaPlayer md = MediaPlayer.create(context, R.raw.gds01);
                long duration = md.getDuration();*/


//                forcePostTest(context, subMenu, 0);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }

    public static void forcePostTest(final Context context, final Data.SubMenu subMenu, long duration) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                context.startActivity(new Intent(context, subMenu.getRouteClass())
                        .putExtra(URI_SUBMENU_DT, subMenu)
                        .putExtra("type", "post"));
                ((Activity) context).finish();

            }
        }, duration);
    }

    public static void showViewPagerDialoge(final Context context, final Data.SubMenu subMenu) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialoge_layout, null);
        ImageView slide = view.findViewById(R.id.slideImage);
        Button proceed = view.findViewById(R.id.proceed);
        proceed.setText("Proceed To End Test");
        Button cancel = view.findViewById(R.id.cancel);
        slide.setImageResource(R.drawable.eclam1051);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EndingActivity.class).putExtra(CONSTANTS.URI_SUBMENU_DT, subMenu)
                        .putExtra("complete", true));
                ((Activity) context).finish();
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

    }

}
