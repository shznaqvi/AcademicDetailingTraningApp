package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class UsersContract {


    private static final String TAG = "Users_CONTRACT";
    Long _ID;
    Long DICTRICT_CODE;
    String ROW_USERNAME;
    String ROW_PASSWORD;

    public UsersContract() {
        // Default Constructor
    }



    public UsersContract(String username, String password, String username2, String password2) {
        this.ROW_PASSWORD = password;
        this.ROW_USERNAME = username;
    }

    public Long getDICTRICT_CODE() {
        return DICTRICT_CODE;
    }

    public void setDICTRICT_CODE(Long DICTRICT_CODE) {
        this.DICTRICT_CODE = DICTRICT_CODE;
    }

    public Long getUserID() {
        return this._ID;
    }

    public void setId(int id) {
        this._ID = Long.valueOf(id);
    }

    public String getUserName() {
        return this.ROW_USERNAME;
    }

    public void setUserName(String username) {
        this.ROW_USERNAME = username;
    }

    public String getPassword() {
        return this.ROW_PASSWORD;
    }

    public void setPassword(String password) {
        this.ROW_PASSWORD = password;
    }

    public UsersContract Sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);
        this.ROW_USERNAME = jsonObject.getString(UsersTable.ROW_USERNAME);
        this.ROW_PASSWORD = jsonObject.getString(UsersTable.ROW_PASSWORD);
        this.DICTRICT_CODE = jsonObject.getLong(UsersTable.DISTRICT_CODE);

        return this;

    }

    public UsersContract Hydrate(Cursor cursor) {
        this.DICTRICT_CODE = cursor.getLong(cursor.getColumnIndex(UsersTable.DISTRICT_CODE));
        this.ROW_USERNAME = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_USERNAME));
        this.ROW_PASSWORD = cursor.getString(cursor.getColumnIndex(UsersTable.ROW_PASSWORD));

        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(UsersTable.ROW_USERNAME, this.ROW_USERNAME == null ? JSONObject.NULL : this.ROW_USERNAME);
        json.put(UsersTable.ROW_PASSWORD, this.ROW_PASSWORD == null ? JSONObject.NULL : this.ROW_PASSWORD);
        json.put(UsersTable.DISTRICT_CODE, this.DICTRICT_CODE == null ? JSONObject.NULL : this.DICTRICT_CODE);
        return json;
    }


    public static abstract class UsersTable implements BaseColumns {

        public static final String TABLE_NAME = "users";
        public static final String _ID = "id";
        public static final String DISTRICT_CODE = "district_code";
        public static final String ROW_USERNAME = "username";
        public static final String ROW_PASSWORD = "password";
    }
}
