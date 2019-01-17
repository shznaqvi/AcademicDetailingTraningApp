package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.FANC_Post_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.FANC_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.VB_Pre_test;

public class Utils {

    public static final String[] modules = new String[]{"Child Health","Maternal Health","New Born Health"};
    public static final String[] childModule = new String[]{"General Danger Sign", "Cough & Difficult Breathing", "Diarrhoea", "PSBI"};
    public static final String[] GDS = new String[]{"GDS(Assessment and Classification)", "GDS(Management, Counseling and Referral)"};
    public static final String[] CDB = new String[]{"CDB(Assessment and Classification)", "CDB(Management, Counseling and Referral)"};
    public static final String[] Diarrhea = new String[]{"Dia(Assessment and Classification)", "Dia(Management, Counseling and Referral)"};
    public static final String[] PSBI = new String[]{"PSBI(Assessment and Classification)", "PSBI(Management, Counseling and Referral)", "PSBI(Breast Feeding & Immunization)"};
    public static final String[] maternalModule = new String[]{"Focused Antenatal Care", "Vaginal Bleeding in Pregnancy",
            "Partograph", "Pre Eclampsia & Eclampsia", " Postpartum Hemorrhage", "Assessment and management of Shock", " Puerperal Sepsis"};


    public static int[] vb_imgs = new int[]{
            R.drawable.vb_img_1, R.drawable.vb_img_5, R.drawable.vb_img_6, R.drawable.vb_img_7, R.drawable.vb_img_8,
            R.drawable.vb_img_9, R.drawable.vb_img_10, R.drawable.vb_img_11, R.drawable.vb_img_12, R.drawable.vb_img_13,
            R.drawable.vb_img_14, R.drawable.vb_img_15, R.drawable.vb_img_16, R.drawable.vb_img_17, R.drawable.vb_img_18,
            R.drawable.vb_img_19, R.drawable.vb_img_20, R.drawable.vb_img_21, R.drawable.vb_img_22, R.drawable.vb_img_23,
            R.drawable.vb_img_24};

    public static int[] fanc_imgs = new int[]{
            R.drawable.fanc_05, R.drawable.fanc_06, R.drawable.fanc_07, R.drawable.fanc_08, R.drawable.fanc_09,
            R.drawable.fanc_10, R.drawable.fanc_11, R.drawable.fanc_12, R.drawable.fanc_13, R.drawable.fanc_14,
            R.drawable.fanc_15, R.drawable.fanc_16, R.drawable.fanc_17, R.drawable.fanc_18, R.drawable.fanc_19,
            R.drawable.fanc_20, R.drawable.fanc_21, R.drawable.fanc_22, R.drawable.fanc_23, R.drawable.fanc_24,
            R.drawable.fanc_25, R.drawable.fanc_26, R.drawable.fanc_27, R.drawable.fanc_28, R.drawable.fanc_29,
            R.drawable.fanc_30, R.drawable.fanc_31, R.drawable.fanc_32, R.drawable.fanc_33, R.drawable.fanc_34,
            R.drawable.fanc_35, R.drawable.fanc_36, R.drawable.fanc_37, R.drawable.fanc_38, R.drawable.fanc_39,
            R.drawable.fanc_40, R.drawable.fanc_41, R.drawable.fanc_42, R.drawable.fanc_43, R.drawable.fanc_44,
            R.drawable.fanc_45, R.drawable.fanc_46, R.drawable.fanc_47, R.drawable.fanc_48, R.drawable.fanc_49,
            R.drawable.fanc_50, R.drawable.fanc_51, R.drawable.fanc_52, R.drawable.fanc_56};


    public static int[] gds1_imgs = new int[]{
            R.drawable.gds1003, R.drawable.gds1004, R.drawable.gds1005, R.drawable.gds1006, R.drawable.gds1007,
            R.drawable.gds1008, R.drawable.gds1009, R.drawable.gds1010, R.drawable.gds1011, R.drawable.gds1012,
            R.drawable.gds1013, R.drawable.gds1014, R.drawable.gds1015, R.drawable.gds1016, R.drawable.gds1017,
            R.drawable.gds1018, R.drawable.gds1019, R.drawable.gds1020,};


    public static int[] gds2_imgs = new int[]{
            R.drawable.gds2003, R.drawable.gds2004, R.drawable.gds2005, R.drawable.gds2006, R.drawable.gds2007,
            R.drawable.gds2008, R.drawable.gds2009, R.drawable.gds2010, R.drawable.gds2011, R.drawable.gds2012,
            R.drawable.gds2013, R.drawable.gds2014, R.drawable.gds2015, R.drawable.gds2016, R.drawable.gds2017,
            R.drawable.gds2018, R.drawable.gds2019, R.drawable.gds2020,};


    public static int[] cdb1_imgs = new int[]{
            R.drawable.cdb1003, R.drawable.cdb1004, R.drawable.cdb1005, R.drawable.cdb1006, R.drawable.cdb1007,
            R.drawable.cdb1008, R.drawable.cdb1009, R.drawable.cdb1010, R.drawable.cdb1011, R.drawable.cdb1012,
            R.drawable.cdb1013, R.drawable.cdb1014, R.drawable.cdb1015, R.drawable.cdb1016, R.drawable.cdb1017,
            R.drawable.cdb1018, R.drawable.cdb1019, R.drawable.cdb1020, R.drawable.cdb1021, R.drawable.cdb1022,
            R.drawable.cdb1023, R.drawable.cdb1024,};


