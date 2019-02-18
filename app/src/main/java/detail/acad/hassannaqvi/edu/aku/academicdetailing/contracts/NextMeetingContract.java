package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class NextMeetingContract {

    public String doctorname;
    public String date;
    public String time;
    public String module;
    public String subModule;
    public String session;
    public String lat;
    public String lng;
    public String doBooking;
    public String bookBy;
    public String gpsTime;
    public String bookingtype;
    public String _id;
    public String synced;
    public String synced_date;
    private String formdate = ""; // Date
    private String deviceid = "";

    private long dist_id = 0;
    private String hf_name = "";
    private String hp_name = "";
    private long hp_code = 0;

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

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(NMCTable.COLUMN_DOCTOR_NAME, this.doctorname == null ? JSONObject.NULL : this.doctorname);
        json.put(NMCTable._ID, this._id == null ? JSONObject.NULL : this._id);
        json.put(NMCTable.COLUMN_FORMDATE, this.formdate == null ? JSONObject.NULL : this.formdate);
        json.put(NMCTable.COLUMN_DEVICEID, this.deviceid == null ? JSONObject.NULL : this.deviceid);
        json.put(NMCTable.COLUMN_DATE, this.date == null ? JSONObject.NULL : this.date);
        json.put(NMCTable.COLUMN_TIME, this.time == null ? JSONObject.NULL : this.time);
        json.put(NMCTable.COLUMN_MOD, this.module == null ? JSONObject.NULL : this.module);
        json.put(NMCTable.COLUMN_SUBMOD, this.subModule == null ? JSONObject.NULL : this.subModule);
        json.put(NMCTable.COLUMN_SESSION, this.session == null ? JSONObject.NULL : this.session);
        json.put(NMCTable.COLUMN_LAT, this.lat == null ? JSONObject.NULL : this.lat);
        json.put(NMCTable.COLUMN_LNG, this.lng == null ? JSONObject.NULL : this.lng);
        json.put(NMCTable.COLUMN_BOOK_DATE, this.doBooking == null ? JSONObject.NULL : this.doBooking);
        json.put(NMCTable.COLUMN_BOOKBY, this.bookBy == null ? JSONObject.NULL : this.bookBy);
        json.put(NMCTable.COLUMN_GPSTIME, this.gpsTime == null ? JSONObject.NULL : this.gpsTime);
        json.put(NMCTable.COLUMN_BTYPE, this.bookingtype == null ? JSONObject.NULL : this.bookingtype);
        json.put(NMCTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(NMCTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);

        return json;
    }

    public NextMeetingContract Hydrate(Cursor cursor) {

        this.doctorname = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DOCTOR_NAME));
        this.module = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_MOD));
        this.date = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DATE));
        this.time = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_TIME));
        this.subModule = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SUBMOD));
        this.session = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SESSION));
        this.lat = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_LAT));
        this.lng = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_LNG));
        this.doBooking = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BOOK_DATE));
        this.bookBy = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BOOKBY));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_GPSTIME));
        this.bookingtype = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_BTYPE));
        this.gpsTime = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_GPSTIME));
        this._id = cursor.getString(cursor.getColumnIndex(NMCTable._ID));
        this.synced = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_SYNCED_DATE));
        this.formdate = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_FORMDATE));
        this.deviceid = cursor.getString(cursor.getColumnIndex(NMCTable.COLUMN_DEVICEID));


        return this;

    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
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

    public NextMeetingContract Sync(JSONObject jsonObject) throws JSONException{

        this.doctorname = jsonObject.getString(NMCTable.COLUMN_DOCTOR_NAME);
        this.date = jsonObject.getString(NMCTable.COLUMN_DATE);
        this.time = jsonObject.getString(NMCTable.COLUMN_TIME);
        this.module = jsonObject.getString(NMCTable.COLUMN_MOD);
        this.subModule = jsonObject.getString(NMCTable.COLUMN_SUBMOD);
        this.session = jsonObject.getString(NMCTable.COLUMN_SESSION);
        this.formdate = jsonObject.getString(NMCTable.COLUMN_FORMDATE);
        this.deviceid = jsonObject.getString(NMCTable.COLUMN_DEVICEID);

        return this;
    }




    public String getDoctorName() {
        return doctorname;
    }

    public void setDoctorName(String doctorName) {
        this.doctorname = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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


    public static abstract class NMCTable implements BaseColumns{

        public static final String TABLE_NAME = "next_meeting";
        public static final String _ID = "_id";
        public static final String COLUMN_DOCTOR_NAME = "provider_name";
        public static final String COLUMN_HF_NAME = "hf_name";
        public static final String COLUMN_HP_NAME = "hp_name";
        public static final String COLUMN_HP_CODE= "hp_code";
        public static final String COLUMN_DIST_CODE= "dist_code";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_MOD = "mod";
        public static final String COLUMN_SUBMOD = "sub_mod";
        public static final String COLUMN_SESSION = "session";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LNG = "lng";
        public static final String COLUMN_BTYPE= "book_type";
        public static final String COLUMN_BOOK_DATE= "book_date";
        public static final String COLUMN_DEVICEID= "device_id";
        public static final String COLUMN_BOOKBY= "booking_by";
        public static final String COLUMN_GPSTIME= "gpsTime";
        public static final String COLUMN_SYNCED= "synced";
        public static final String COLUMN_SYNCED_DATE= "synced_date";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String nms_Url = "next_meeting.php";

    }


}
