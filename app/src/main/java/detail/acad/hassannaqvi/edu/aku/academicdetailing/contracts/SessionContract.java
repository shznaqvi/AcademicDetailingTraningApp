package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class SessionContract {

    private String session = "";
    private String module = "";
    private String sessionTime = "";
    private String synced = "";
    private String synced_date = "";
    private int slidenumber;
    private String _id;

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();

        json.put(SessionTable.COLUMN_SESSION, this.session == null ? JSONObject.NULL : this.session);
        json.put(SessionTable.COLUMN_MODULE, this.module == null ? JSONObject.NULL : this.module);
        json.put(SessionTable.COLUMN_SESSION_TIME, this.sessionTime == null ? JSONObject.NULL : this.sessionTime);
        json.put(SessionTable.COLUMN_SLIDE_NUMBER, this.slidenumber == 0 ? JSONObject.NULL : this.slidenumber);
        json.put(SessionTable.COLUMN_SYNCED, this.synced == null ? JSONObject.NULL : this.synced);
        json.put(SessionTable.COLUMN_SYNCED_DATE, this.synced_date == null ? JSONObject.NULL : this.synced_date);
        json.put(SessionTable._ID, this._id == null ? JSONObject.NULL : this._id);
        return json;
    }

    public SessionContract Hydrate(Cursor cursor) {

        this.session = cursor.getString(cursor.getColumnIndex(SessionTable.COLUMN_SESSION));
        this.module = cursor.getString(cursor.getColumnIndex(SessionTable.COLUMN_MODULE));
        this.sessionTime = cursor.getString(cursor.getColumnIndex(SessionTable.COLUMN_SESSION_TIME));
        this.slidenumber = cursor.getInt(cursor.getColumnIndex(SessionTable.COLUMN_SLIDE_NUMBER));
        this.synced = cursor.getString(cursor.getColumnIndex(SessionTable.COLUMN_SYNCED));
        this.synced_date = cursor.getString(cursor.getColumnIndex(SessionTable.COLUMN_SYNCED_DATE));
        this._id = cursor.getString(cursor.getColumnIndex(SessionTable._ID));


        return this;

    }
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public int getSlideNumber() {
        return slidenumber;
    }

    public void setSlideNumber(int slideNumber) {
        this.slidenumber = slideNumber;
    }

    public abstract class SessionTable implements BaseColumns{

        public static final String TABLE_NAME = "sessions_table";
        public static final String _ID = "_id";
        public static final String COLUMN_SESSION = "session";
        public static final String COLUMN_MODULE = "module";
        public static final String COLUMN_SESSION_TIME = "session_time";
        public static final String COLUMN_SLIDE_NUMBER = "slide_number";
        public static final String COLUMN_SYNCED= "synced";
        public static final String COLUMN_SYNCED_DATE= "synced_date";

    }
}
