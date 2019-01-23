package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import java.util.ArrayList;
import java.util.Arrays;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;

public class Data {
    public static final String[] modules = new String[]{"Child Health","Maternal Health","New Born Health"};
    public static final String[] childModule = new String[]{"General Danger Sign", "Cough & Difficult Breathing", "Diarrhoea", "PSBI"};
    public static final String[] GDS = new String[]{"GDS(Assessment and Classification)", "GDS(Management, Counseling and Referral)"};
    public static final String[] CDB = new String[]{"CDB(Assessment and Classification)", "CDB(Management, Counseling and Referral)"};
    public static final String[] Diarrhea = new String[]{"Dia(Assessment and Classification)", "Dia(Management, Counseling and Referral)"};
    public static final String[] PSBI = new String[]{"PSBI(Assessment and Classification)", "PSBI(Management, Counseling and Referral)", "PSBI(Breast Feeding & Immunization)"};
    public static final String[] maternalModule = new String[]{"Focused Antenatal Care", "Vaginal Bleeding in Pregnancy",
            "Partograph", "Pre Eclampsia & Eclampsia", " Postpartum Hemorrhage", "Assessment and management of Shock", " Puerperal Sepsis"};

    public static final String[] newBornModule = new String[]{"Essential Care for Every Baby", "Essential Care for Small Babies", "Helping Babies Breathe"};
    public static final String[] ECEB = new String[]{"ECEB(Session One)", "ECEB(Session Two)"};
    public static final String[] ECSB = new String[]{"ECSB(Session One)", "ECSB(Session Two)"};

    public static ArrayList<String> fanc_cans = new ArrayList<String>(Arrays.asList("3","4","4","3","3"));
    public static ArrayList<String> pretestAnswers = new ArrayList<>();
    public static ArrayList<String> posttestAnswers = new ArrayList<>();
    public static ArrayList<String> correctAnswers = new ArrayList<>();

    public static ArrayList<String> vb_cans = new ArrayList<String>(Arrays.asList("2", "3", "4", "3", "2"));
    public static ArrayList<String> gdsa_cans = new ArrayList<String>(Arrays.asList("4", "1", "1", "3", "4"));
    public static ArrayList<String> gdsb_cans = new ArrayList<String>(Arrays.asList("2", "3", "3", "1", "3"));
    public static ArrayList<String> cdba_cans = new ArrayList<String>(Arrays.asList("1", "3", "2", "2"));
    public static ArrayList<String> cdbb_cans = new ArrayList<String>(Arrays.asList("3", "3", "2", "1", "4", "4"));

    public static ArrayList<String> fanc_cb = new ArrayList<String>(Arrays.asList("1", "3", "4", "7", "9", "11"));

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
            R.drawable.gds1018, R.drawable.gds1019, R.drawable.gds1020,1,0};


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
}
