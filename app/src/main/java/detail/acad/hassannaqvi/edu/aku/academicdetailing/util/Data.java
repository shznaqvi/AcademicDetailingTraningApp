package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EclamTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.FANC_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession02_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HbbTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HemoTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HemoTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PartoTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PerpuralSepsisTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ShockTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.VB_Pre_test;


public final class Data {
    public static final String[] modules = new String[]{"Child Health", "Maternal Health", "New Born Health"};
    public static final String[] childModule = new String[]{"General Danger Sign", "Cough & Difficult Breathing", "Diarrhoea", "PSBI"};
    public static final String[] GDS = new String[]{"GDS(Assessment and Classification)", "GDS(Management, Counseling and Referral)"};
    public static final String[] CDB = new String[]{"CDB(Assessment and Classification)", "CDB(Management, Counseling and Referral)"};
    public static final String[] Diarrhea = new String[]{"Dia(Assessment and Classification)", "Dia(Management, Counseling and Referral)"};
    public static final String[] PSBI = new String[]{"PSBI(Assessment and Classification)", "PSBI(Management, Counseling and Referral)", "PSBI(Breast Feeding & Immunization)"};
    public static final String[] maternalModule = new String[]{"Focused Antenatal Care", "Vaginal Bleeding in Pregnancy",
            "Partograph", "Pre Eclampsia & Eclampsia", "Postpartum Hemorrhage - 1", "Postpartum Hemorrhage - 2", "Assessment and management of Shock", "Puerperal Sepsis"};

    public static final String[] newBornModule =
            new String[]{
                    "Essential Care for Every Baby",
                    "Essential Care for Small Babies",
                    "Helping Babies Breathe"
            };

    public static final String[] ECEB = new String[]{"ECEB(Session One)", "ECEB(Session Two)"};
    public static final String[] ECSB = new String[]{"ECSB(Session One)", "ECSB(Session Two)"};
    public static final String[] HBB = new String[]{"Helping Babies Breathe"};
    public static final ArrayList<String> newBornMenu = new ArrayList<>();
    public static Map<String, SubMenu[]> newMenuModule;

    public static ArrayList<String> pretestAnswers = new ArrayList<>();
    public static ArrayList<String> checkboxPreAnswers = new ArrayList<>();
    public static ArrayList<String> checkboxPostAnswers = new ArrayList<>();
    public static ArrayList<String> posttestAnswers = new ArrayList<>();
    public static ArrayList<String> correctAnswers = new ArrayList<>();


    public static ArrayList<String> gdsa_cans = new ArrayList<String>(Arrays.asList("4", "1", "1", "3", "4"));
    public static ArrayList<String> gdsb_cans = new ArrayList<String>(Arrays.asList("2", "3", "3", "1", "3"));
    public static ArrayList<String> cdba_cans = new ArrayList<String>(Arrays.asList("1", "3", "2", "2"));
    public static ArrayList<String> cdbb_cans = new ArrayList<String>(Arrays.asList("3", "3", "2", "1", "4", "4"));
    public static ArrayList<String> diaa_cans = new ArrayList<String>(Arrays.asList("1", "2", "4", "1", "4"));
    public static ArrayList<String> diab_cans = new ArrayList<String>(Arrays.asList("2", "1", "1", "2", "2"));
    public static ArrayList<String> psbia_cans = new ArrayList<String>(Arrays.asList("1", "3"));
    public static ArrayList<String> psbib_cans = new ArrayList<String>(Arrays.asList("2", "3", "3", "2", "3"));
    public static ArrayList<String> psbic_cans = new ArrayList<String>(Arrays.asList("1", "3", "2", "2"));

