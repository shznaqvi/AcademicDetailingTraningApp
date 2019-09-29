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
            "Partograph", "Pre Eclampsia & Eclampsia", "Postpartum Hemorrhage", "Assessment and management of Shock", "Puerperal Sepsis"};

    public static final HashMap<String, String> maternalMap = new HashMap<String, String>() {{
        put("Focused Antenatal Care", "20101");
        put("Vaginal Bleeding in Pregnancy", "20201");
        put("Partograph", "20301");
        put("Pre Eclampsia & Eclampsia", "20401");
        put("Postpartum Hemorrhage", "20501");
//        put("Postpartum Hemorrhage - 2", "20502");
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
        put("20501", "Postpartum Hemorrhage");
//        put("20502", "Postpartum Hemorrhage - 2");
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
            R.drawable.fanc02, R.drawable.fanc03, R.drawable.fanc04, R.drawable.fanc05, R.drawable.fanc06, R.drawable.fanc07,
            R.drawable.fanc08, R.drawable.fanc09, R.drawable.fanc10, R.drawable.fanc11, R.drawable.fanc12, R.drawable.fanc13,
            R.drawable.fanc14, R.drawable.fanc15, R.drawable.fanc16, R.drawable.fanc17, R.drawable.fanc18, R.drawable.fanc19,
            R.drawable.fanc20, R.drawable.fanc21, R.drawable.fanc22, R.drawable.fanc23};

    public static int[] vb_imgs = new int[]{
            R.drawable.vbdp02, R.drawable.vbdp04, R.drawable.vbdp05, R.drawable.vbdp06, R.drawable.vbdp07,
            R.drawable.vbdp08, R.drawable.vbdp09, R.drawable.vbdp10, R.drawable.vbdp11, R.drawable.vbdp12, R.drawable.vbdp13,
    };

    public static int[] parto_imgs = new int[]{
            R.drawable.part02, R.drawable.part03, R.drawable.part04, R.drawable.part05, R.drawable.part06,
            R.drawable.part07, R.drawable.part08, R.drawable.part09, R.drawable.part10, R.drawable.part11, R.drawable.part12,
            R.drawable.part13, R.drawable.part14, R.drawable.part15, R.drawable.part16, R.drawable.part17, R.drawable.part18,
            R.drawable.part19, R.drawable.part20, R.drawable.part21, R.drawable.part22, R.drawable.part23, R.drawable.part24,
            R.drawable.part25, R.drawable.part26, R.drawable.part27, R.drawable.part28, R.drawable.part29, R.drawable.part30,
            R.drawable.part31, R.drawable.part32, R.drawable.part33
    };


    public static int[] eclam_imgs = new int[]{
            R.drawable.preecl02, R.drawable.ationplan01, R.drawable.actionplan02, R.drawable.preecl03, R.drawable.preecl04
            , R.drawable.preecl05, R.drawable.preecl06, R.drawable.preecl07, R.drawable.preecl08, R.drawable.preecl09, R.drawable.preecl10,
            R.drawable.preecl11, R.drawable.preecl12, R.drawable.preecl13, R.drawable.preecl14, R.drawable.preecl15, R.drawable.preecl16,
            R.drawable.preecl17, R.drawable.preecl18, R.drawable.preecl19, R.drawable.preecl20, R.drawable.preecl21, R.drawable.preecl22,
            R.drawable.preecl23, R.drawable.preecl24, R.drawable.preecl25
    };


    public static int[] hemo1_imgs = new int[]{
            R.drawable.cdpph02, R.drawable.cdpph03, R.drawable.cdpph04, R.drawable.cdpph05, R.drawable.cdpph06, R.drawable.cdpph07,
            R.drawable.cdpph08, R.drawable.cdpph09, R.drawable.cdpph10, R.drawable.cdpph11, R.drawable.cdpph12, R.drawable.cdpph13,
            R.drawable.cdpph14, R.drawable.cdpph15, R.drawable.cdpph16, R.drawable.cdpph17};


//    public static int[] hemo2_imgs = new int[]{
//            R.drawable.hemo2002, R.drawable.hemo2003, R.drawable.hemo2004, R.drawable.hemo2005, R.drawable.hemo2006,
//            R.drawable.hemo2007, R.drawable.hemo2008, R.drawable.hemo2009, R.drawable.hemo2010, R.drawable.hemo2011,
//            R.drawable.hemo2012, R.drawable.hemo2013, R.drawable.hemo2014, R.drawable.hemo2015, R.drawable.hemo2016,
//            R.drawable.hemo2017, R.drawable.hemo2018, R.drawable.hemo2019, R.drawable.hemo2020,};


    public static int[] shock_imgs = new int[]{
            R.drawable.sh02, R.drawable.sh03, R.drawable.sh04, R.drawable.sh05, R.drawable.sh06, R.drawable.sh07, R.drawable.sh08,
            R.drawable.sh09, R.drawable.sh10, R.drawable.sh11, R.drawable.sh12, R.drawable.sh13};


    public static int[] sepsis_imgs = new int[]{
            R.drawable.ps02, R.drawable.ps03, R.drawable.ps04, R.drawable.ps05, R.drawable.ps06, R.drawable.ps07, R.drawable.ps08,
            R.drawable.ps09, R.drawable.ps10, R.drawable.ps11, R.drawable.ps12, R.drawable.ps13, R.drawable.ps14, R.drawable.ps15,
            R.drawable.ps15, R.drawable.ps16, R.drawable.ps17, R.drawable.ps18, R.drawable.ps19, R.drawable.ps20, R.drawable.ps21,
            R.drawable.ps22, R.drawable.ps23, R.drawable.ps24, R.drawable.ps25, R.drawable.ps26, R.drawable.ps27, R.drawable.ps28};


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
                            new SubMenu("maternalHealth", "2", "20101", "Focused Antenatal Care", FANC_Pre_test.class, R.drawable.fanc01, fanc_imgs, fanc_cans, new String[]{})
                    });
            newMenuModule.put("Vaginal Bleeding in Pregnancy",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20201", "Vaginal Bleeding in Pregnancy", VB_Pre_test.class, R.drawable.vbdp01, vb_imgs, vb_cans, new String[]{})
                    });
            newMenuModule.put("Partograph",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20301", "Partograph", PartoTest.class, R.drawable.part01, parto_imgs, parto_cans, new String[]{})
                    });
            newMenuModule.put("Pre Eclampsia & Eclampsia",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20401", "Pre Eclampsia & Eclampsia", EclamTest.class, R.drawable.preecl01, eclam_imgs, eclam_cans, new String[]{})
                    });
            newMenuModule.put("Postpartum Hemorrhage",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20501", "Postpartum Hemorrhage", HemoTest01.class, R.drawable.cdpph01, hemo1_imgs, hemotest01_cans, new String[]{})
                    });
//            newMenuModule.put("Postpartum Hemorrhage - 2",
//                    new SubMenu[]{
//                            new SubMenu("maternalHealth", "2", "20502", "Postpartum Hemorrhage - 2", HemoTest02.class, R.drawable.hemo2001, hemo2_imgs, hemotest02_cans, new String[]{})
//                    });
            newMenuModule.put("Assessment and management of Shock",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20601", "Assessment and management of Shock", ShockTest.class, R.drawable.sh01, shock_imgs, shocktest_cans, new String[]{})
                    });
            newMenuModule.put("Puerperal Sepsis",
                    new SubMenu[]{
                            new SubMenu("maternalHealth", "2", "20701", "Puerperal Sepsis", PerpuralSepsisTest.class, R.drawable.ps01, sepsis_imgs, sepsistest_cans, new String[]{})
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
