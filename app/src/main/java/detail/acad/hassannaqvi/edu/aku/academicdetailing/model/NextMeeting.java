package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;

import android.database.Cursor;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.NextMeetingTable;

public class NextMeeting {

    public String doctorname =  StringUtils.EMPTY;
    public String book_date =  StringUtils.EMPTY;
    public String book_time =  StringUtils.EMPTY;
    public String module =  StringUtils.EMPTY;
    public String subModule =  StringUtils.EMPTY;
    public String session =  StringUtils.EMPTY;
    public String gpsLat =  StringUtils.EMPTY;
    public String gpsLng =  StringUtils.EMPTY;
    public String doBooking =  StringUtils.EMPTY;
    public String bookBy =  StringUtils.EMPTY;
    public String gps_time =  StringUtils.EMPTY;
    public String bookingtype =  StringUtils.EMPTY;
    public String _id =  StringUtils.EMPTY;
    public String synced =  StringUtils.EMPTY;

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSyncDate() {
        return synced_date;
    }

    public void setSyncDate(String synced_date) {
        this.synced_date = synced_date;
    }

    public String synced_date =  StringUtils.EMPTY;


    private String _UID =  StringUtils.EMPTY;
    private String username =  StringUtils.EMPTY;

    private String formdate  =  StringUtils.EMPTY; // Date
    private String deviceid  =  StringUtils.EMPTY;

    private long dist_id = 0;
    private String hf_name =  StringUtils.EMPTY;;
    private String hp_name  =  StringUtils.EMPTY;;
    private long hp_code = 0;
    private String devicetagID =  StringUtils.EMPTY;;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_UID() {
        return _UID;
    }

    public void set_UID(String _UID) {
        this._UID = _UID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(NextMeetingTable.COLUMN_ID, this._id == null ? JSONObject.NULL : this._id);
        json.put(NextMeetingTable.COLUMN_FORMDATE, this.formdate == null ? JSONObject.NULL : this.formdate);
        json.put(NextMeetingTable.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
        json.put(NextMeetingTable.COLUMN_DATE, this.book_date == null ? JSONObject.NULL : this.book_date);
        json.put(NextMeetingTable.COLUMN_TIME, this.book_time == null ? JSONObject.NULL : this.book_time);
        json.put(NextMeetingTable.COLUMN_MODULE_CODE, this.module == null ? JSONObject.NULL : this.module);
        json.put(NextMeetingTable.COLUMN_SESSION_CODE, this.session == null ? JSONObject.NULL : this.session);
        json.put(NextMeetingTable.COLUMN_LAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(NextMeetingTable.COLUMN_LNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(NextMeetingTable.COLUMN_BOOKBY, this.bookBy == null ? JSONObject.NULL : this.bookBy);
        json.put(NextMeetingTable.COLUMN_GPSTIME, this.gps_time == null ? JSONObject.NULL : this.gps_time);
        json.put(NextMeetingTable.COLUMN_BTYPE, this.bookingtype == null ? JSONObject.NULL : this.bookingtype);
        json.put(NextMeetingTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(NextMeetingTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(NextMeetingTable.COLUMN_DIST_CODE, this.dist_id == 0 ? JSONObject.NULL : this.dist_id);
        json.put(NextMeetingTable.COLUMN_HF_NAME, this.hf_name == null ? JSONObject.NULL : this.hf_name);
        json.put(NextMeetingTable.COLUMN_HP_NAME, this.hp_name == null ? JSONObject.NULL : this.hp_name);
        json.put(NextMeetingTable.COLUMN_HP_CODE, this.hp_code == 0 ? JSONObject.NULL : this.hp_code);
        json.put(NextMeetingTable.COLUMN_USER, this.username == null ? JSONObject.NULL : this.username);
        json.put(NextMeetingTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(NextMeetingTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);
        return json;
    }

    public NextMeeting Hydrate(Cursor cursor) {

        this.module = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_MODULE_CODE));
        this.book_date = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_DATE));
        this.book_time = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_TIME));
        this.session = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_SESSION_CODE));
        this.gpsLat = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_LAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_LNG));
        this.bookBy = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_BOOKBY));
        this.bookingtype = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_BTYPE));
        this.gps_time = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_GPSTIME));
        this._id = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_ID));
        this.synced = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_SYNCED_DATE));
        this.formdate = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_FORMDATE));
        this.deviceid = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_DEVICEID));
        this.dist_id = cursor.getLong(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_DIST_CODE));
        this.hf_name = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_HF_NAME));
        this.hp_name = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_HP_NAME));
        this.hp_code = cursor.getLong(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_HP_CODE));
        this.username = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_USER));
        this._UID = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_UID));
        this.devicetagID = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_DEVICETAGID));


        return this;

    }
    public NextMeeting HydrateForAppointment(Cursor cursor) {

        this.module = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_MODULE_CODE));
        this.book_date = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_DATE));
        this.book_time = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_TIME));
        this.session = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_SESSION_CODE));
        this.bookBy = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_BOOKBY));
        this.bookingtype = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_BTYPE));
        this._id = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_ID));
        this.dist_id = cursor.getLong(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_DIST_CODE));
        this.hf_name = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_HF_NAME));
        this.hp_name = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_HP_NAME));
        this.hp_code = cursor.getLong(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_HP_CODE));
        this.username = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_USER));
        this.formdate = cursor.getString(cursor.getColumnIndexOrThrow(NextMeetingTable.COLUMN_FORMDATE));