    public static ArrayList<String> fanc_cb = new ArrayList<String>(Arrays.asList("1", "0", "3", "4", "0", "0", "7", "0", "9", "0", "11"));
    public static ArrayList<String> fanc_cans = new ArrayList<String>(Arrays.asList("3", "4", "4", "3", "3"));
    public static ArrayList<String> vb_cans = new ArrayList<String>(Arrays.asList("2", "3", "4", "3", "2"));
    public static ArrayList<String> parto_cans = new ArrayList<String>(Arrays.asList("1", "3", "2", "2", "2"));
    public static ArrayList<String> eclam_cans = new ArrayList<String>(Arrays.asList("3", "2", "2", "4", "1"));
    public static ArrayList<String> hemotest01_cans = new ArrayList<String>(Arrays.asList("2", "2", "4", "3", "4"));
    public static ArrayList<String> hemotest02_cans = new ArrayList<String>(Arrays.asList("2", "3", "4", "4", "4"));
    public static ArrayList<String> shocktest_cans = new ArrayList<String>(Arrays.asList("2", "3", "1", "4", "4"));
    public static ArrayList<String> sepsistest_cans = new ArrayList<String>(Arrays.asList("3", "4", "2", "2", "2"));

    public static ArrayList<String> eceb01_cans = new ArrayList<String>(Arrays.asList("1", "3", "1", "4", "2", "3", "1", "2", "1", "3", "2", "3"));
    public static ArrayList<String> eceb02_cans = new ArrayList<String>(Arrays.asList("4", "1", "3", "3", "1", "2", "2", "2", "4", "1", "2", "4", "2"));
    public static ArrayList<String> ecebA_cans = new ArrayList<String>(Arrays.asList("1", "3", "1", "4", "2", "3", "1", "2", "1", "3", "2", "3"));
    public static ArrayList<String> ecebB_cans = new ArrayList<String>(Arrays.asList("4", "1", "3", "3", "1", "2", "2", "2", "4", "1", "2", "4", "2"));

    public static ArrayList<String> ecsbA_cans = new ArrayList<String>(Arrays.asList("2", "3", "1", "3", "3", "4", "3", "2", "3", "4", "1", "3"));
    public static ArrayList<String> ecsbB_cans = new ArrayList<String>(Arrays.asList("1", "1", "4", "1", "2", "2", "2", "3", "1", "2", "1", "2", "3"));

    public static ArrayList<String> hbb_cans = new ArrayList<String>(Arrays.asList("2", "1", "3", "3", "2", "2", "1", "3", "4", "4", "3", "2", "2", "4", "4", "1", "1"));

    public static int[] mainSlides = new int[]{
            R.drawable.cdb,R.drawable.diarrhea,R.drawable.eclampsia,R.drawable.fanc,R.drawable.gds,R.drawable.graph,
            R.drawable.mother1,R.drawable.newborn,R.drawable.posthemo,R.drawable.shock,R.drawable.sick,R.drawable.vb
    };

    public static int[] gds1_imgs = new int[]{
            R.drawable.gds1002, R.drawable.gds1003, R.drawable.gds1004, R.drawable.gds1005, R.drawable.gds1006,
            R.drawable.gds1007, R.drawable.gds1008, R.drawable.gds1009, R.drawable.gds1010, R.drawable.gds1011,
            R.drawable.gds1012, R.drawable.gds1013, R.drawable.gds1014, R.drawable.gds1015, R.drawable.gds1016,
            R.drawable.gds1017, R.drawable.gds1018};

    public static int[] gds2_imgs = new int[]{
            R.drawable.gds2002, R.drawable.gds2003, R.drawable.gds2004, R.drawable.gds2005, R.drawable.gds2006,
            R.drawable.gds2007, R.drawable.gds2008, R.drawable.gds2009, R.drawable.gds2010, R.drawable.gds2011,
            R.drawable.gds2012, R.drawable.gds2013, R.drawable.gds2014, R.drawable.gds2015, R.drawable.gds2016,
            R.drawable.gds2017, R.drawable.gds2018, R.drawable.gds2019, R.drawable.gds2020,};

