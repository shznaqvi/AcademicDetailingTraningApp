package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.DistrictTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class District {


    private static final String TAG = "Users_CONTRACT";
    Long _ID;

    Long DICTRICT_CODE;
    String District_name;

    public District() {
        // Default Constructor
    }

    public District(String username, String password, String username2, String password2) {


    }

    public String getDistrict_name() {
        return District_name;
    }

    public void setDistrict_name(String district_name) {
        District_name = district_name;
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


    public District Sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);

        this.DICTRICT_CODE = jsonObject.getLong(DistrictTable.COLUMN_DIST_ID);
        this.District_name = jsonObject.getString(DistrictTable.DISTRICT_NAME);

        return this;

    }

    public District Hydrate(Cursor cursor) {
        this.DICTRICT_CODE = cursor.getLong(cursor.getColumnIndexOrThrow(DistrictTable.COLUMN_DIST_ID));
        this.District_name = cursor.getString(cursor.getColumnIndexOrThrow(DistrictTable.DISTRICT_NAME));
        return this;

    }


    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(DistrictTable.COLUMN_DIST_ID, this.DICTRICT_CODE == null ? JSONObject.NULL : this.DICTRICT_CODE);
        json.put(DistrictTable.DISTRICT_NAME, this.District_name == null ? JSONObject.NULL : this.District_name);
        return json;
    }



}
