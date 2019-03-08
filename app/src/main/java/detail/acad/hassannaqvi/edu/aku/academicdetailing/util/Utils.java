package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EclamTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EndingActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.FANC_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HbbTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HemoTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HemoTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PartoTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PerpuralSepsisTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PlayerActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ShockTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.VB_Pre_test;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS.URI_DIRECT_VIEWPAGER;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS.URI_FORM_TYPE;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS.URI_SUBMENU_DT;
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
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.eclam_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.eclam_imgs;
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
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.hemo1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.hemo2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.hemotest01_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.hemotest02_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.parto_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.parto_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbi1_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbi2_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbi3_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbia_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbib_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.psbic_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.sepsis_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.sepsistest_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.shock_imgs;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.shocktest_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.vb_cans;
import static detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data.vb_imgs;

public class Utils {


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


    public static int[] getMaternalSessions(int i) {

        switch (i) {

            case 0:
                return fanc_imgs;

            case 1:
                return vb_imgs;

            case 2:
                return parto_imgs;

            case 3:
                return eclam_imgs;

            case 4:
                return hemo1_imgs;

            case 5:
                return hemo2_imgs;

            case 6:
                return shock_imgs;

            case 7:
                return sepsis_imgs;

            default:
                return null;
        }

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
        } else if (MainApp.isNBorn) {
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
        slide.setImageResource(R.drawable.eclam1051);
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
                                : MainApp.isNBorn ? Utils.getChildSessions(index, moduleName)
                                : null).putExtra("type", "post"));
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

    public static void showPostDialoge(final Context context, final Data.SubMenu subMenu) {

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

                Intent intent = new Intent(context, PlayerActivity.class).putExtra("submenu", subMenu);
                context.startActivity(intent);

                MediaPlayer md = MediaPlayer.create(context, R.raw.gds01);
                long duration = md.getDuration();


                forcePostTest(context, subMenu, duration);
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

    public static Class<?> selectTest(int index, String moduleName) {

        if (MainApp.isMaternal) {

            switch (index) {

                case 0:
                    return FANC_Pre_test.class;
                case 1:
                    return VB_Pre_test.class;
                case 2:
                    return PartoTest.class;
                case 3:
                    return EclamTest.class;
                case 4:
                    return HemoTest01.class;
                case 5:
                    return HemoTest02.class;
                case 6:
                    return ShockTest.class;
                case 7:
                    return PerpuralSepsisTest.class;
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
                case 2:
                    return parto_cans;
                case 3:
                    return eclam_cans;
                case 4:
                    return hemotest01_cans;
                case 5:
                    return hemotest02_cans;
                case 6:
                    return shocktest_cans;
                case 7:
                    return sepsistest_cans;
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
                    case 2:
                        return psbic_cans;
                }

            }


        } else if (MainApp.isNBorn) {
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


    public static int getPostImages() {

        return R.drawable.eclam1051;
    }

    public static int getPreImages(int index, String moduleName) {

        if (MainApp.isMaternal) {
            switch (index) {
                case 0:
                    return R.drawable.fanc_02;
                case 1:
                    return R.drawable.vb_img_2;
                case 2:
                    return R.drawable.parto1001;
                case 3:
                    return R.drawable.eclam1001;
                case 4:
                    return R.drawable.hemo1001;
                case 5:
                    return R.drawable.hemo2001;
                case 6:
                    return R.drawable.shock1001;
                case 7:
                    return R.drawable.sepsis1001;
                default:
                    return R.drawable.fanc_02;
            }
        } else if (MainApp.isChild) {
            if (moduleName.equals("gds")) {
                switch (index) {
                    case 0:
                        return R.drawable.gds1001;
                    case 1:
                        return R.drawable.gds2001;
                    default:
                        return 0;
                }

            } else if (moduleName.equals("cdb")) {
                switch (index) {
                    case 0:
                        return R.drawable.cdb1001;
                    case 1:
                        return R.drawable.cdb2001;
                    default:
                        return 0;
                }

            } else if (moduleName.equals("dia")) {
                switch (index) {
                    case 0:
                        return R.drawable.dia1001;
                    case 1:
                        return R.drawable.cdb2001;
                    default:
                        return 0;
                }

            } else if (moduleName.equals("psbi")) {
                switch (index) {
                    case 0:
                        return R.drawable.psbi1001;
                    case 1:
                        return R.drawable.psbi2001;
                    case 2:
                        return R.drawable.psbi3001;
                    default:
                        return 0;
                }

            }
        } else if (MainApp.isNBorn) {
            if (moduleName.equals("eceb")) {
                switch (index) {
                    case 0:
                        return R.drawable.eceb1001;
                    case 1:
                        return R.drawable.eceb2001;
                    default:
                        return 0;
                }

            } else if (moduleName.equals("ecsb")) {
                switch (index) {
                    case 0:
                        return R.drawable.ecsb1001;
                    case 1:
                        return R.drawable.ecsb2001;
                    default:
                        return 0;
                }

            }
        }

        return R.drawable.hbb1001;

    }


}