    public static int[] cdb1_imgs = new int[]{
            R.drawable.cdb1002, R.drawable.cdb1003, R.drawable.cdb1004, R.drawable.cdb1005, R.drawable.cdb1006,
            R.drawable.cdb1007, R.drawable.cdb1008, R.drawable.cdb1009, R.drawable.cdb1010, R.drawable.cdb1011,
            R.drawable.cdb1012, R.drawable.cdb1013, R.drawable.cdb1014, R.drawable.cdb1015, R.drawable.cdb1016,
            R.drawable.cdb1017, R.drawable.cdb1018, R.drawable.cdb1019, R.drawable.cdb1020, R.drawable.cdb1021,
            R.drawable.cdb1022, R.drawable.cdb1023,};

    public static int[] cdb2_imgs = new int[]{
            R.drawable.cdb2002, R.drawable.cdb2003, R.drawable.cdb2004, R.drawable.cdb2005, R.drawable.cdb2006,
            R.drawable.cdb2007, R.drawable.cdb2008, R.drawable.cdb2009, R.drawable.cdb2010, R.drawable.cdb2011,
            R.drawable.cdb2012, R.drawable.cdb2013, R.drawable.cdb2014, R.drawable.cdb2015, R.drawable.cdb2016,
            R.drawable.cdb2017, R.drawable.cdb2018, R.drawable.cdb2019, R.drawable.cdb2020, R.drawable.cdb2021,
            R.drawable.cdb2022,};

    public static int[] dia1_imgs = new int[]{
            R.drawable.dia1001, R.drawable.dia1002, R.drawable.dia1003, R.drawable.dia1004, R.drawable.dia1005,
            R.drawable.dia1006, R.drawable.dia1007, R.drawable.dia1008, R.drawable.dia1009, R.drawable.dia1010,
            R.drawable.dia1011, R.drawable.dia1012, R.drawable.dia1013, R.drawable.dia1014, R.drawable.dia1015,
            R.drawable.dia1016, R.drawable.dia1017, R.drawable.dia1018, R.drawable.dia1019, R.drawable.dia1020,
            R.drawable.dia1021, R.drawable.dia1022, R.drawable.dia1023, R.drawable.dia1024, R.drawable.dia1025,
            R.drawable.dia1026,};

    public static int[] dia2_imgs = new int[]{
            R.drawable.dia2001, R.drawable.dia2002, R.drawable.dia2003, R.drawable.dia2004, R.drawable.dia2005,
            R.drawable.dia2006, R.drawable.dia2007, R.drawable.dia2008, R.drawable.dia2009, R.drawable.dia2010,
            R.drawable.dia2011, R.drawable.dia2012, R.drawable.dia2013, R.drawable.dia2014, R.drawable.dia2015,
            R.drawable.dia2016, R.drawable.dia2017, R.drawable.dia2018, R.drawable.dia2019, R.drawable.dia2020,
            R.drawable.dia2021, R.drawable.dia2022, R.drawable.dia2023, R.drawable.dia2024, R.drawable.dia2025,
            R.drawable.dia2026, R.drawable.dia2027, R.drawable.dia2028, R.drawable.dia2029, R.drawable.dia2030,
            R.drawable.dia2031, R.drawable.dia2032, R.drawable.dia2033, R.drawable.dia2034, R.drawable.dia2035,
    };

    public static int[] psbi1_imgs = new int[]{
            R.drawable.psbi1001, R.drawable.psbi1002, R.drawable.psbi1003, R.drawable.psbi1004, R.drawable.psbi1005,
            R.drawable.psbi1006, R.drawable.psbi1007, R.drawable.psbi1008, R.drawable.psbi1009, R.drawable.psbi1010,
            R.drawable.psbi1011, R.drawable.psbi1012, R.drawable.psbi1013, R.drawable.psbi1014, R.drawable.psbi1015,
            R.drawable.psbi1016, R.drawable.psbi1017, R.drawable.psbi1018, R.drawable.psbi1019, R.drawable.psbi1020,
            R.drawable.psbi1021,};

