package detail.acad.hassannaqvi.edu.aku.academicdetailing.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.CDBSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.DiaTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcEbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EcSbTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EclamTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.FANC_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.GDSSession01_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HbbTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HemoTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.HemoTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PartoTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PerpuralSepsisTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest01;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.PsbiTest02;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ShockTest;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.VB_Pre_test;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ViewPagerActivity;


public final class Data {
    public static final String[] modules = new String[]{"Child Health", "Maternal Health", "New Born Health"};
    public static final String[] modulesCode = new String[]{"0", "1", "2", "3"};
    public static final String[] childModule = new String[]{"General Danger Sign", "Cough & Difficult Breathing", "Diarrhoea", "PSBI"};
    public static final String[] GDS = new String[]{"General Danger Sign"};
    public static final HashMap<String, String> GDSMap = new HashMap<String, String>() {{
        put("General Danger Sign", "10101");
//        put("GDS(Management, Counseling and Referral)", "10102");
    }};
    public static final String[] CDB = new String[]{"Cough & Difficult Breathing"};
    public static final HashMap<String, String> CDBMap = new HashMap<String, String>() {{
        put("Cough & Difficult Breathing", "10201");
//        put("CDB(Management, Counseling and Referral)", "10202");
    }};
    public static final String[] Diarrhea = new String[]{"Dia(Assessment and Classification)", "Dia(Management, Counseling and Referral)"};
    public static final HashMap<String, String> DiaMap = new HashMap<String, String>() {{
        put("Dia(Assessment and Classification)", "10301");
        put("Dia(Management, Counseling and Referral)", "10302");
    }};
    public static final String[] PSBI = new String[]{"PSBI(Assessment and Classification)", "PSBI(Management, Counseling and Referral)", "PSBI(Breast Feeding & Immunization)"};
    public static final String[] PSBI_Code = new String[]{"10401", "10402", "10403"};
    public static final HashMap<String, String> PSBIMap = new HashMap<String, String>() {{
        put("PSBI(Assessment and Classification)", "10401");
        put("PSBI(Management, Counseling and Referral)", "10402");
        put("PSBI(Breast Feeding & Immunization)", "10403");
    }};
    public static final String[] maternalModule = new String[]{"Focused Antenatal Care", "Vaginal Bleeding in Pregnancy",
            "Partograph", "Pre Eclampsia & Eclampsia", "Postpartum Hemorrhage - 1", "Postpartum Hemorrhage - 2", "Assessment and management of Shock", "Puerperal Sepsis"};

    public static final HashMap<String, String> maternalMap = new HashMap<String, String>() {{
        put("Focused Antenatal Care", "20101");
        put("Vaginal Bleeding in Pregnancy", "20201");
        put("Partograph", "20301");
        put("Pre Eclampsia & Eclampsia", "20401");
        put("Postpartum Hemorrhage - 1", "20501");
        put("Postpartum Hemorrhage - 2", "20502");
        put("Assessment and management of Shock", "20601");
        put("Puerperal Sepsis", "20701");
    }};

    public static final HashMap<String, String> allSessionsMap = new HashMap<String, String>() {{
        put("10101", "General Danger Sign");
//        put("10102", "GDS(Management, Counseling and Referral)");
        put("10201", "Cough & Difficult Breathing");
//        put("10202", "CDB(Management, Counseling and Referral)");
        put("10301", "Dia(Assessment and Classification)");
        put("10302", "Dia(Management, Counseling and Referral)");
        put("10401", "PSBI(Assessment and Classification)");
        put("10402", "PSBI(Management, Counseling and Referral)");
        put("10403", "PSBI(Breast Feeding & Immunization)");
        put("20101", "Focused Antenatal Care");
        put("20201", "Vaginal Bleeding in Pregnancy");
        put("20301", "Partograph");
        put("20401", "Pre Eclampsia & Eclampsia");
        put("20501", "Postpartum Hemorrhage - 1");
        put("20502", "Postpartum Hemorrhage - 2");
        put("20601", "Assessment and management of Shock");
        put("20701", "Puerperal Sepsis");
        put("30101", "ECEB(Session One)");
        put("30102", "ECEB(Session Two)");
        put("30201", "ECSB(Session One)");
        put("30202", "ECSB(Session Two)");
        put("30301", "Helping Babies Breathe");
    }};

