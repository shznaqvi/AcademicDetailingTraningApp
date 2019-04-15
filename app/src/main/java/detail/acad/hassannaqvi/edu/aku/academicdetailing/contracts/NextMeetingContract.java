package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class NextMeetingContract {

    public String doctorname;
    public String book_date;
    public String book_time;
    public String module;
    public String subModule;
    public String session;
    public String gpsLat;
    public String gpsLng;
    public String doBooking;
    public String bookBy;
    public String gps_time;
    public String bookingtype;
    public String _id;
    public String synced;

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String synced_date;


    private String _UID = "";
    private String username = "";

    private String formdate = ""; // Date
    private String deviceid = "";

    private long dist_id = 0;
    private String hf_name = "";
    private String hp_name = "";
    private long hp_code = 0;
    private String devicetagID = "";

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

        json.put(NMCTable._ID, this._id == null ? JSONObject.NULL : this._id);
        json.put(NMCTable.COLUMN_FORMDATE, this.formdate == null ? JSONObject.NULL : this.formdate);
        json.put(NMCTable.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
        json.put(NMCTable.COLUMN_DATE, this.book_date == null ? JSONObject.NULL : this.book_date);
        json.put(NMCTable.COLUMN_TIME, this.book_time == null ? JSONObject.NULL : this.book_time);
        json.put(NMCTable.COLUMN_MODULE_CODE, this.module == null ? JSONObject.NULL : this.module);
        json.put(NMCTable.COLUMN_SESSION_CODE, this.session == null ? JSONObject.NULL : this.session);
        json.put(NMCTable.COLUMN_LAT, this.gpsLat == null ? JSONObject.NULL : this.gpsLat);
        json.put(NMCTable.COLUMN_LNG, this.gpsLng == null ? JSONObject.NULL : this.gpsLng);
        json.put(NMCTable.COLUMN_BOOKBY, this.bookBy == null ? JSONObject.NULL : this.bookBy);
        json.put(NMCTable.COLUMN_GPSTIME, this.gps_time == null ? JSONObject.NULL : this.gps_time);
        json.put(NMCTable.COLUMN_BTYPE, this.bookingtype == null ? JSONObject.NULL : this.bookingtype);
        json.put(NMCTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(NMCTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(NMCTable.COLUMN_DIST_CODE, this.dist_id == 0 ? JSONObject.NULL : this.dist_id);
        json.put(NMCTable.COLUMN_HF_NAME, this.hf_name == null ? JSONObject.NULL : this.hf_name);
        json.put(NMCTable.COLUMN_HP_NAME, this.hp_name == null ? JSONObject.NULL : this.hp_name);
        json.put(NMCTable.COLUMN_HP_CODE, this.hp_code == 0 ? JSONObject.NULL : this.hp_code);
        json.put(NMCTable.COLUMN_USER, this.username == null ? JSONObject.NULL : this.username);
        json.put(NMCTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);
        json.put(NMCTable.COLUMN_UID, this._UID == null ? JSONObject.NULL : this._UID);

        return json;
    }

    public NextMeetingContract Hydrate(Cursor cursor) {

        this.module = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_MODULE_CODE));
        this.book_date = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DATE));
        this.book_time = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_TIME));
        this.session = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SESSION_CODE));
        this.gpsLat = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_LAT));
        this.gpsLng = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_LNG));
        this.bookBy = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BOOKBY));
        this.gps_time = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_GPSTIME));
        this.bookingtype = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BTYPE));
        this.gps_time = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_GPSTIME));
        this._id = cursor.getString(cursor.getColumnIndex(NMCTable._ID));
        this.synced = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SYNCED_DATE));
        this.formdate = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_FORMDATE));
        this.deviceid = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DEVICEID));
        this.dist_id = cursor.getLong(cursor.getColumnIndex(NMCTable.COLUMN_DIST_CODE));
        this.hf_name = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_HF_NAME));
        this.hp_name = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_HP_NAME));
        this.hp_code = cursor.getLong(cursor.getColumnIndex(NMCTable.COLUMN_HP_CODE));
        this.username = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_USER));
        this._UID = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_UID));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DEVICETAGID));


        return this;

    }
    public NextMeetingContract HydrateForAppointment(Cursor cursor) {

        this.module = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_MODULE_CODE));
        this.book_date = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DATE));
        this.book_time = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_TIME));
        this.session = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SESSION_CODE));
        this.bookBy = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BOOKBY));
        this.bookingtype = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BTYPE));
        this._id = cursor.getString(cursor.getColumnIndex(NMCTable._ID));
        this.dist_id = cursor.getLong(cursor.getColumnIndex(NMCTable.COLUMN_DIST_CODE));
        this.hf_name = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_HF_NAME));
        this.hp_name = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_HP_NAME));
        this.hp_code = cursor.getLong(cursor.getColumnIndex(NMCTable.COLUMN_HP_CODE));
        this.username = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_USER));
        this.formdate = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_FORMDATE));

//          NMCTable.COLUMN_DATE,
//                NMCTable.COLUMN_TIME,
//                NMCTable.COLUMN_MODULE_CODE,
//                NMCTable.COLUMN_SESSION_CODE,
//                NMCTable.COLUMN_BOOKBY,
//                NMCTable.COLUMN_BTYPE,
//                NMCTable._ID,
//                NMCTable.COLUMN_HP_CODE,
//                NMCTable.COLUMN_HF_NAME,
//                NMCTable.COLUMN_DIST_CODE,
//                NMCTable.COLUMN_HP_NAME,
//                NMCTable.COLUMN_USER,

        return this;

    }


    public NextMeetingContract Sync(JSONObject jsonObject) throws JSONException {
        this._UID = jsonObject.getString(NMCTable.COLUMN_UID);
        this.bookingtype = jsonObject.getString(NMCTable.COLUMN_BTYPE);
        this.bookBy = jsonObject.getString(NMCTable.COLUMN_BOOKBY);
        this.book_date = jsonObject.getString(NMCTable.COLUMN_DATE);
        this.book_time = jsonObject.getString(NMCTable.COLUMN_TIME);
        this.formdate = jsonObject.getString(NMCTable.COLUMN_FORMDATE);
        this.dist_id = jsonObject.getLong(NMCTable.COLUMN_DIST_CODE);
        this.hf_name = jsonObject.getString(NMCTable.COLUMN_HF_NAME);
        this.hp_code = jsonObject.getLong(NMCTable.COLUMN_HP_CODE);
        this.hp_name = jsonObject.getString(NMCTable.COLUMN_HP_NAME);
        this.module = jsonObject.getString(NMCTable.COLUMN_MODULE_CODE);
        this.session = jsonObject.getString(NMCTable.COLUMN_SESSION_CODE);
        this.synced = jsonObject.getString(NMCTable.COLUMN_SYNCED);
        this.formdate = jsonObject.getString(NMCTable.COLUMN_FORMDATE);

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


    public static abstract class NMCTable implements BaseColumns {

        public static final String TABLE_NAME = "next_meeting";
        public static final String _ID = "_id";
        public static final String COLUMN_HF_NAME = "hf_name";
        public static final String COLUMN_HP_NAME = "hp_name";
        public static final String COLUMN_HP_CODE = "hp_code";
        public static final String COLUMN_DIST_CODE = "dist_code";
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


}