    public static int[] psbi2_imgs = new int[]{
            R.drawable.psbi2001, R.drawable.psbi2002, R.drawable.psbi2003, R.drawable.psbi2004, R.drawable.psbi2005,
            R.drawable.psbi2006, R.drawable.psbi2007, R.drawable.psbi2008, R.drawable.psbi2009, R.drawable.psbi2010,
            R.drawable.psbi2011, R.drawable.psbi2012, R.drawable.psbi2013, R.drawable.psbi2014, R.drawable.psbi2015,
            R.drawable.psbi2016, R.drawable.psbi2017, R.drawable.psbi2018, R.drawable.psbi2019, R.drawable.psbi2020,
            R.drawable.psbi2021, R.drawable.psbi2022, R.drawable.psbi2023, R.drawable.psbi2024, R.drawable.psbi2025,
            R.drawable.psbi2026, R.drawable.psbi2027, R.drawable.psbi2028, R.drawable.psbi2029, R.drawable.psbi2030,
    };

    public static int[] psbi3_imgs = new int[]{
            R.drawable.psbi3001, R.drawable.psbi3002, R.drawable.psbi3003, R.drawable.psbi3004, R.drawable.psbi3005,
            R.drawable.psbi3006, R.drawable.psbi3007, R.drawable.psbi3008, R.drawable.psbi3009, R.drawable.psbi3010,
            R.drawable.psbi3011, R.drawable.psbi3012, R.drawable.psbi3013, R.drawable.psbi3014, R.drawable.psbi3015,};


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
            R.drawable.fanc_50, R.drawable.fanc_51, R.drawable.fanc_52};

    public static int[] vb_imgs = new int[]{
            R.drawable.vb_img_1, R.drawable.vb_img_5, R.drawable.vb_img_6, R.drawable.vb_img_7, R.drawable.vb_img_8,
            R.drawable.vb_img_9, R.drawable.vb_img_10, R.drawable.vb_img_11, R.drawable.vb_img_12, R.drawable.vb_img_13,
            R.drawable.vb_img_14, R.drawable.vb_img_15, R.drawable.vb_img_16, R.drawable.vb_img_17, R.drawable.vb_img_18,
            R.drawable.vb_img_19, R.drawable.vb_img_20, R.drawable.vb_img_21, R.drawable.vb_img_22, R.drawable.vb_img_23,
            R.drawable.vb_img_24};

    public static int[] parto_imgs = new int[]{
            R.drawable.parto1002, R.drawable.parto1003, R.drawable.parto1004, R.drawable.parto1005, R.drawable.parto1006,
            R.drawable.parto1007, R.drawable.parto1008, R.drawable.parto1009, R.drawable.parto1010, R.drawable.parto1011,
            R.drawable.parto1012, R.drawable.parto1013, R.drawable.parto1014, R.drawable.parto1015, R.drawable.parto1016,
            R.drawable.parto1017, R.drawable.parto1018, R.drawable.parto1019, R.drawable.parto1020, R.drawable.parto1021,
            R.drawable.parto1022, R.drawable.parto1023, R.drawable.parto1024, R.drawable.parto1025, R.drawable.parto1026,
            R.drawable.parto1027, R.drawable.parto1028, R.drawable.parto1029, R.drawable.parto1030, R.drawable.parto1031,
            R.drawable.parto1032, R.drawable.parto1033, R.drawable.parto1034, R.drawable.parto1035,};


    public static int[] eclam_imgs = new int[]{
            R.drawable.eclam1002, R.drawable.eclam1003, R.drawable.eclam1004, R.drawable.eclam1005, R.drawable.eclam1006,
            R.drawable.eclam1007, R.drawable.eclam1008, R.drawable.eclam1009, R.drawable.eclam1010, R.drawable.eclam1011,
            R.drawable.eclam1012, R.drawable.eclam1013, R.drawable.eclam1014, R.drawable.eclam1015, R.drawable.eclam1016,
            R.drawable.eclam1017, R.drawable.eclam1018, R.drawable.eclam1019, R.drawable.eclam1020, R.drawable.eclam1021,
            R.drawable.eclam1022, R.drawable.eclam1023, R.drawable.eclam1024, R.drawable.eclam1025, R.drawable.eclam1026,
            R.drawable.eclam1027, R.drawable.eclam1028, R.drawable.eclam1029, R.drawable.eclam1030, R.drawable.eclam1031,
            R.drawable.eclam1032, R.drawable.eclam1033, R.drawable.eclam1034, R.drawable.eclam1035, R.drawable.eclam1036,
            R.drawable.eclam1037, R.drawable.eclam1038, R.drawable.eclam1039, R.drawable.eclam1040, R.drawable.eclam1041,
            R.drawable.eclam1042, R.drawable.eclam1043, R.drawable.eclam1044, R.drawable.eclam1045, R.drawable.eclam1046,
            R.drawable.eclam1047, R.drawable.eclam1048, R.drawable.eclam1049, R.drawable.eclam1050,};


    public static int[] hemo1_imgs = new int[]{
            R.drawable.hemo1002, R.drawable.hemo1003, R.drawable.hemo1004, R.drawable.hemo1005, R.drawable.hemo1006,
            R.drawable.hemo1007, R.drawable.hemo1008, R.drawable.hemo1009, R.drawable.hemo1010, R.drawable.hemo1011,
            R.drawable.hemo1012, R.drawable.hemo1013, R.drawable.hemo1014, R.drawable.hemo1015, R.drawable.hemo1016,
            R.drawable.hemo1017, R.drawable.hemo1018, R.drawable.hemo1019,};


    public static int[] hemo2_imgs = new int[]{
            R.drawable.hemo2002, R.drawable.hemo2003, R.drawable.hemo2004, R.drawable.hemo2005, R.drawable.hemo2006,
            R.drawable.hemo2007, R.drawable.hemo2008, R.drawable.hemo2009, R.drawable.hemo2010, R.drawable.hemo2011,
            R.drawable.hemo2012, R.drawable.hemo2013, R.drawable.hemo2014, R.drawable.hemo2015, R.drawable.hemo2016,
            R.drawable.hemo2017, R.drawable.hemo2018, R.drawable.hemo2019, R.drawable.hemo2020,};


    public static int[] shock_imgs = new int[]{
            R.drawable.shock1002, R.drawable.shock1003, R.drawable.shock1004, R.drawable.shock1005, R.drawable.shock1006,
            R.drawable.shock1007, R.drawable.shock1008, R.drawable.shock1009, R.drawable.shock1010, R.drawable.shock1011,
            R.drawable.shock1012, R.drawable.shock1013, R.drawable.shock1014, R.drawable.shock1015, R.drawable.shock1016,
            R.drawable.shock1017, R.drawable.shock1018, R.drawable.shock1019, R.drawable.shock1020, R.drawable.shock1021,
            R.drawable.shock1022, R.drawable.shock1023, R.drawable.shock1024, R.drawable.shock1025,};


    public static int[] sepsis_imgs = new int[]{
            R.drawable.sepsis1002, R.drawable.sepsis1003, R.drawable.sepsis1004, R.drawable.sepsis1005, R.drawable.sepsis1006,
            R.drawable.sepsis1007, R.drawable.sepsis1008, R.drawable.sepsis1009, R.drawable.sepsis1010, R.drawable.sepsis1011,
            R.drawable.sepsis1012, R.drawable.sepsis1013, R.drawable.sepsis1014, R.drawable.sepsis1015, R.drawable.sepsis1016,
            R.drawable.sepsis1017, R.drawable.sepsis1018, R.drawable.sepsis1019, R.drawable.sepsis1020, R.drawable.sepsis1021,
            R.drawable.sepsis1022, R.drawable.sepsis1023, R.drawable.sepsis1024, R.drawable.sepsis1025, R.drawable.sepsis1026,
            R.drawable.sepsis1027, R.drawable.sepsis1028, R.drawable.sepsis1029,};


    public static int[] eceb1_imgs = new int[]{
            R.drawable.eceb1002, R.drawable.action_plan, R.drawable.eceb1003, R.drawable.eceb1004, R.drawable.eceb1005,
            R.drawable.eceb1006, R.drawable.eceb1007, R.drawable.eceb1008, R.drawable.eceb1009, R.drawable.eceb1010,
            R.drawable.eceb1011, R.drawable.eceb1012, R.drawable.eceb1013, R.drawable.eceb1014, R.drawable.eceb1015,
            R.drawable.eceb1016, R.drawable.eceb1017, R.drawable.eceb1018, R.drawable.eceb1019, R.drawable.eceb1020,
            R.drawable.eceb1021, R.drawable.eceb1022, R.drawable.eceb1023, R.drawable.eceb1024, R.drawable.eceb1025,
            R.drawable.eceb1026, R.drawable.eceb1027, R.drawable.eceb1028, R.drawable.eceb1029, R.drawable.eceb1030,
            R.drawable.eceb1031, R.drawable.eceb1032, R.drawable.eceb1033, R.drawable.eceb1034, R.drawable.eceb1035,
            R.drawable.eceb1036, R.drawable.eceb1037, R.drawable.eceb1038, R.drawable.eceb1039, R.drawable.eceb1040,
            R.drawable.eceb1041,};

    public static int[] eceb2_imgs = new int[]{
            R.drawable.eceb2001, R.drawable.eceb2002, R.drawable.eceb2003, R.drawable.eceb2004, R.drawable.eceb2005,
            R.drawable.eceb2006, R.drawable.eceb2007, R.drawable.eceb2008, R.drawable.eceb2009, R.drawable.eceb2010,
            R.drawable.eceb2011, R.drawable.eceb2012, R.drawable.eceb2013, R.drawable.eceb2014, R.drawable.eceb2015,
            R.drawable.eceb2016, R.drawable.eceb2017, R.drawable.eceb2018, R.drawable.eceb2019, R.drawable.eceb2020,
            R.drawable.eceb2021, R.drawable.eceb2022, R.drawable.eceb2023, R.drawable.eceb2024, R.drawable.eceb2025,
            R.drawable.eceb2026,};

    public static int[] ecsb1_imgs = new int[]{
            R.drawable.ecsb1001, R.drawable.ecsb1002, R.drawable.ecsb1003, R.drawable.ecsb1004, R.drawable.ecsb1005,
            R.drawable.ecsb1006, R.drawable.ecsb1007, R.drawable.ecsb1008, R.drawable.ecsb1009, R.drawable.ecsb1010,
            R.drawable.ecsb1011, R.drawable.ecsb1012, R.drawable.ecsb1013, R.drawable.ecsb1014, R.drawable.ecsb1015,
            R.drawable.ecsb1016, R.drawable.ecsb1017, R.drawable.ecsb1018, R.drawable.ecsb1019, R.drawable.ecsb1020,
            R.drawable.ecsb1021, R.drawable.ecsb1022, R.drawable.ecsb1023, R.drawable.ecsb1024, R.drawable.ecsb1025,
            R.drawable.ecsb1026, R.drawable.ecsb1027, R.drawable.ecsb1028, R.drawable.ecsb1029, R.drawable.ecsb1030,
            R.drawable.ecsb1031, R.drawable.ecsb1032, R.drawable.ecsb1033, R.drawable.ecsb1034, R.drawable.ecsb1035,
            R.drawable.ecsb1036, R.drawable.ecsb1037, R.drawable.ecsb1038, R.drawable.ecsb1039, R.drawable.ecsb1040,
            R.drawable.ecsb1041, R.drawable.ecsb1042, R.drawable.ecsb1043, R.drawable.ecsb1044, R.drawable.ecsb1045,
            R.drawable.ecsb1046, R.drawable.ecsb1047, R.drawable.ecsb1048,};

    public static int[] ecsb2_imgs = new int[]{
            R.drawable.ecsb2001, R.drawable.ecsb2002, R.drawable.ecsb2003, R.drawable.ecsb2004, R.drawable.ecsb2005,
            R.drawable.ecsb2006, R.drawable.ecsb2007, R.drawable.ecsb2008,};

    public static int[] hbb_imgs = new int[]{
            R.drawable.hbb1001, R.drawable.hbb1002, R.drawable.hbb1003, R.drawable.hbb1004, R.drawable.hbb1005,
            R.drawable.hbb1006, R.drawable.hbb1007, R.drawable.hbb1008, R.drawable.hbb1009, R.drawable.hbb1010,
            R.drawable.hbb1011, R.drawable.hbb1012, R.drawable.hbb1013, R.drawable.hbb1014, R.drawable.hbb1015,
            R.drawable.hbb1016, R.drawable.hbb1017, R.drawable.hbb1018, R.drawable.hbb1019, R.drawable.hbb1020,
            R.drawable.hbb1021, R.drawable.hbb1022, R.drawable.hbb1023, R.drawable.hbb1024, R.drawable.hbb1025,
            R.drawable.hbb1026, R.drawable.hbb1027, R.drawable.hbb1028, R.drawable.hbb1029, R.drawable.hbb1030,
            R.drawable.hbb1031, R.drawable.hbb1032, R.drawable.hbb1033, R.drawable.hbb1034, R.drawable.hbb1035,
            R.drawable.hbb1036, R.drawable.hbb1037, R.drawable.hbb1038, R.drawable.hbb1039, R.drawable.hbb1040,
            R.drawable.hbb1041, R.drawable.hbb1042, R.drawable.hbb1043, R.drawable.hbb1044, R.drawable.hbb1045,
            R.drawable.hbb1046};

    public static void fillingMenus(int type) {

        newMenuModule = new HashMap<>();

        if (type == 0) {
            newMenuModule.put("General Danger Sign",
                    new SubMenu[]{
                            new SubMenu("GDS(Assessment and Classification)", GDSSession01_Pre_test.class, R.drawable.gds1001, gds1_imgs, gdsa_cans),
                            new SubMenu("GDS(Management, Counseling and Referral)", GDSSession02_Pre_test.class, R.drawable.gds2001, gds2_imgs, gdsb_cans)
                    }
            );
            newMenuModule.put("Cough & Difficult Breathing",
                    new SubMenu[]{
                            new SubMenu("CDB(Assessment and Classification)", CDBSession01_Pre_test.class, R.drawable.cdb1001, cdb1_imgs, cdba_cans),
                            new SubMenu("CDB(Management, Counseling and Referral)", CDBSession02_Pre_test.class, R.drawable.cdb2001, cdb2_imgs, cdbb_cans)
                    }
            );
            newMenuModule.put("Diarrhoea",
                    new SubMenu[]{
                            new SubMenu("Dia(Assessment and Classification)", DiaTest01.class, R.drawable.dia1001, dia1_imgs, diaa_cans),
                            new SubMenu("Dia(Management, Counseling and Referral)", DiaTest02.class, R.drawable.cdb2001, dia2_imgs, diab_cans)
                    }
            );
            newMenuModule.put("PSBI",
                    new SubMenu[]{
                            new SubMenu("PSBI(Assessment and Classification)", PsbiTest01.class, R.drawable.psbi1001, psbi1_imgs, psbia_cans),
                            new SubMenu("PSBI(Management, Counseling and Referral)", PsbiTest02.class, R.drawable.psbi2001, psbi2_imgs, psbib_cans),
                            new SubMenu("PSBI(Breast Feeding & Immunization)", PsbiTest02.class, R.drawable.psbi3001, psbi3_imgs, psbic_cans)
                    }
            );
        } else if (type == 1) {
            newMenuModule.put("Focused Antenatal Care",
                    new SubMenu[]{
                            new SubMenu("Focused Antenatal Care", FANC_Pre_test.class, R.drawable.fanc_02, fanc_imgs, fanc_cans)
                    });
            newMenuModule.put("Vaginal Bleeding in Pregnancy",
                    new SubMenu[]{
                            new SubMenu("Vaginal Bleeding in Pregnancy", VB_Pre_test.class, R.drawable.vb_img_2, vb_imgs, vb_cans)
                    });
            newMenuModule.put("Partograph",
                    new SubMenu[]{
                            new SubMenu("Partograph", PartoTest.class, R.drawable.parto1001, parto_imgs, parto_cans)
                    });
            newMenuModule.put("Pre Eclampsia & Eclampsia",
                    new SubMenu[]{
                            new SubMenu("Pre Eclampsia & Eclampsia", EclamTest.class, R.drawable.eclam1001, eclam_imgs, eclam_cans)
                    });
            newMenuModule.put("Postpartum Hemorrhage - 1",
                    new SubMenu[]{
                            new SubMenu("Postpartum Hemorrhage - 1", HemoTest01.class, R.drawable.hemo1001, hemo1_imgs, hemotest01_cans)
                    });
            newMenuModule.put("Postpartum Hemorrhage - 2",
                    new SubMenu[]{
                            new SubMenu("Postpartum Hemorrhage - 2", HemoTest02.class, R.drawable.hemo2001, hemo2_imgs, hemotest02_cans)
                    });
            newMenuModule.put("Assessment and management of Shock",
                    new SubMenu[]{
                            new SubMenu("Assessment and management of Shock", ShockTest.class, R.drawable.shock1001, shock_imgs, shocktest_cans)
                    });
            newMenuModule.put("Puerperal Sepsis",
                    new SubMenu[]{
                            new SubMenu("Puerperal Sepsis", PerpuralSepsisTest.class, R.drawable.sepsis1001, sepsis_imgs, sepsistest_cans)
                    });

        } else if (type == 2) {
            newMenuModule.put("Essential Care for Every Baby",
                    new SubMenu[]{
                            new SubMenu("ECEB(Session One)", EcEbTest01.class, R.drawable.eceb1001, eceb1_imgs, eceb01_cans),
                            new SubMenu("ECEB(Session Two)", EcEbTest02.class, R.drawable.eceb2001, eceb2_imgs, eceb02_cans)
                    }
            );
            newMenuModule.put("Essential Care for Small Babies",
                    new SubMenu[]{
                            new SubMenu("ECSB(Session One)", EcSbTest01.class, R.drawable.ecsb1001, ecsb1_imgs, ecsbA_cans),
                            new SubMenu("ECSB(Session Two)", EcSbTest02.class, R.drawable.ecsb2001, ecsb2_imgs, ecsbB_cans)
                    }
            );
            newMenuModule.put("Helping Babies Breathe",
                    new SubMenu[]{
                            new SubMenu("Helping Babies Breathe)", HbbTest.class, R.drawable.hbb1001, hbb_imgs, hbb_cans)
                    }
            );
        }

    }

    public static class SubMenu {
        String name;
        Class routeClass;
        int[] session;
        int preImage;
        ArrayList<String> answers;

        public SubMenu(String name, Class routeClass, int preImage, int[] session, ArrayList<String> answers) {
            this.name = name;
            this.routeClass = routeClass;
            this.preImage = preImage;
            this.session = session;
            this.answers = answers;
        }

        public String getName() {
            return name;
        }

        public Class getRouteClass() {
            return routeClass;
        }

        public int[] getSession() {
            return session;
        }

        public int getPreImage() {
            return preImage;
        }

        public ArrayList<String> getAnswers() {
            return answers;
        }
    }

}