    public static final String[] newBornModule =
            new String[]{
                    "Essential Care for Every Baby",
                    "Essential Care for Small Babies",
                    "Helping Babies Breathe"
            };

    public static final String[] cdb_videos = {"cdb_1234_01", "cdb_1234_02", "cdb_1234_03", "cdb_1234_04"};
    public static final String[] dia_videos = {"dia_10301_01", "dia_10301_02", "dia_10301_03", "dia_10302_01"};
    public static final String[] gds_videos = {"gds_1234_01", "gds_1234_02"};
    public static final String[] psbi_videos = {"psbi_10401_01"};
    public static final String[] eceb_videos = {"eceb_30101_01", "eceb_30101_02", "eceb_30101_03", "eceb_30101_04",
            "eceb_30101_05", "eceb_30101_06", "eceb_30101_07", "eceb_30101_08", "eceb_30101_09", "eceb_30101_10", "eceb_30101_11"};

    public static final String[] childVideos = MainApp.mergeArrays(cdb_videos, dia_videos, gds_videos, psbi_videos);
    public static final String[] newBornVideos = MainApp.mergeArrays(eceb_videos);

    public static final String[] ECEB = new String[]{"ECEB(Session One)", "ECEB(Session Two)"};
    public static final HashMap<String, String> ECEBMap = new HashMap<String, String>() {{
        put("ECEB(Session One)", "30101");
        put("ECEB(Session Two)", "30102");

    }};
    public static final String[] ECSB = new String[]{"ECSB(Session One)", "ECSB(Session Two)"};
    public static final HashMap<String, String> ECSBMap = new HashMap<String, String>() {{
        put("ECSB(Session One)", "30201");
        put("ECSB(Session Two)", "30202");

    }};
    public static final String[] HBB = new String[]{"Helping Babies Breathe"};
    public static Map<String, SubMenu[]> newMenuModule;


    public static ArrayList<String> pretestAnswers = new ArrayList<>();
    public static ArrayList<String> checkboxPreAnswers = new ArrayList<>();
    public static ArrayList<String> checkboxPostAnswers = new ArrayList<>();
    public static ArrayList<String> posttestAnswers = new ArrayList<>();
    public static ArrayList<String> correctAnswers = new ArrayList<>();


    public static ArrayList<String> gdsa_cans = new ArrayList<String>(Arrays.asList("4", "1", "1", "3", "4"));
    public static ArrayList<String> gdsb_cans = new ArrayList<String>(Arrays.asList("2", "3", "2", "1", "3"));
    public static ArrayList<String> cdba_cans = new ArrayList<String>(Arrays.asList("4", "3", "2", "1"));
    public static ArrayList<String> cdbb_cans = new ArrayList<String>(Arrays.asList("3", "3", "2", "1", "4", "4"));
    public static ArrayList<String> diaa_cans = new ArrayList<String>(Arrays.asList("1", "2", "4", "2", "4"));
    public static ArrayList<String> diab_cans = new ArrayList<String>(Arrays.asList("1", "1", "1", "2", "2"));
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
            R.drawable.cdb, R.drawable.eclampsia, R.drawable.fanc, R.drawable.gds, R.drawable.graph,
            R.drawable.newborn, R.drawable.shock, R.drawable.sick,
    };

    public static int[] gds1_imgs = new int[]{
            R.drawable.gds02, R.drawable.gds03, R.drawable.gds04, R.drawable.gds05, R.drawable.gds06,
            R.drawable.gds07, R.drawable.gds08, R.drawable.gds09, R.drawable.gds10, R.drawable.gds11,
            R.drawable.gds12, R.drawable.gds13, R.drawable.gds14, R.drawable.gds15, R.drawable.gds16,
            R.drawable.gds17, R.drawable.gds18, R.drawable.gds19, R.drawable.gds20, R.drawable.gds21, R.drawable.gds22,
            R.drawable.gds23, R.drawable.gds24, R.drawable.gds25, R.drawable.gds26, R.drawable.gds27, R.drawable.gds28,
            R.drawable.gds29, R.drawable.gds30,
            R.drawable.gds31,
    };

