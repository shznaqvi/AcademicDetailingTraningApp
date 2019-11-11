package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class TehsilsContract {


    private static final String TAG = "Districts_CONTRACT";
    Long _ID;

    Long TEHSIL_CODE;
    String Tehsil_name;

    public TehsilsContract() {
        // Default Constructor
    }

    public TehsilsContract(String districtcode) {


    }

    public String getTehsil_name() {
        return Tehsil_name;
    }

    public void setTehsil_name(String tehsil_name) {
        Tehsil_name = tehsil_name;
    }

    public Long getTEHSIL_CODE() {
        return TEHSIL_CODE;
    }

    public void setTEHSIL_CODE(Long TEHSIL_CODE) {
        this.TEHSIL_CODE = TEHSIL_CODE;
    }

    public Long getDistrictCode() {
        return this._ID;
    }

    public void setId(int id) {
        this._ID = Long.valueOf(id);
    }


    public TehsilsContract Sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);

        this.TEHSIL_CODE = jsonObject.getLong(TehsilTable.TEHSIL_CODE);
        this.Tehsil_name = jsonObject.getString(TehsilTable.TEHSIL_NAME);

        return this;

    }

    public TehsilsContract Hydrate(Cursor cursor) {
        this.TEHSIL_CODE = cursor.getLong(cursor.getColumnIndex(TehsilTable.TEHSIL_CODE));
        this.Tehsil_name = cursor.getString(cursor.getColumnIndex(TehsilTable.TEHSIL_NAME));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(TehsilTable.TEHSIL_CODE, this.TEHSIL_CODE == null ? JSONObject.NULL : this.TEHSIL_CODE);
        json.put(TehsilTable.TEHSIL_NAME, this.Tehsil_name == null ? JSONObject.NULL : this.Tehsil_name);
        return json;
    }


    public static abstract class TehsilTable implements BaseColumns {

        public static final String TABLE_NAME = "tehsils";
        public static final String _ID = "id";
        public static final String TEHSIL_CODE = "tehsil_code";
        public static final String TEHSIL_NAME = "tehsil_name";

    }
}
