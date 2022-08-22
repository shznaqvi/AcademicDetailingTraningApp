package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.FormsTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Forms implements Parcelable {

    private String projectName = "Academic Detailing Training";
    private String surveyType = "BL";
    private String _id = "";
    private String _UID = "";
    private String formDate = ""; // Date
    private String username = ""; // Interviewer 01
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
    private String deviceID = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";
    private String appversion;
    private String districtID = "";
    private String districtName = "";
    private String healthFacilityCode = "";
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
    private String sessionCode = "";
    private String moduleCode = "";

    protected Forms(Parcel in) {
        projectName = in.readString();
        surveyType = in.readString();
        _id = in.readString();
        _UID = in.readString();
        formDate = in.readString();
        username = in.readString();
        istatus = in.readString();
        istatus88x = in.readString();
        sA = in.readString();
        score_post = in.readString();
        percentage_pre = in.readString();
        score_pre = in.readString();
        percentage_post = in.readString();
        sInfo = in.readString();
        sno = in.readString();
        endingdatetime = in.readString();
        gpsLat = in.readString();
        gpsLng = in.readString();
        gpsDT = in.readString();
        gpsAcc = in.readString();
        gpsElev = in.readString();
        deviceID = in.readString();
        devicetagID = in.readString();
        synced = in.readString();
        synced_date = in.readString();
        appversion = in.readString();
        districtID = in.readString();
        healthFacilityCode = in.readString();
        providerName = in.readString();
        sessionStartTime = in.readString();
        sessionEndTime = in.readString();
        preTestStartTime = in.readString();
        preTestEndTime = in.readString();
        pre_test = in.readString();
        module = in.readString();
        gpsTime = in.readString();
        session = in.readString();
        wrong_pre = in.readString();
        wrong_post = in.readString();
        total = in.readString();
        post_test = in.readString();
        logginTime = in.readString();
        postTestEndTime = in.readString();
        postTestStartTime = in.readString();
        providerID = in.readString();
        sessionCode = in.readString();
        moduleCode = in.readString();
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public static final Creator<Forms> CREATOR = new Creator<Forms>() {
        @Override
        public Forms createFromParcel(Parcel in) {
            return new Forms(in);
        }

        @Override
        public Forms[] newArray(int size) {
            return new Forms[size];
        }
    };

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }


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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }


    public String getHealthFacilityName() {
        return healthFacilityName;
    }

    public void setHealthFacilityName(String healthFacilityName) {
        this.healthFacilityName = healthFacilityName;
    }

    public String getHealthFacilityCode() {
        return healthFacilityCode;
    }

    public void setHealthFacilityCode(String healthFacilityCode) {
        this.healthFacilityCode = healthFacilityCode;
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


    public Forms() {
    }



    public String setAndGetSyncedDate() {
        Boolean syncstatus = true;
        return syncstatus.toString();
    }

    public Forms Sync(JSONObject jsonObject) throws JSONException {
        this.projectName = jsonObject.getString(FormsTable.COLUMN_PROJECTNAME);
        this.surveyType = jsonObject.getString(FormsTable.COLUMN_SURVEYTYPE);
        this._id = jsonObject.getString(FormsTable.COLUMN_ID);
        this._UID = jsonObject.getString(FormsTable.COLUMN_UID);
        this.formDate = jsonObject.getString(FormsTable.COLUMN_FORMDATE);
        this.username = jsonObject.getString(FormsTable.COLUMN_USER);
        this.istatus = jsonObject.getString(FormsTable.COLUMN_ISTATUS);
        this.istatus88x = jsonObject.getString(FormsTable.COLUMN_ISTATUS88X);
        this.endingdatetime = jsonObject.getString(FormsTable.COLUMN_ENDINGDATETIME);
        this.gpsLat = jsonObject.getString(FormsTable.COLUMN_GPSLAT);
        this.gpsLng = jsonObject.getString(FormsTable.COLUMN_GPSLNG);
        this.gpsDT = jsonObject.getString(FormsTable.COLUMN_GPSDT);
        this.gpsAcc = jsonObject.getString(FormsTable.COLUMN_GPSACC);
        this.gpsElev = jsonObject.getString(FormsTable.COLUMN_GPSELEV);
        this.deviceID = jsonObject.getString(FormsTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(FormsTable.COLUMN_DEVICETAGID);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.appversion = jsonObject.getString(FormsTable.COLUMN_APPVERSION);
        this.synced = jsonObject.getString(FormsTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(FormsTable.COLUMN_SYNCED_DATE);
        this.logginTime = jsonObject.getString(FormsTable.COLUMN_loggin_TIME);


        return this;

    }

    public Forms Hydrate(Cursor cursor) {

        this.projectName = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PROJECTNAME));
        this._id = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_ID));
        this._UID = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_UID));
        this.username = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_USER));
        this.devicetagID = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_DEVICETAGID));
        this.moduleCode = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_MODULE_CODE));
        this.sessionCode = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SESSION_CODE));
        this.istatus = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_ISTATUS));
        this.istatus88x = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_ISTATUS88X));
        this.endingdatetime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_ENDINGDATETIME));
        this.gpsLat = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_GPSLAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_GPSLNG));
        this.gpsAcc = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_GPSACC));
        this.gpsTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_GPSTIME));
        this.deviceID = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_DEVICEID));
        this.appversion = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_APPVERSION));
        this.districtID = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_DIST_ID));
        this.healthFacilityCode = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_HFACILITY_NAME));
        this.providerName = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PROVIDER_NAME));
        this.providerID = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PROVIDER_ID));
        this.preTestStartTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PRETEST_START_TIME));
        this.preTestEndTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PRETEST_END_TIME));
        this.postTestStartTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_POSTTEST_START_TIME));
        this.postTestEndTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_POSTTEST_END_TIME));
        this.sessionStartTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SESSION_START_TIME));
        this.sessionEndTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SESSION_END_TIME));
        this.pre_test = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PRE_TEST));
        this.post_test = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_POST_TEST));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SYNCED_DATE));
        this.logginTime = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_loggin_TIME));
        this.formDate = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_FORMDATE));
        this.total = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_TOTAL));
        this.score_pre = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SCORE_PRE));
        this.score_post = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_SCORE_POST));
        this.wrong_pre = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_WRONG_PRE));
        this.wrong_post = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_WRONG_POST));
        this.percentage_pre = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PER_PRE));
        this.percentage_post = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PER_POST));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(FormsTable.COLUMN_PROJECTNAME, this.projectName == null ? JSONObject.NULL : this.projectName);
        json.put(FormsTable.COLUMN_MODULE_CODE, this.moduleCode == null ? JSONObject.NULL : this.moduleCode);
        json.put(FormsTable.COLUMN_SESSION_CODE, this.sessionCode == null ? JSONObject.NULL : this.sessionCode);
        json.put(FormsTable.COLUMN_ISTATUS, this.istatus == null ? JSONObject.NULL : this.istatus);
        json.put(FormsTable.COLUMN_ISTATUS88X, this.istatus88x == null ? JSONObject.NULL : this.istatus88x);
        json.put(FormsTable.COLUMN_ENDINGDATETIME, this.endingdatetime == null ? JSONObject.NULL : this.endingdatetime);
        json.put(FormsTable.COLUMN_GPSLAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(FormsTable.COLUMN_GPSLNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(FormsTable.COLUMN_GPSACC, this.gpsAcc == null ? JSONObject.NULL : this.gpsAcc);
        json.put(FormsTable.COLUMN_GPSTIME, this.gpsTime == null ? JSONObject.NULL : this.gpsTime);
        json.put(FormsTable.COLUMN_DEVICEID, this.deviceID == null ? JSONObject.NULL : this.deviceID);
        json.put(FormsTable.COLUMN_APPVERSION, this.appversion == null ? JSONObject.NULL : this.appversion);
        json.put(FormsTable.COLUMN_DIST_ID, this.districtID == null ? JSONObject.NULL : this.districtID);
        json.put(FormsTable.COLUMN_HFACILITY_NAME, this.healthFacilityCode == null ? JSONObject.NULL : this.healthFacilityCode);
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
        json.put(FormsTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(FormsTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        json.put(FormsTable.COLUMN_USER, this.username == null ? JSONObject.NULL : this.username);
        json.put(FormsTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(FormsTable.COLUMN_TOTAL, this.total == null ? JSONObject.NULL : this.total);
        json.put(FormsTable.COLUMN_SCORE_PRE, this.score_pre == null ? JSONObject.NULL : this.score_pre);
        json.put(FormsTable.COLUMN_SCORE_POST, this.score_post == null ? JSONObject.NULL : this.score_post);
        json.put(FormsTable.COLUMN_WRONG_PRE, this.devicetagID == null ? JSONObject.NULL : this.wrong_pre);
        json.put(FormsTable.COLUMN_WRONG_POST, this.devicetagID == null ? JSONObject.NULL : this.wrong_post);
        json.put(FormsTable.COLUMN_PER_PRE, this.devicetagID == null ? JSONObject.NULL : this.percentage_pre);
        json.put(FormsTable.COLUMN_PER_POST, this.devicetagID == null ? JSONObject.NULL : this.percentage_post);
        return json;
    }

    public String getEndingdatetime() {
        return endingdatetime;
    }

    public void setEndingdatetime(String endingdatetime) {
        this.endingdatetime = endingdatetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
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
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(projectName);
        dest.writeString(surveyType);
        dest.writeString(_id);
        dest.writeString(_UID);
        dest.writeString(formDate);
        dest.writeString(username);
        dest.writeString(istatus);
        dest.writeString(istatus88x);
        dest.writeString(sA);
        dest.writeString(score_post);
        dest.writeString(percentage_pre);
        dest.writeString(score_pre);
        dest.writeString(percentage_post);
        dest.writeString(sInfo);
        dest.writeString(sno);
        dest.writeString(endingdatetime);
        dest.writeString(gpsLat);
        dest.writeString(gpsLng);
        dest.writeString(gpsDT);
        dest.writeString(gpsAcc);
        dest.writeString(gpsElev);
        dest.writeString(deviceID);
        dest.writeString(devicetagID);
        dest.writeString(synced);
        dest.writeString(synced_date);
        dest.writeString(appversion);
        dest.writeString(districtID);
        dest.writeString(healthFacilityCode);
        dest.writeString(providerName);
        dest.writeString(sessionStartTime);
        dest.writeString(sessionEndTime);
        dest.writeString(preTestStartTime);
        dest.writeString(preTestEndTime);
        dest.writeString(pre_test);
        dest.writeString(module);
        dest.writeString(gpsTime);
        dest.writeString(session);
        dest.writeString(wrong_pre);
        dest.writeString(wrong_post);
        dest.writeString(total);
        dest.writeString(post_test);
        dest.writeString(logginTime);
        dest.writeString(postTestEndTime);
        dest.writeString(postTestStartTime);
        dest.writeString(providerID);
        dest.writeString(moduleCode);
        dest.writeString(sessionCode);
    }


}