package detail.acad.hassannaqvi.edu.aku.academicdetailing.core


import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.*


object CreateTable {
    const val SQL_CREATE_USERS = ("CREATE TABLE "
            + UsersTable.TABLE_NAME + "("
            + UsersTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.COLUMN_USERNAME + " TEXT,"
            + UsersTable.COLUMN_PASSWORD + " TEXT,"
            + UsersTable.COLUMN_FULLNAME + " TEXT,"
            + UsersTable.COLUMN_DIST_ID + " TEXT,"
            + UsersTable.COLUMN_ENABLED + " TEXT,"
            + UsersTable.COLUMN_ISNEW_USER + " TEXT,"
            + UsersTable.COLUMN_PWD_EXPIRY + " TEXT,"
            + UsersTable.COLUMN_DESIGNATION + " TEXT"
            + " );"
            )

    const val SQL_CREATE_FORMS = ("CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FormsTable.COLUMN_PROJECTNAME + " TEXT," +
            FormsTable.COLUMN_loggin_TIME + " TEXT, " +
            FormsTable.COLUMN_SURVEYTYPE + " TEXT," +
            FormsTable.COLUMN_MODULE_CODE + " TEXT, " +
            FormsTable.COLUMN_SESSION_CODE + " TEXT, " +
            FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_ISTATUS88X + " TEXT," +
            FormsTable.COLUMN_ENDINGDATETIME + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDT + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_GPSELEV + " TEXT," +
            FormsTable.COLUMN_GPSTIME + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT," +
            FormsTable.COLUMN_APPVERSION + " TEXT," +
            FormsTable.COLUMN_DIST_ID + " TEXT," +
            FormsTable.COLUMN_HFACILITY_NAME + " TEXT," +
            FormsTable.COLUMN_PROVIDER_NAME + " TEXT," +
            FormsTable.COLUMN_PROVIDER_ID + " TEXT ," +
            FormsTable.COLUMN_PRETEST_START_TIME + " TEXT ," +
            FormsTable.COLUMN_PRETEST_END_TIME + " TEXT ," +
            FormsTable.COLUMN_POSTTEST_START_TIME + " TEXT ," +
            FormsTable.COLUMN_POSTTEST_END_TIME + " TEXT ," +
            FormsTable.COLUMN_SESSION_START_TIME + " TEXT ," +
            FormsTable.COLUMN_SESSION_END_TIME + " TEXT, " +
            FormsTable.COLUMN_PRE_TEST + " TEXT ," +
            FormsTable.COLUMN_POST_TEST + " TEXT," +
            FormsTable.COLUMN_TOTAL + " TEXT," +
            FormsTable.COLUMN_SCORE_PRE + " TEXT," +
            FormsTable.COLUMN_PER_PRE + " TEXT," +
            FormsTable.COLUMN_WRONG_PRE + " TEXT, " +
            FormsTable.COLUMN_SCORE_POST + " TEXT," +
            FormsTable.COLUMN_PER_POST + " TEXT, " +
            FormsTable.COLUMN_WRONG_POST + " TEXT " +
            " ); ")

    const val SQL_CREATE_SESSION_TABLE = (" CREATE TABLE " + SessionTable.TABLE_NAME
            + " ( " + SessionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SessionTable.COLUMN_FORMDATE + " TEXT, "
            + SessionTable.COLUMN_DEVICEID + " TEXT, "
            + SessionTable.COLUMN_USER + " TEXT, "
            + SessionTable.COLUMN_UID + " TEXT, "
            + SessionTable.COLUMN_DEVICETAGID + " TEXT, "
            + SessionTable.COLUMN_UUID + " TEXT, "
            + SessionTable.COLUMN_SLIDE_NUMBER + " INTEGER,"
            + SessionTable.COLUMN_MODULE_CODE + " TEXT,"
            + SessionTable.COLUMN_SESSION_CODE + " TEXT,"
            + SessionTable.COLUMN_SESSION_TIME + " TEXT,"
            + SessionTable.COLUMN_SYNCED + " TEXT,"
            + SessionTable.COLUMN_SYNCED_DATE + " TEXT" + ");")

    const val SQL_CREATE_DISTRICT_TABLE = (" CREATE TABLE " + DistrictTable.TABLE_NAME
            + " ( " + DistrictTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DistrictTable.COLUMN_DIST_ID + " Long," +
            DistrictTable.DISTRICT_NAME + " TEXT" + ");")

    const val SQL_CREATE_TEHSIL_TABLE = (" CREATE TABLE " + TehsilTable.TABLE_NAME
            + " ( " + TehsilTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TehsilTable.TEHSIL_CODE + " Long," +
            TehsilTable.TEHSIL_NAME + " TEXT" + ");")

