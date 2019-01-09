package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ViewPagerActivity;

public class Utils {

    public static final String[] childModule = new String[]{"General Danger Sign", "Cough & Difficult Breathing", "Diarrhoea", "PSBI"};
    public static final String[] maternalModule = new String[]{"Focused Antenatal Care", "Vaginal Bleeding in Pregnancy",
            "Partograph", "Pre Eclampsia & Eclampsia", " Postpartum Hemorrhage", "Assessment and management of Shock", " Puerperal Sepsis"};


    public static int[] vb_imgs = new int[]{R.drawable.vb_img_1,
            R.drawable.vb_img_5, R.drawable.vb_img_6, R.drawable.vb_img_7,
            R.drawable.vb_img_8, R.drawable.vb_img_9, R.drawable.vb_img_10, R.drawable.vb_img_11,
            R.drawable.vb_img_12, R.drawable.vb_img_13, R.drawable.vb_img_14, R.drawable.vb_img_15,
            R.drawable.vb_img_16, R.drawable.vb_img_17, R.drawable.vb_img_18, R.drawable.vb_img_19,
            R.drawable.vb_img_20, R.drawable.vb_img_21, R.drawable.vb_img_22, R.drawable.vb_img_23,
            R.drawable.vb_img_24};

    public static int[] fanc_imgs = new int[]{
            R.drawable.fanc_05, R.drawable.fanc_06,
            R.drawable.fanc_07, R.drawable.fanc_08,
            R.drawable.fanc_09, R.drawable.fanc_10,
            R.drawable.fanc_11, R.drawable.fanc_12,
            R.drawable.fanc_13, R.drawable.fanc_14,
            R.drawable.fanc_15, R.drawable.fanc_16,
            R.drawable.fanc_17, R.drawable.fanc_18,
            R.drawable.fanc_19, R.drawable.fanc_20,
            R.drawable.fanc_21, R.drawable.fanc_22,
            R.drawable.fanc_23, R.drawable.fanc_24,
            R.drawable.fanc_25, R.drawable.fanc_26,
            R.drawable.fanc_27, R.drawable.fanc_28,
            R.drawable.fanc_29, R.drawable.fanc_30,
            R.drawable.fanc_31, R.drawable.fanc_32,
            R.drawable.fanc_33, R.drawable.fanc_34,
            R.drawable.fanc_35, R.drawable.fanc_36,
            R.drawable.fanc_37, R.drawable.fanc_38,
            R.drawable.fanc_39, R.drawable.fanc_40,
            R.drawable.fanc_41, R.drawable.fanc_42,
            R.drawable.fanc_43, R.drawable.fanc_44,
            R.drawable.fanc_45, R.drawable.fanc_46,
            R.drawable.fanc_47, R.drawable.fanc_48,
            R.drawable.fanc_49, R.drawable.fanc_50,
            R.drawable.fanc_51, R.drawable.fanc_52,
            R.drawable.fanc_56};


    public static int[] getMaternalSessions(int i) {

        switch (i) {

            case 0:
                return fanc_imgs;

            case 1:
                return vb_imgs;

                default:
                    return null;
        }

    }

    public static void showMaternalPreDialogue(final Context context, final int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialoge_layout, null);
        ImageView slide = view.findViewById(R.id.slideImage);
        Button proceed = view.findViewById(R.id.proceed);
        Button cancel = view.findViewById(R.id.cancel);
        slide.setImageResource(getMaternalPreImages(index));
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.getMaternalSessions(index) != null) {
                    MainApp.isSlideStart = true;
                    MainApp.isMaternal = true;
                    context.startActivity(new Intent(context, GDSSession02_Pre_test.class)
                            .putExtra("slides", Utils.getMaternalSessions(index)));
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Module is under development", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
    }

    public static void showMaternalPostDialoge(final Context context,int index){

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
        slide.setImageResource(getMaternalPostImages(index));
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, GDSSession02_Pre_test.class)
                );
                ((Activity)context).finish();
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


    public static int getMaternalPostImages(int index){

        switch (index){
            case 0:
                return R.drawable.fanc_53;
            case 1:
                return R.drawable.vb_img_25;
            default:
                return R.drawable.fanc_02;
        }
    }

    public static int getMaternalPreImages(int index){

        switch (index){
            case 0:
                return R.drawable.fanc_02;
            case 1:
                return R.drawable.vb_img_2;
            default:
                return R.drawable.fanc_02;
        }
    }

}
