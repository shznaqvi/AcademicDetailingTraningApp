package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.FANC_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HbbTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.VB_Pre_test;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.cdb1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.cdb2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.cdba_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.cdbb_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.dia1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.dia2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.diaa_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.diab_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.eceb01_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.eceb02_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.eceb1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.eceb2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ecsb1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ecsb2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ecsbA_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.ecsbB_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.fanc_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.fanc_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.gds1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.gds2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.gdsa_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.gdsb_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.hbb_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.hbb_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbi1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbi2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbi3_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbia_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbib_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.vb_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.vb_imgs;

public class Utils {


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
            switch (i) {
                case 0:
                    return dia1_imgs;

                case 1:
                    return dia2_imgs;

                default:
                    return null;
            }

        } else if (moduleName.equals("psbi")) {
            switch (i) {
                case 0:
                    return psbi1_imgs;

                case 1:
                    return psbi2_imgs;

                case 2:
                    return psbi3_imgs;

                default:
                    return null;
            }

        }
        return null;

    }

    public static int[] getNBSessions(int i, String moduleName) {

        if (moduleName.equals("eceb")) {
            switch (i) {
                case 0:
                    return eceb1_imgs;

                case 1:
                    return eceb2_imgs;

                default:
                    return null;
            }

        } else if (moduleName.equals("ecsb")) {
            switch (i) {
                case 0:
                    return ecsb1_imgs;

                case 1:
                    return ecsb2_imgs;

                default:
                    return null;
            }

        } else {
            return hbb_imgs;
        }


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
        }else if (MainApp.isNBorn){
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
                                : MainApp.isChild ? Utils.getChildSessions(index, moduleName)
                                : MainApp.isNBorn ? Utils.getNBSessions(index, moduleName) : null)
                        .putExtra("type", "pre")
                        .putExtra("ans", selectAnswers(index, moduleName)));
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
                                : MainApp.isChild ? Utils.getChildSessions(index, moduleName)
                                : MainApp.isNBorn ? Utils.getChildSessions(index, moduleName) : null).putExtra("type", "post"));
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

            } else if (moduleName.equals("dia")) {
                switch (index) {
                    case 0:
                        return DiaTest01.class;
                    case 1:
                        return DiaTest02.class;
                }

            } else if (moduleName.equals("psbi")) {
                switch (index) {
                    case 0:
                        return PsbiTest01.class;
                    case 1:
                        return PsbiTest02.class;
                }

            }


        } else if (MainApp.isNBorn) {
            if (moduleName.equals("eceb")) {
                switch (index) {
                    case 0:
                        return EcEbTest01.class;
                    case 1:
                        return EcEbTest02.class;

                }
            } else if (moduleName.equals("ecsb")) {
                switch (index) {
                    case 0:
                        return EcSbTest01.class;
                    case 1:
                        return EcSbTest02.class;

                }
            } else {
                return HbbTest.class;
            }
        }
        return null;

    }

    public static ArrayList<String> selectAnswers(int index, String moduleName) {

        if (MainApp.isMaternal) {

            switch (index) {

                case 0:
                    return fanc_cans;
                case 1:
                    return vb_cans;
            }
        } else if (MainApp.isChild) {

            if (moduleName.equals("gds")) {

                switch (index) {
                    case 0:
                        return gdsa_cans;
                    case 1:
                        return gdsb_cans;
                }

            } else if (moduleName.equals("cdb")) {
                switch (index) {
                    case 0:
                        return cdba_cans;
                    case 1:
                        return cdbb_cans;
                }

            } else if (moduleName.equals("dia")) {
                switch (index) {
                    case 0:
                        return diaa_cans;
                    case 1:
                        return diab_cans;
                }

            } else if (moduleName.equals("psbi")) {
                switch (index) {
                    case 0:
                        return psbia_cans;
                    case 1:
                        return psbib_cans;
                }

            }


        }else if(MainApp.isNBorn){
            if (moduleName.equals("eceb")) {

                switch (index) {
                    case 0:
                        return eceb01_cans;
                    case 1:
                        return eceb02_cans;
                }

            } else if (moduleName.equals("ecsb")) {
                switch (index) {
                    case 0:
                        return ecsbA_cans;
                    case 1:
                        return ecsbB_cans;
                }

            } else
                return hbb_cans;
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

            } else if (moduleName.equals("psbi")) {

            } else if (moduleName.equals("dia")) {

            }
        } else if (MainApp.isNBorn) {
            if (moduleName.equals("eceb")) {
                switch (index) {
                    case 0:
                        return R.drawable.eceb1001;
                    case 1:
                        return R.drawable.eceb1001;
                    default:
                        return 0;
                }

            }
        }

        return 0;

    }


}