    const val SQL_CREATE_HF_TABLE = (" CREATE TABLE " + HealthFacilityTable.TABLE_NAME
            + " ( " + HealthFacilityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            //   + HealthFacilityTable.COLUMN_HF_DHIS + " LONG, "
            + HealthFacilityTable.COLUMN_HF_DIST_CODE + " LONG, "
            + HealthFacilityTable.COLUMN_HF_TEHSIL_NAME + " TEXT, "
            + HealthFacilityTable.COLUMN_HF_UC_NAME + " TEXT, "
            + HealthFacilityTable.COLUMN_HF_NAME + " TEXT, "
            // + HealthFacilityTable.COLUMN_HF_NAME_GOVT + " TEXT, "
            + HealthFacilityTable.COLUMN_HF_CODE + " LONG " + ");")

    const val SQL_CREATE_HP_TABLE = (" CREATE TABLE " + HealthProviderTable.TABLE_NAME
            + " ( " + HealthProviderTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + HealthProviderTable.COLUMN_HP_DIST_CODE + " LONG, "
            + HealthProviderTable.COLUMN_HP_TEHSIL + " TEXT, "
            //   + HealthProviderTable.COLUMN_HP_UC_NAME + " TEXT, "
            + HealthProviderTable.COLUMN_HP_UEN_CODE + " LONG, "
            + HealthProviderTable.COLUMN_HF_CODE + " LONG, "
            + HealthProviderTable.COLUMN_HP_NAME + " TEXT "
            // + HealthProviderTable.COLUMN_HP_DESIGNATION + " TEXT "
            + ");")
//    private static final String SQL_CREATE_TEHSIL = " CREATE TABLE " + TehsilTable.TABLE_NAME
//            + " ( " + TehsilTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + TehsilTable.TEHSIL_CODE + " LONG, "
//            + TehsilTable.TEHSIL_NAME + " TEXT, "
//            + TehsilTable.DIST_CODE + " LONG " + ");";
//

    //    private static final String SQL_CREATE_TEHSIL = " CREATE TABLE " + TehsilTable.TABLE_NAME
    //            + " ( " + TehsilTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
    //            + TehsilTable.TEHSIL_CODE + " LONG, "
    //            + TehsilTable.TEHSIL_NAME + " TEXT, "
    //            + TehsilTable.DIST_CODE + " LONG " + ");";
    //
    const val SQL_CREATE_NMS = ("CREATE TABLE " + NextMeetingTable.TABLE_NAME + "("
            + NextMeetingTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NextMeetingTable.COLUMN_DEVICEID + " TEXT, "
            + NextMeetingTable.COLUMN_USER + " TEXT, "
            + NextMeetingTable.COLUMN_UID + " TEXT, "
            + NextMeetingTable.COLUMN_DEVICETAGID + " TEXT, "
            + NextMeetingTable.COLUMN_LAT + " TEXT, "
            + NextMeetingTable.COLUMN_FORMDATE + " TEXT, "
            + NextMeetingTable.COLUMN_HF_NAME + " TEXT, "
            + NextMeetingTable.COLUMN_HP_NAME + " TEXT, "
            + NextMeetingTable.COLUMN_HP_CODE + " LONG, "
            + NextMeetingTable.COLUMN_DIST_CODE + " LONG, "
            + NextMeetingTable.COLUMN_LNG + " TEXT, "
            + NextMeetingTable.COLUMN_GPSTIME + " TEXT, "
            + NextMeetingTable.COLUMN_BTYPE + " TEXT, "
            + NextMeetingTable.COLUMN_BOOKBY + " TEXT, "
            + NextMeetingTable.COLUMN_DATE + " TEXT, "
            + NextMeetingTable.COLUMN_TIME + " TEXT, "
            + NextMeetingTable.COLUMN_MODULE_CODE + " TEXT, "
            + NextMeetingTable.COLUMN_SESSION_CODE + " TEXT, "
            + NextMeetingTable.COLUMN_SYNCED + " TEXT, "
            + NextMeetingTable.COLUMN_SYNCED_DATE + " TEXT " + ");")

    const val SQL_CREATE_ENTRYLOGS = ("CREATE TABLE "
            + EntryLogTable.TABLE_NAME + "("
            + EntryLogTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EntryLogTable.COLUMN_PROJECT_NAME + " TEXT,"
            + EntryLogTable.COLUMN_UID + " TEXT,"
            + EntryLogTable.COLUMN_UUID + " TEXT,"
            + EntryLogTable.COLUMN_HHID + " TEXT,"
            + EntryLogTable.COLUMN_USERNAME + " TEXT,"
            + EntryLogTable.COLUMN_SYSDATE + " TEXT,"
            + EntryLogTable.COLUMN_DEVICEID + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_DATE + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS + " TEXT,"
            + EntryLogTable.COLUMN_ISTATUS96x + " TEXT,"
            + EntryLogTable.COLUMN_ENTRY_TYPE + " TEXT,"
            + EntryLogTable.COLUMN_SYNCED + " TEXT,"
            + EntryLogTable.COLUMN_SYNC_DATE + " TEXT,"
            + EntryLogTable.COLUMN_APPVERSION + " TEXT"
            + " );"
            )

}