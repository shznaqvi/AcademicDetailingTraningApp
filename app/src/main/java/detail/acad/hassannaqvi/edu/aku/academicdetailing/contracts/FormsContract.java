package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class FormsContract {

    private String projectName = "Academic Detailing Training";
    private String surveyType = "BL";
    private String _id = "";
    private String _UID = "";
    private String formdate = ""; // Date
    private String user = ""; // Interviewer 01
    private String istatus = ""; // Interview Status
    private String istatus88x = ""; // Interview Status
    private String sA = "";
    private String score_post = "";
    private String percentage_pre = "";
    private String score_pre = "";
    private String percentage_post = "";
    private String sInfo = "";
    private String sno = "";
    private String endingdatetime = "";
    private String gpsLat = "";
    private String gpsLng = "";
    private String gpsDT = "";
    private String gpsAcc = "";
    private String gpsElev = "";
    private String deviceid = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion;
    private String districtID = "";
    private String healthFacilityName = "";
    private String providerName = "";
    private String sessionStartTime = "";
    private String sessionEndTime = "";
    private String preTestStartTime = "";
    private String preTestEndTime = "";
    private String pre_test = "";
    private String module = "";
    private String gpsTime = "";
    private String session = "";
    private String wrong_pre = "";
    private String wrong_post = "";
    private String total = "";

    public String getWrong_pre() {
        return wrong_pre;
    }

    public void setWrong_pre(String wrong_pre) {
        this.wrong_pre = wrong_pre;
    }

    public String getWrong_post() {
        return wrong_post;
    }

    public void setWrong_post(String wrong_post) {
        this.wrong_post = wrong_post;
    }

    public String getScore_post() {
        return score_post;
    }

    public void setScore_post(String score_post) {
        this.score_post = score_post;
    }

    public String getPercentage_pre() {
        return percentage_pre;
    }

    public void setPercentage_pre(String percentage_pre) {
        this.percentage_pre = percentage_pre;
    }

    public String getScore_pre() {
        return score_pre;
    }

    public void setScore_pre(String score_pre) {
        this.score_pre = score_pre;
    }

    public String getPercentage_post() {
        return percentage_post;
    }

    public void setPercentage_post(String percentage_post) {
        this.percentage_post = percentage_post;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


    public String getsInfo() {
        return sInfo;
    }

    public void setsInfo(String sInfo) {
        this.sInfo = sInfo;
    }




    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getPre_test() {
        return pre_test;
    }

    public void setPre_test(String pre_test) {
        this.pre_test = pre_test;
    }

    public String getPost_test() {
        return post_test;
    }

    public void setPost_test(String post_test) {
        this.post_test = post_test;
    }

    private String post_test = "";

    public String getLogginTime() {
        return logginTime;
    }

    public void setLogginTime(String logginTime) {
        this.logginTime = logginTime;
    }

    private String logginTime = "";

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(String sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public String getPreTestStartTime() {
        return preTestStartTime;
    }

    public void setPreTestStartTime(String preTestStartTime) {
        this.preTestStartTime = preTestStartTime;
    }

    public String getPreTestEndTime() {
        return preTestEndTime;
    }

    public void setPreTestEndTime(String preTestEndTime) {
        this.preTestEndTime = preTestEndTime;
    }

    public String getPostTestEndTime() {
        return postTestEndTime;
    }

    public void setPostTestEndTime(String postTestEndTime) {
        this.postTestEndTime = postTestEndTime;
    }

    public String getPostTestStartTime() {
        return postTestStartTime;
    }

    public void setPostTestStartTime(String postTestStartTime) {
        this.postTestStartTime = postTestStartTime;
    }

    private String postTestEndTime = "";
    private String postTestStartTime = "";

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getHealthFacilityName() {
        return healthFacilityName;
    }

    public void setHealthFacilityName(String healthFacilityName) {
        this.healthFacilityName = healthFacilityName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    private String providerID = "";


    public FormsContract() {
    }


    public String setAndGetSyncedDate() {
        Boolean syncstatus = true;
        return syncstatus.toString();
    }

    public FormsContract Sync(JSONObject jsonObject) throws JSONException {
        this.projectName = jsonObject.getString(FormsTable.COLUMN_PROJECTNAME);
        this.surveyType = jsonObject.getString(FormsTable.COLUMN_SURVEYTYPE);
        this._id = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formdate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.user = jsonObject.getString(FormsTable.COLUMN_USER);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(FormsTable.COLUMN_ISTATUS88X);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.gpsElev = jsonObject.getString(FormsTable.COLUMN_GPSELEV);
        this.deviceid = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APPVERSION);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.logginTime = jsonObject.getString(FormsTable.COLUMN_loggin_TIME);


        return this;

    }

    public FormsContract Hydrate(Cursor cursor) {

        this.projectName = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PROJECTNAME));
        this._id = cursor.getString(cursor.getColumnIndex(FormsTable._ID));
        this.module = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_MODULE));
        this.session = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SESSION));
        this.istatus = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ISTATUS88X));
        this.endingdatetime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSLNG));
        this.gpsAcc = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSACC));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_GPSTIME));
        this.deviceid = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DEVICEID));
        this.appversion = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_APPVERSION));
        this.districtID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_DIST_ID));
        this.healthFacilityName = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_HFACILITY_NAME));
        this.providerName = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PROVIDER_NAME));
        this.providerID = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PROVIDER_ID));
        this.preTestStartTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PRETEST_START_TIME));
        this.preTestEndTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PRETEST_END_TIME));
        this.postTestStartTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_POSTTEST_START_TIME));
        this.postTestEndTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_POSTTEST_END_TIME));
        this.sessionStartTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SESSION_START_TIME));
        this.sessionEndTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SESSION_END_TIME));
        this.pre_test = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PRE_TEST));
        this.post_test = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_POST_TEST));
        this.synced = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_SYNCED_DATE));
        this.logginTime = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_loggin_TIME));
        this.formdate = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_FORMDATE));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(FormsTable.COLUMN_MODULE, this.module == null ? JSONObject.NULL : this.module);
        json.put(FormsTable.COLUMN_SESSION, this.session == null ? JSONObject.NULL : this.session);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_ISTATUS88X, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_GPSTIME, this.gpsTime == null ? JSONObject.NULL : this.gpsTime);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
        json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(FormsTable.COLUMN_DIST_ID, this.districtID == null ? JSONObject.NULL : this.districtID);
        json.put(FormsTable.COLUMN_HFACILITY_NAME, this.healthFacilityName == null ? JSONObject.NULL : this.healthFacilityName);
        json.put(FormsTable.COLUMN_PROVIDER_NAME, this.providerName == null ? JSONObject.NULL : this.providerName);
        json.put(FormsTable.COLUMN_PROVIDER_ID, this.providerID == null ? JSONObject.NULL : this.providerID);
        json.put(FormsTable.COLUMN_PRETEST_START_TIME, this.preTestStartTime == null ? JSONObject.NULL : this.preTestStartTime);
        json.put(FormsTable.COLUMN_PRETEST_END_TIME, this.preTestEndTime == null ? JSONObject.NULL : this.preTestEndTime);
        json.put(FormsTable.COLUMN_POSTTEST_START_TIME, this.postTestStartTime == null ? JSONObject.NULL : this.postTestStartTime);
        json.put(FormsTable.COLUMN_POSTTEST_END_TIME, this.postTestEndTime == null ? JSONObject.NULL : this.postTestEndTime);
        json.put(FormsTable.COLUMN_SESSION_START_TIME, this.sessionStartTime == null ? JSONObject.NULL : this.sessionStartTime);
        json.put(FormsTable.COLUMN_SESSION_END_TIME, this.sessionEndTime == null ? JSONObject.NULL : this.sessionEndTime);

        try {

            if (!this.pre_test.equals("")) {
                json.put(FormsTable.COLUMN_PRE_TEST, this.pre_test.equals("") ? JSONObject.NULL : new JSONObject(this.pre_test));
            }

            if (!this.post_test.equals("")) {
                json.put(FormsTable.COLUMN_POST_TEST, this.post_test.equals("") ? JSONObject.NULL : new JSONObject(this.post_test));
            }
        } catch (Exception e) {
        }

        json.put(FormsTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(FormsTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(FormsTable.COLUMN_loggin_TIME, this.logginTime == null ? JSONObject.NULL : this.logginTime);
        json.put(FormsTable._ID, this._id == null ? JSONObject.NULL : this._id);
        json.put(FormsTable.COLUMN_FORMDATE, this.formdate == null ? JSONObject.NULL : this.formdate);
        return json;
    }

    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public String get_ID() {
        return _id;
    }

    public void set_ID(String _ID) {
        this._id = _ID;
    }

    public String getUID() {
        return _UID;
    }

    public void setUID(String _UID) {
        this._UID = _UID;
    }

    public String getFormDate() {
        return formdate;
    }

    public void setFormDate(String formDate) {
        this.formdate = formDate;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getIstatus88x() {
        return istatus88x;
    }

    public void setIstatus88x(String istatus88x) {
        this.istatus88x = istatus88x;
    }

    public String getsA() {
        return sA;
    }

    public void setsA(String sA) {
        this.sA = sA;
    }

    public String getGpsElev() {
        return gpsElev;
    }

    public void setGpsElev(String gpsElev) {
        this.gpsElev = gpsElev;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsDT() {
        return gpsDT;
    }

    public void setGpsDT(String gpsDT) {
        this.gpsDT = gpsDT;
    }

    public String getGpsAcc() {
        return gpsAcc;
    }

    public void setGpsAcc(String gpsAcc) {
        this.gpsAcc = gpsAcc;
    }

    public String getDeviceID() {
        return deviceid;
    }

    public void setDeviceID(String deviceID) {
        this.deviceid = deviceID;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public static abstract class FormsTable implements BaseColumns {

        public static final String TABLE_NAME = "forms";
        public static final String COLUMN_NAME_NULLABLE = "NULLHACK";
        public static final String COLUMN_PROJECTNAME = "projectname";
        public static final String COLUMN_SURVEYTYPE = "surveytype";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_ISTATUS = "istatus";
        public static final String COLUMN_ISTATUS88X = "istatus88x";
        public static final String COLUMN_ENDINGDATETIME = "endingdatetime";
        public static final String COLUMN_GPSLAT = "gpslat";
        public static final String COLUMN_GPSLNG = "gpslng";
        public static final String COLUMN_GPSDT = "gpsdt";
        public static final String COLUMN_GPSACC = "gpsacc";
        public static final String COLUMN_GPSTIME = "gps_time";
        public static final String COLUMN_GPSELEV = "gpselev";
        public static final String COLUMN_DEVICEID = "deviceid";
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
        public static final String COLUMN_MODULE = "module";
        public static final String COLUMN_SESSION = "session";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_SCORE_PRE = "correct_pre";
        public static final String COLUMN_SCORE_POST = "correct_post";
        public static final String COLUMN_PER_PRE = "percentage_pre";
        public static final String COLUMN_PER_POST = "percentage_post";
        public static final String COLUMN_WRONG_PRE = "wrong_pre";
        public static final String COLUMN_WRONG_POST = "wrong_post";

        public static final String Form_Url = "forms.php";



    }
}