//    public static int[] gds2_imgs = new int[]{
//            R.drawable.gds2002, R.drawable.gds2003, R.drawable.gds2004, R.drawable.gds2005, R.drawable.gds2006,
//            R.drawable.gds2007, R.drawable.gds2008, R.drawable.gds2009, R.drawable.gds2010, R.drawable.gds2011,
//            R.drawable.gds2012, R.drawable.gds2013, R.drawable.gds2014, R.drawable.gds2015, R.drawable.gds2016,
//            R.drawable.gds2017, R.drawable.gds2018, R.drawable.gds2019, R.drawable.gds2020,};

    public static int[] cdb1_imgs = new int[]{
            R.drawable.cdbs02, R.drawable.cdbs03, R.drawable.cdbs04, R.drawable.cdbs05,
            R.drawable.cdbs06, R.drawable.cdbs07, R.drawable.cdbs08, R.drawable.cdbs09, R.drawable.cdbs10,
            R.drawable.cdbs11, R.drawable.cdbs12, R.drawable.cdbs13, R.drawable.cdbs14, R.drawable.cdbs15,
            R.drawable.cdbs16, R.drawable.cdbs17, R.drawable.cdbs18, R.drawable.cdbs19, R.drawable.cdbs20,
            R.drawable.cdbs21, R.drawable.cdbs22, R.drawable.cdbs23, R.drawable.cdbs24, R.drawable.cdbs25,
            R.drawable.cdbs26, R.drawable.cdbs27, R.drawable.cdbs28, R.drawable.cdbs29, R.drawable.cdbs30, R.drawable.cdbs31
            , R.drawable.cdbs32, R.drawable.cdbs33, R.drawable.cdbs34, R.drawable.cdbs35, R.drawable.cdbs36,
            R.drawable.cdbs37, R.drawable.cdbs38,
            R.drawable.cdbs39};

    public static int[] dia1_imgs = new int[]{
            R.drawable.ds0102, R.drawable.ds0103, R.drawable.ds0104, R.drawable.ds0105, R.drawable.ds0106,
            R.drawable.ds0107, R.drawable.ds0108, R.drawable.ds0109, R.drawable.ds0110, R.drawable.ds0111, R.drawable.ds0112,
            R.drawable.ds0113, R.drawable.ds0114, R.drawable.ds0115, R.drawable.ds0116, R.drawable.ds0117, R.drawable.ds0118,
            R.drawable.ds0119, R.drawable.ds0120, R.drawable.ds0121, R.drawable.ds0122, R.drawable.ds0123, R.drawable.ds0124, R.drawable.ds0125,
            R.drawable.ds0126, R.drawable.ds0127};

    public static int[] dia2_imgs = new int[]{
            R.drawable.ds0202, R.drawable.ds0203, R.drawable.ds0204, R.drawable.ds0205, R.drawable.ds0206, R.drawable.ds0207,
            R.drawable.ds0208, R.drawable.ds0209, R.drawable.ds0210, R.drawable.ds0211, R.drawable.ds0212, R.drawable.ds0213,
            R.drawable.ds0214, R.drawable.ds0215, R.drawable.ds0216, R.drawable.ds0217, R.drawable.ds0218, R.drawable.ds0219,
            R.drawable.ds0220, R.drawable.ds0221, R.drawable.ds0222, R.drawable.ds0223, R.drawable.ds0224, R.drawable.ds0225,
            R.drawable.ds0226, R.drawable.ds0227, R.drawable.ds0228, R.drawable.ds0229, R.drawable.ds0230, R.drawable.ds0231,
            R.drawable.ds0232, R.drawable.ds0233, R.drawable.ds0234
    };

    public static int[] psbi1_imgs = new int[]{
            R.drawable.psbi02, R.drawable.psbi03, R.drawable.psbi04, R.drawable.psbi05, R.drawable.psbi06,
            R.drawable.psbi07, R.drawable.psbi08, R.drawable.psbi09, R.drawable.psbi10, R.drawable.psbi11
            , R.drawable.psbi12, R.drawable.psbi13, R.drawable.psbi14, R.drawable.psbi15, R.drawable.psbi16, R.drawable.psbi17
            , R.drawable.psbi18, R.drawable.psbi19, R.drawable.psbi20, R.drawable.psbi21, R.drawable.psbi22};

    public static int[] psbi2_imgs = new int[]{
            R.drawable.psbi0102, R.drawable.psbi0103, R.drawable.psbi0104, R.drawable.psbi0105, R.drawable.psbi0106
            , R.drawable.psbi0107, R.drawable.psbi0108, R.drawable.psbi0109, R.drawable.psbi0110, R.drawable.psbi0111,
            R.drawable.psbi0112, R.drawable.psbi0113, R.drawable.psbi0114, R.drawable.psbi0115, R.drawable.psbi0116,
            R.drawable.psbi0117, R.drawable.psbi0118, R.drawable.psbi0119, R.drawable.psbi0120, R.drawable.psbi0121, R.drawable.psbi0122,
            R.drawable.psbi0123, R.drawable.psbi0124, R.drawable.psbi0125, R.drawable.psbi0126
    };

    public static int[] psbi3_imgs = new int[]{
            R.drawable.psbi0302, R.drawable.psbi0303, R.drawable.psbi0304, R.drawable.psbi0305, R.drawable.psbi0306,
            R.drawable.psbi0307, R.drawable.psbi0308, R.drawable.psbi0309, R.drawable.psbi0310, R.drawable.psbi0311, R.drawable.psbi0312

    };


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
                            new SubMenu("childHealth", "1", "10101", "General Danger Sign", GDSSession01_Pre_test.class, R.drawable.gds01, gds1_imgs, gdsa_cans, gds_videos),