    public static int[] cdb2_imgs = new int[]{
            R.drawable.cdb2003, R.drawable.cdb2004, R.drawable.cdb2005, R.drawable.cdb2006, R.drawable.cdb2007,
            R.drawable.cdb2008, R.drawable.cdb2009, R.drawable.cdb2010, R.drawable.cdb2011, R.drawable.cdb2012,
            R.drawable.cdb2013, R.drawable.cdb2014, R.drawable.cdb2015, R.drawable.cdb2016, R.drawable.cdb2017,
            R.drawable.cdb2018, R.drawable.cdb2019, R.drawable.cdb2020, R.drawable.cdb2021, R.drawable.cdb2022,
            R.drawable.cdb2023, R.drawable.cdb2024, R.drawable.cdb2025, R.drawable.cdb2026,};


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


    public static int[] getChildSessions(int i, String moduleName) {

        if (moduleName.equals("gds")) {
            switch (i) {
                case 0:
                    return gds1_imgs;

                case 1:
                    return gds2_imgs;


                default:
                    return null;
            }

        } else if (moduleName.equals("cdb")) {
            switch (i) {
                case 0:
                    return cdb1_imgs;

                case 1:
                    return cdb2_imgs;

                default:
                    return null;
            }

        } else if (moduleName.equals("dia")) {

        } else if (moduleName.equals("psbi")) {

        }
        return null;

    }

    public static void showPreDialogue(final Context context, final int index, final String moduleName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialoge_layout, null);
        ImageView slide = view.findViewById(R.id.slideImage);
        Button proceed = view.findViewById(R.id.proceed);
        Button cancel = view.findViewById(R.id.cancel);
        if (MainApp.isMaternal) {
            slide.setImageResource(getPreImages(index, moduleName));
        } else if (MainApp.isChild) {
            slide.setImageResource(getPreImages(index, moduleName));
        }
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainApp.isSlideStart = true;
                context.startActivity(new Intent(context, selectTest(index, moduleName))
                        .putExtra("slides", MainApp.isMaternal ? Utils.getMaternalSessions(index)
                                : MainApp.isChild ? Utils.getChildSessions(index, moduleName) : null).putExtra("type","pre"));
                MainApp.fc.setSessionStartTime(MainApp.getCurrentTime());
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

    public static void showPostDialoge(final Context context, final int index, final String moduleName) {

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
        if (MainApp.isMaternal) {
            slide.setImageResource(getPostImages(index, MainApp.isMaternal));
        } else if (MainApp.isChild) {
            slide.setImageResource(getPostImages(index, MainApp.isChild));
        }
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context, selectTest(index, moduleName))
                        .putExtra("slides", MainApp.isMaternal ? Utils.getMaternalSessions(index)
                                : MainApp.isChild ? Utils.getChildSessions(index, moduleName) : null).putExtra("type","post"));
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

    public static Class<?> selectTest(int index, String moduleName) {

        if (MainApp.isMaternal) {

            switch (index) {

                case 0:
                    return FANC_Pre_test.class;
                case 1:
                    return VB_Pre_test.class;
            }
        } else if (MainApp.isChild) {

            if (moduleName.equals("gds")) {

                switch (index) {
                    case 0:
                        return GDSSession01_Pre_test.class;
                    case 1:
                        return GDSSession02_Pre_test.class;
                }

            } else if (moduleName.equals("cdb")) {
                switch (index) {
                    case 0:
                        return CDBSession01_Pre_test.class;
                    case 1:
                        return CDBSession02_Pre_test.class;
                }

            }


        }
        return null;

    }

    public static Class<?> selectPostTest(int index, String moduleName) {

        if (MainApp.isMaternal) {

            switch (index) {

                case 0:
                    return FANC_Post_test.class;
                case 1:
                    return VB_Post_test.class;
            }
        } else if (MainApp.isChild) {

            if (moduleName.equals("gds")) {
                switch (index) {
                    case 0:
                        return GDSSession01_Post_test.class;
                    case 1:
                        return GDSSession02_Post_test.class;
                }
            } else if (moduleName.equals("cdb")) {
                switch (index) {
                    case 0:
                        return CDBSession01_Post_test.class;
                    case 1:
                        return CDBSession02_Post_test.class;
                }

            }


        }
        return null;

    }


    public static int getPostImages(int index, boolean type) {

        if (type == MainApp.isMaternal) {
            switch (index) {
                case 0:
                    return R.drawable.fanc_53;
                case 1:
                    return R.drawable.vb_img_25;
                default:
                    return R.drawable.fanc_02;
            }
        } else if (type == MainApp.isChild) {
            switch (index) {
                case 0:
                    return R.drawable.gds1001;
                case 1:
                    return R.drawable.gds2001;
                default:
                    return R.drawable.gdssummary;
            }
        }
        return 0;

    }

    public static int getPreImages(int index, String moduleName) {

        if (MainApp.isMaternal) {
            switch (index) {
                case 0:
                    return R.drawable.fanc_02;
                case 1:
                    return R.drawable.vb_img_2;
                default:
                    return R.drawable.fanc_02;
            }
        } else if (MainApp.isChild) {
            if (moduleName.equals("gds")) {
                switch (index) {
                    case 0:
                        return R.drawable.gds1002;
                    case 1:
                        return R.drawable.gds2002;
                    default:
                        return 0;
                }

            } else if (moduleName.equals("cdb")) {
                switch (index) {
                    case 0:
                        return R.drawable.cdb1002;
                    case 1:
                        return R.drawable.cdb2002;
                    default:
                        return 0;
                }

            } else if (moduleName.equals("cdb")) {

            } else if (moduleName.equals("psbi")) {

            }else if(moduleName.equals("dia")){

            }

        }

        return 0;

    }

}
