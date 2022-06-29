package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.HealthProviderTable;

public class HealthProvider {

    private static final String TAG = "HealthProvider";
    String ID;
    String tehsil;
    String uc;
    String hp_name;
    String hp_designation;
    long COLUMN_DIST_ID;
    long hp_uen_code;
    long hf_code;

    public HealthProvider() {
        // Default Constructor
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getHp_name() {
        return hp_name;
    }

    public void setHp_name(String hp_name) {
        this.hp_name = hp_name;
    }

    public String getHp_designation() {
        return hp_designation;
    }

    public void setHp_designation(String hp_designation) {
        this.hp_designation = hp_designation;
    }

    public long getCOLUMN_DIST_ID() {
        return COLUMN_DIST_ID;
    }

    public void setCOLUMN_DIST_ID(long COLUMN_DIST_ID) {
        this.COLUMN_DIST_ID = COLUMN_DIST_ID;
    }

    public long getHp_uen_code() {
        return hp_uen_code;
    }

    public void setHp_uen_code(long hp_uen_code) {
        this.hp_uen_code = hp_uen_code;
    }

    public long getHf_code() {
        return hf_code;
    }

    public void setHf_code(long hf_code) {
        this.hf_code = hf_code;
    }

    public HealthProvider Sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);

        this.COLUMN_DIST_ID = jsonObject.getLong(HealthProviderTable.COLUMN_HP_DIST_CODE);
        this.tehsil = jsonObject.getString(HealthProviderTable.COLUMN_HP_TEHSIL);
        // this.uc = jsonObject.getString(HealthProviderTable.COLUMN_HP_UC_NAME);
        this.hp_uen_code = jsonObject.getLong(HealthProviderTable.COLUMN_HP_UEN_CODE);
        this.hf_code = jsonObject.getLong(HealthProviderTable.COLUMN_HF_CODE);
        this.hp_name = jsonObject.getString(HealthProviderTable.COLUMN_HP_NAME);
        //   this.hp_designation = jsonObject.getString(HealthProviderTable.COLUMN_HP_DESIGNATION);

        return this;

    }


    public HealthProvider Hydrate(Cursor cursor) {

//        this.hf_dist_name = cursor.getString(cursor.getColumnIndexOrThrow(HealthFacilityTable.COLUMN_HF_DISTRICT_NAME));
        this.hp_name = cursor.getString(cursor.getColumnIndexOrThrow(HealthProviderTable.COLUMN_HP_NAME));
        this.hp_uen_code = cursor.getLong(cursor.getColumnIndexOrThrow(HealthProviderTable.COLUMN_HP_UEN_CODE));

        return this;
    }





}