//                            new SubMenu("childHealth", "1", "10102", "GDS(Management, Counseling and Referral)", GDSSession02_Pre_test.class, R.drawable.gds2001, gds2_imgs, gdsb_cans, gds_videos)
                    }
            );
            newMenuModule.put("Cough & Difficult Breathing",
                    new SubMenu[]{
                            new SubMenu("childHealth", "1", "10201", "Cough & Difficult Breathing", CDBSession01_Pre_test.class, R.drawable.cdbs01, cdb1_imgs, cdba_cans, cdb_videos),
//                            new SubMenu("childHealth", "1", "10202", "CDB(Management, Counseling and Referral)", CDBSession02_Pre_test.class, R.drawable.cdb2001, cdb2_imgs, cdbb_cans, cdb_videos)
                    }
            );
            newMenuModule.put("Diarrhoea",
                    new SubMenu[]{
                            new SubMenu("childHealth", "1", "10301", "Dia(Assessment and Classification)", DiaTest01.class, R.drawable.ds0101, dia1_imgs, diaa_cans, dia_videos),
                            new SubMenu("childHealth", "1", "10302", "Dia(Management, Counseling and Referral)", DiaTest02.class, R.drawable.ds0202, dia2_imgs, diab_cans, dia_videos)
                    }
            );
            newMenuModule.put("PSBI",
                    new SubMenu[]{
                            new SubMenu("childHealth", "1", "10401", "PSBI(Assessment and Classification)", PsbiTest01.class, R.drawable.psbi01, psbi1_imgs, psbia_cans, psbi_videos),
                            new SubMenu("childHealth", "1", "10402", "PSBI(Management, Counseling and Referral)", PsbiTest02.class, R.drawable.psbi0101, psbi2_imgs, psbib_cans, psbi_videos),
                            new SubMenu("childHealth", "1", "10403", "PSBI(Breast Feeding & Immunization)", ViewPagerActivity.class, R.drawable.psbi0301, psbi3_imgs, psbic_cans, psbi_videos)
                    }
            );
        } else if (type == 1) {
            newMenuModule.put("Focused Antenatal Care",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20101", "Focused Antenatal Care", FANC_Pre_test.class, R.drawable.fanc_02, fanc_imgs, fanc_cans, new String[]{})
                    });
            newMenuModule.put("Vaginal Bleeding in Pregnancy",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20201", "Vaginal Bleeding in Pregnancy", VB_Pre_test.class, R.drawable.vb_img_2, vb_imgs, vb_cans, new String[]{})
                    });
            newMenuModule.put("Partograph",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20301", "Partograph", PartoTest.class, R.drawable.parto1001, parto_imgs, parto_cans, new String[]{})
                    });
            newMenuModule.put("Pre Eclampsia & Eclampsia",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20401", "Pre Eclampsia & Eclampsia", EclamTest.class, R.drawable.eclam1001, eclam_imgs, eclam_cans, new String[]{})
                    });
            newMenuModule.put("Postpartum Hemorrhage - 1",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20501", "Postpartum Hemorrhage - 1", HemoTest01.class, R.drawable.hemo1001, hemo1_imgs, hemotest01_cans, new String[]{})
                    });
            newMenuModule.put("Postpartum Hemorrhage - 2",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20502", "Postpartum Hemorrhage - 2", HemoTest02.class, R.drawable.hemo2001, hemo2_imgs, hemotest02_cans, new String[]{})
                    });
            newMenuModule.put("Assessment and management of Shock",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20601", "Assessment and management of Shock", ShockTest.class, R.drawable.shock1001, shock_imgs, shocktest_cans, new String[]{})
                    });
            newMenuModule.put("Puerperal Sepsis",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20701", "Puerperal Sepsis", PerpuralSepsisTest.class, R.drawable.sepsis1001, sepsis_imgs, sepsistest_cans, new String[]{})
                    });

        } else if (type == 2) {
            newMenuModule.put("Essential Care for Every Baby",
                    new SubMenu[]{
                            new SubMenu("nBornHealth", "3", "30101", "ECEB(Session One)", EcEbTest01.class, R.drawable.eceb1001, eceb1_imgs, eceb01_cans, eceb_videos),
                            new SubMenu("nBornHealth", "3", "30102", "ECEB(Session Two)", EcEbTest02.class, R.drawable.eceb2001, eceb2_imgs, eceb02_cans, eceb_videos)
                    }
            );
            newMenuModule.put("Essential Care for Small Babies",
                    new SubMenu[]{
                            new SubMenu("nBornHealth", "3", "30201", "ECSB(Session One)", EcSbTest01.class, R.drawable.ecsb1001, ecsb1_imgs, ecsbA_cans, eceb_videos),
                            new SubMenu("nBornHealth", "3", "30202", "ECSB(Session Two)", EcSbTest02.class, R.drawable.ecsb2001, ecsb2_imgs, ecsbB_cans, eceb_videos)
                    }
            );
            newMenuModule.put("Helping Babies Breathe",
                    new SubMenu[]{
                            new SubMenu("nBornHealth", "3", "30301", "Helping Babies Breathe)", HbbTest.class, R.drawable.hbb1001, hbb_imgs, hbb_cans, new String[]{})
                    }
            );
        }

    }

    public static class SubMenu implements Serializable {
        String moduleName;
        String moduleCode;
        String sessionCode;
        String name;
        Class routeClass;

        public SubMenu(String moduleName, String moduleCode, String sessionCode, String name, Class routeClass, int preImage, int[] session, ArrayList<String> answers, String[] videosName) {
            this.moduleName = moduleName;
            this.name = name;
            this.moduleCode = moduleCode;
            this.sessionCode = sessionCode;
            this.routeClass = routeClass;
            this.preImage = preImage;
            this.session = session;
            this.answers = answers;
            this.videosName = videosName;
        }

        public String getModuleCode() {
            return moduleCode;
        }

        int[] session;
        int preImage;
        ArrayList<String> answers;
        String[] videosName;

        public String getSessionCode() {
            return sessionCode;
        }

        public String getModuleName() {
            return moduleName;
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

        public String[] getVideosName() {
            return videosName;
        }


    }

}
