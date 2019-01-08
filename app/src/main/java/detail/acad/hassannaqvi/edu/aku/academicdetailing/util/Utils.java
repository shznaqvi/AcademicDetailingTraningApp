package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;

public class Utils {

    public static final String[] childModule = new String[]{"General Danger Sign", "Cough & Difficult Breathing", "Diarrhoea", "PSBI"};
    public static final String[] GDS = new String[]{"Assessment & Classification", "Management Counseling & Referral"};
    public static final String[] CDB = new String[]{"Assessment & Classification", "Management Counseling & Referral"};
    public static final String[] Diarrhea = new String[]{"Assessment & Classification", "Management Counseling & Referral"};
    public static final String[] PSBI = new String[]{"Assessment & Classification", "Management Counseling & Referral", "Breast Feeding & Immunization"};
    public static final String[] maternalModule = new String[]{"Focused Antenatal Care", "Vaginal Bleeding in Pregnancy",
            "Partograph", "Pre Eclampsia & Eclampsia", " Postpartum Hemorrhage", "Assessment and management of Shock", " Puerperal Sepsis"};


    public static int[] vb_imgs = new int[]{R.drawable.vb_img_1, R.drawable.vb_img_2, R.drawable.vb_img_3,
            R.drawable.vb_img_4, R.drawable.vb_img_5, R.drawable.vb_img_6, R.drawable.vb_img_7,
            R.drawable.vb_img_8, R.drawable.vb_img_9, R.drawable.vb_img_10, R.drawable.vb_img_11,
            R.drawable.vb_img_12, R.drawable.vb_img_13, R.drawable.vb_img_14, R.drawable.vb_img_15,
            R.drawable.vb_img_16, R.drawable.vb_img_17, R.drawable.vb_img_18, R.drawable.vb_img_19,
            R.drawable.vb_img_20, R.drawable.vb_img_21, R.drawable.vb_img_22, R.drawable.vb_img_23,
            R.drawable.vb_img_24, R.drawable.vb_img_25};

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
            R.drawable.fanc_53, R.drawable.fanc_54,
            R.drawable.fanc_55, R.drawable.fanc_56};


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

}
