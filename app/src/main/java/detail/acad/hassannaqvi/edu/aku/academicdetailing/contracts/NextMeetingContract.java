package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class NextMeetingContract {

    public String doctorName;
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

        this.doctorName = jsonObject.getString(NMCTable.COLUMN_DOCTOR_NAME);
        this.date = jsonObject.getString(NMCTable.COLUMN_DATE);
        this.time = jsonObject.getString(NMCTable.COLUMN_TIME);
        this.module = jsonObject.getString(NMCTable.COLUMN_MOD);
        this.subModule = jsonObject.getString(NMCTable.COLUMN_SUBMOD);
        this.session = jsonObject.getString(NMCTable.COLUMN_SESSION);

        return this;
    }



    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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
        public static final String COLUMN_DOCTOR_NAME = "doc_name";
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
    }


}
