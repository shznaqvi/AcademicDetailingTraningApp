package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.provider.BaseColumns;

public class TableContracts {

    public static abstract class ListingsTable implements BaseColumns {
        public static final String TABLE_NAME = "Form";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_CLUSTER = "cluster";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_TAB_NO = "tabNo";
        public static final String COLUMN_GEOAREA = "geoArea";
        public static final String COLUMN_SA = "sA";
        public static final String COLUMN_SB = "sB";
        public static final String COLUMN_SC = "sC";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_END_TIME = "end_time";
        public static final String COLUMN_START_TIME = "start_time";
    }


    public static abstract class UsersTable implements BaseColumns {
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String TABLE_NAME = "AppUser";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "passwordEnc";
        public static final String COLUMN_FULLNAME = "full_name";
        public static final String COLUMN_DESIGNATION = "designation";
        public static final String COLUMN_ENABLED = "enabled";
        public static final String COLUMN_ISNEW_USER = "isNewUser";
        public static final String COLUMN_PWD_EXPIRY = "pwdExpiry";
        public static final String COLUMN_DIST_ID = "dist_id";

    }

    public static abstract class DistrictTable implements BaseColumns {

        public static final String TABLE_NAME = "district";
        public static final String _ID = "id";
        public static final String COLUMN_DIST_ID = "district_code";
        public static final String DISTRICT_NAME = "district_name";

    }

    public static abstract class TehsilTable implements BaseColumns {

        public static final String TABLE_NAME = "tehsils";
        public static final String _ID = "id";
        public static final String TEHSIL_CODE = "tehsil_code";
        public static final String TEHSIL_NAME = "tehsil_name";
        public static final String DIST_CODE = "COLUMN_DIST_ID";

    }
    public static abstract class VersionTable implements BaseColumns {
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String TABLE_NAME = "versionApp";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_VERSION_PATH = "elements";
        public static final String COLUMN_VERSION_CODE = "versionCode";
        public static final String COLUMN_VERSION_NAME = "versionName";
        public static final String COLUMN_PATH_NAME = "outputFile";
        public static final String SERVER_URI = "output-metadata.json";

    }

    public static abstract class EntryLogTable implements BaseColumns {
        public static final String TABLE_NAME = "EntryLog";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECT_NAME = "projectName";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_EB_CODE = "ebCode";
        public static final String COLUMN_HHID = "hhid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_SYSDATE = "sysdate";
        public static final String COLUMN_ENTRY_DATE = "entryDate";

        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNC_DATE = "sync_date";
        public static final String COLUMN_ENTRY_TYPE = "entry_type";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS96x = "istatus96x";
    }

    public static abstract class FormsTable implements BaseColumns {

        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN_SURVEYTYPE = "surveytype";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formDate";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88X = "istatus88x";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_GPSTIME = "gps_time";
        public static final String COLUMN_GPSELEV = "gpselev";
        public static final String COLUMN_DEVICEID = "deviceID";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_APPVERSION = "appversion";
        public static final String COLUMN_DIST_ID = "dictrict_id";
        public static final String COLUMN_PROVIDER_NAME = "provider_name";
        public static final String COLUMN_PROVIDER_ID = "provider_id";
        public static final String COLUMN_HFACILITY_NAME = "health_fac_name";
        public static final String COLUMN_PRETEST_START_TIME = "pre_test_start_time";
        public static final String COLUMN_PRETEST_END_TIME = "pre_test_end_time";
        public static final String COLUMN_POSTTEST_END_TIME = "post_test_end_time";
        public static final String COLUMN_POSTTEST_START_TIME = "post_test_start_time";
        public static final String COLUMN_SESSION_START_TIME = "session_start_time";
        public static final String COLUMN_SESSION_END_TIME = "session_end_time";
        public static final String COLUMN_loggin_TIME = "login_time";
        public static final String COLUMN_PRE_TEST = "pre_test";
        public static final String COLUMN_POST_TEST = "post_test";
        public static final String COLUMN_MODULE_CODE = "module_code";
        public static final String COLUMN_SESSION_CODE = "session_code";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_SCORE_PRE = "pre_correct";
        public static final String COLUMN_SCORE_POST = "post_correct";
        public static final String COLUMN_PER_PRE = "pre_percentage";
        public static final String COLUMN_PER_POST = "post_percentage";
        public static final String COLUMN_WRONG_PRE = "pre_wrong";
        public static final String COLUMN_WRONG_POST = "post_wrong";
        public static final String Form_Url = "forms.php";


    }
    public abstract class SessionTable implements BaseColumns{

        public static final String TABLE_NAME = "sessions_table";
        public static final String _ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_UUID = "_uuid";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_DEVICETAGID = "devicetagid";
        public static final String COLUMN_SESSION_CODE = "session_code";
        public static final String COLUMN_MODULE_CODE = "module_code";
        public static final String COLUMN_SESSION_TIME = "session_time";
        public static final String COLUMN_SLIDE_NUMBER = "slide_number";
        public static final String COLUMN_SYNCED= "synced";
        public static final String COLUMN_SYNCED_DATE= "synced_date";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String SESSION_URL = "sessions.php";

    }

    public static abstract class HealthProviderTable implements BaseColumns {

        public static final String TABLE_NAME = "healthcare_providers";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HP_NAME = "provider_name";
        public static final String COLUMN_HP_TEHSIL = "tehsil";
        // public static final String COLUMN_HP_UC_NAME = "uc";
        public static final String COLUMN_HP_UEN_CODE = "provider_code";
        public static final String COLUMN_HF_CODE = "hfcode";
        //public static final String COLUMN_HP_DESIGNATION = "hp_designation";
        public static final String COLUMN_HP_DIST_CODE = "dist_id";

    }

    public static abstract class NextMeetingTable implements BaseColumns {

        public static final String TABLE_NAME = "next_meeting";
        public static final String _ID = "_id";
        public static final String COLUMN_HF_NAME = "hf_name";
        public static final String COLUMN_HP_NAME = "hp_name";
        public static final String COLUMN_HP_CODE = "hp_code";
        public static final String COLUMN_DIST_CODE = "dist_id";
        public static final String COLUMN_DATE = "book_date";
        public static final String COLUMN_TIME = "book_time";
        public static final String COLUMN_MODULE_CODE = "module_code";
        public static final String COLUMN_SESSION_CODE = "session_code";
        public static final String COLUMN_LAT = "gpsLat";
        public static final String COLUMN_LNG = "gpsLng";
        public static final String COLUMN_BTYPE = "book_type";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_BOOKBY = "booking_by";
        public static final String COLUMN_GPSTIME = "gps_time";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_DEVICETAGID = "devicetagid";

        public static final String nms_Url = "next_meeting.php";
        public static final String app_Url = "appointments.php";

    }

    public static abstract class HealthFacilityTable implements BaseColumns {

        public static final String TABLE_NAME = "HealthFacility";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HF_NAME = "hf_name";
        //        public static final String COLUMN_HF_DISTRICT_NAME = "hf_district";
        public static final String COLUMN_HF_TEHSIL_NAME = "tehsil";
        public static final String COLUMN_HF_UC_NAME = "uc_name";
        public static final String COLUMN_HF_CODE = "hfcode";
        // public static final String COLUMN_HF_DHIS = "hf_dhis";
        //public static final String COLUMN_HF_NAME_GOVT = "hf_name_govt";
        public static final String COLUMN_HF_DIST_CODE = "dist_id";
    }


}
