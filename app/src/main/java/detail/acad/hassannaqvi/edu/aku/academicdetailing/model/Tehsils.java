package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.TehsilTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Tehsils {


    private static final String TAG = "Districts_CONTRACT";
    Long _ID;
    Long TEHSIL_CODE;
    String Tehsil_name;
    Long COLUMN_DIST_ID;

    public Long getCOLUMN_DIST_ID() {
        return COLUMN_DIST_ID;
    }

    public void setCOLUMN_DIST_ID(Long COLUMN_DIST_ID) {
        this.COLUMN_DIST_ID = COLUMN_DIST_ID;
    }



    public Tehsils() {
        // Default Constructor
    }

    public Tehsils(String districtcode) {


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


    public Tehsils Sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);

        this.TEHSIL_CODE = jsonObject.getLong(TehsilTable.TEHSIL_CODE);
        this.Tehsil_name = jsonObject.getString(TehsilTable.TEHSIL_NAME);
        this.COLUMN_DIST_ID = jsonObject.getLong(TehsilTable.DIST_CODE);

        return this;

    }

    public Tehsils Hydrate(Cursor cursor) {
        this.TEHSIL_CODE = cursor.getLong(cursor.getColumnIndexOrThrow(TehsilTable.TEHSIL_CODE));
        this.Tehsil_name = cursor.getString(cursor.getColumnIndexOrThrow(TehsilTable.TEHSIL_NAME));
        this.COLUMN_DIST_ID = cursor.getLong(cursor.getColumnIndexOrThrow(TehsilTable.DIST_CODE));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(TehsilTable.TEHSIL_CODE, this.TEHSIL_CODE == null ? JSONObject.NULL : this.TEHSIL_CODE);
        json.put(TehsilTable.TEHSIL_NAME, this.Tehsil_name == null ? JSONObject.NULL : this.Tehsil_name);
        json.put(TehsilTable.DIST_CODE, this.COLUMN_DIST_ID == null ? JSONObject.NULL : this.COLUMN_DIST_ID);
        return json;
    }



}