//          NextMeetingTable.COLUMN_DATE,
//                NextMeetingTable.COLUMN_TIME,
//                NextMeetingTable.COLUMN_MODULE_CODE,
//                NextMeetingTable.COLUMN_SESSION_CODE,
//                NextMeetingTable.COLUMN_BOOKBY,
//                NextMeetingTable.COLUMN_BTYPE,
//                NextMeetingTable._ID,
//                NextMeetingTable.COLUMN_HP_CODE,
//                NextMeetingTable.COLUMN_HF_NAME,
//                NextMeetingTable.COLUMN_DIST_CODE,
//                NextMeetingTable.COLUMN_HP_NAME,
//                NextMeetingTable.COLUMN_USER,

        return this;

    }


    public NextMeeting Sync(JSONObject jsonObject) throws JSONException {
        this._UID = jsonObject.getString(NextMeetingTable.COLUMN_UID);
        this.bookingtype = jsonObject.getString(NextMeetingTable.COLUMN_BTYPE);
        this.bookBy = jsonObject.getString(NextMeetingTable.COLUMN_BOOKBY);
        this.book_date = jsonObject.getString(NextMeetingTable.COLUMN_DATE);
        this.book_time = jsonObject.getString(NextMeetingTable.COLUMN_TIME);
        this.gps_time = jsonObject.getString(NextMeetingTable.COLUMN_GPSTIME);
        this.gpsLat = jsonObject.getString(NextMeetingTable.COLUMN_LAT);
        this.gpsLng = jsonObject.getString(NextMeetingTable.COLUMN_LNG);
        this.deviceid = jsonObject.getString(NextMeetingTable.COLUMN_DEVICEID);
        this.devicetagID = jsonObject.getString(NextMeetingTable.COLUMN_DEVICETAGID);
        this.formdate = jsonObject.getString(NextMeetingTable.COLUMN_FORMDATE);
        this.dist_id = jsonObject.getLong(NextMeetingTable.COLUMN_DIST_CODE);
        this.hf_name = jsonObject.getString(NextMeetingTable.COLUMN_HF_NAME);
        this.hp_code = jsonObject.getLong(NextMeetingTable.COLUMN_HP_CODE);
        this.hp_name = jsonObject.getString(NextMeetingTable.COLUMN_HP_NAME);
        this.module = jsonObject.getString(NextMeetingTable.COLUMN_MODULE_CODE);
        this.session = jsonObject.getString(NextMeetingTable.COLUMN_SESSION_CODE);
        this.synced = jsonObject.getString(NextMeetingTable.COLUMN_SYNCED);
        this.synced_date = jsonObject.getString(NextMeetingTable.COLUMN_SYNCED_DATE);
        this.username = jsonObject.getString(NextMeetingTable.COLUMN_USER);

        return this;


//        "_uid": "3206860ee5ba1081",
//                "book_type": "1",
//                "booking_by": "dmu@aku",
//                "book_date": "04-12-2019",
//                "dist_code": "414",
//                "formdate": "12-04-19 10:34",
//                "hf_name": "nn",
//                "hp_code": "35376",
//                "hp_name": "chd",
//                "module_code": "1",
//                "session_code": "10201",
//                "book_time": "10:34:49",
//                "synced": 2

    }

    public long getDist_id() {
        return dist_id;
    }

    public void setDist_id(long dist_id) {
        this.dist_id = dist_id;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }

    public String getHp_name() {
        return hp_name;
    }

    public void setHp_name(String hp_name) {
        this.hp_name = hp_name;
    }

    public long getHp_code() {
        return hp_code;
    }

    public void setHp_code(long hp_code) {
        this.hp_code = hp_code;
    }

    public String getFormdate() {
        return formdate;
    }

    public void setFormdate(String formdate) {
        this.formdate = formdate;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getGps_time() {
        return gps_time;
    }

    public void setGps_time(String gps_time) {
        this.gps_time = gps_time;
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

    public String getDoBooking() {
        return doBooking;
    }

    public void setDoBooking(String doBooking) {
        this.doBooking = doBooking;
    }

    public String getBookBy() {
        return bookBy;
    }

    public void setBookBy(String bookBy) {
        this.bookBy = bookBy;
    }

    public String getBookingtype() {
        return bookingtype;
    }

    public void setBookingtype(String bookingtype) {
        this.bookingtype = bookingtype;
    }


    public String getDoctorName() {
        return doctorname;
    }

    public void setDoctorName(String doctorName) {
        this.doctorname = doctorName;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSubModule() {
        return subModule;
    }

    public void setSubModule(String subModule) {
        this.subModule = subModule;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }





}
