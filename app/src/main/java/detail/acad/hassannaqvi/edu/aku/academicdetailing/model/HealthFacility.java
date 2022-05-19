package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;


import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.HealthFacilityTable;

public class HealthFacility {

    private static final String TAG = "HealthFacility";
    String ID;
    String hf_name;
    long hf_dhis;
    String hf_tehsil;
    String hf_uc;
    String hf_name_govt;
    String hf_dist_name;
    long hf_COLUMN_DIST_ID;
    long hf_uen_code;





    public HealthFacility() {
        // Default Constructor
    }

    public HealthFacility Sync(JSONObject jsonObject) throws JSONException {
        this.hf_name = jsonObject.getString(HealthFacilityTable.COLUMN_HF_NAME);
//        this.hf_dhis = jsonObject.getLong(HealthFacilityTable.COLUMN_HF_DHIS);
        this.hf_tehsil = jsonObject.getString(HealthFacilityTable.COLUMN_HF_TEHSIL_NAME);
//        this.hf_uc = jsonObject.getString(HealthFacilityTable.COLUMN_HF_UC_NAME);
//        this.hf_name_govt = jsonObject.getString(HealthFacilityTable.COLUMN_HF_NAME_GOVT);
//        this.hf_dist_name = jsonObject.getString(HealthFacilityTable.COLUMN_HF_DISTRICT_NAME);
        this.hf_COLUMN_DIST_ID = jsonObject.getLong(HealthFacilityTable.COLUMN_HF_DIST_CODE);
        this.hf_uen_code = jsonObject.getLong(HealthFacilityTable.COLUMN_HF_UEN_CODE);



        return this;
    }

    public HealthFacility HydrateHF(Cursor cursor) {

//        this.hf_dist_name = cursor.getString(cursor.getColumnIndexOrThrow(HealthFacilityTable.COLUMN_HF_DISTRICT_NAME));
        this.hf_name = cursor.getString(cursor.getColumnIndexOrThrow(HealthFacilityTable.COLUMN_HF_NAME));
        this.hf_uen_code = cursor.getLong(cursor.getColumnIndexOrThrow(HealthFacilityTable.COLUMN_HF_UEN_CODE));

        return this;
    }
    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(HealthFacilityTable.COLUMN_ID, this.ID == null ? JSONObject.NULL : this.ID);
        json.put(HealthFacilityTable.COLUMN_HF_NAME, this.hf_name == null ? JSONObject.NULL : this.hf_name);
//        json.put(HealthFacilityTable.COLUMN_HF_DHIS, this.hf_dhis == null ? JSONObject.NULL : this.hf_dhis);
        json.put(HealthFacilityTable.COLUMN_HF_TEHSIL_NAME, this.hf_tehsil == null ? JSONObject.NULL : this.hf_tehsil);
        json.put(HealthFacilityTable.COLUMN_HF_UC_NAME, this.hf_uc == null ? JSONObject.NULL : this.hf_uc);
        json.put(HealthFacilityTable.COLUMN_HF_NAME_GOVT, this.hf_name_govt == null ? JSONObject.NULL : this.hf_name_govt);
//        json.put(HealthFacilityTable.COLUMN_HF_DISTRICT_NAME, this.hf_dist_name == null ? JSONObject.NULL : this.hf_dist_name);
//        json.put(HealthFacilityTable.COLUMN_HF_DIST_CODE, this.hf_COLUMN_DIST_ID == null ? JSONObject.NULL : this.hf_COLUMN_DIST_ID);
//        json.put(HealthFacilityTable.COLUMN_HF_UEN_CODE, this.hf_uen_code == null ? JSONObject.NULL : this.hf_uen_code);

        return json;
    }

    public HealthFacility HydrateHF() {
        this.hf_dist_name = "....";
        this.hf_name = "....";
//        this.hf_uen_code = "....";
        return this;
    }

    public long getHf_COLUMN_DIST_ID() {
        return hf_COLUMN_DIST_ID;
    }

    public void setHf_COLUMN_DIST_ID(long hf_COLUMN_DIST_ID) {
        this.hf_COLUMN_DIST_ID = hf_COLUMN_DIST_ID;
    }

    public long getHf_dhis() {
        return hf_dhis;
    }

    public void setHf_dhis(long hf_dhis) {
        this.hf_dhis = hf_dhis;
    }

    public String getHf_tehsil() {
        return hf_tehsil;
    }

    public void setHf_tehsil(String hf_tehsil) {
        this.hf_tehsil = hf_tehsil;
    }

    public String getHf_uc() {
        return hf_uc;
    }

    public void setHf_uc(String hf_uc) {
        this.hf_uc = hf_uc;
    }

    public String getHf_name_govt() {
        return hf_name_govt;
    }

    public void setHf_name_govt(String hf_name_govt) {
        this.hf_name_govt = hf_name_govt;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHf_name() {
        return hf_name;
    }

    public void setHf_name(String hf_name) {
        this.hf_name = hf_name;
    }


    public String getHf_dist_name() {
        return hf_dist_name;
    }

    public void setHf_dist_name(String hf_dist_name) {
        this.hf_dist_name = hf_dist_name;
    }


    public long getHf_uen_code() {
        return hf_uen_code;
    }

    public void setHf_uen_code(long hf_uen_code) {
        this.hf_uen_code = hf_uen_code;
    }






/*
    public static class ColumnsClass {
        private String columnName, columnClause;

        public ColumnsClass(String columnName, String columnClause) {
            this.columnName = columnName;
            this.columnClause = columnClause;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnClause() {
            return columnClause;
        }
    }
*/

}