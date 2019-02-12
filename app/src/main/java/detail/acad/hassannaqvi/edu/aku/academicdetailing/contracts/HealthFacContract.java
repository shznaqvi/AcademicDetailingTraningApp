package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class HealthFacContract {

    private static final String TAG = "Villages_CONTRACT";
    String ID;
    String hf_name;
    long hf_dhis;
    String hf_tehsil;
    String hf_uc;
    String hf_name_govt;
    String hf_dist_name;
    long hf_district_code;
    long hf_uen_code;





    public HealthFacContract() {
        // Default Constructor
    }

    public HealthFacContract Sync(JSONObject jsonObject) throws JSONException {
        this.hf_name = jsonObject.getString(singleHF.COLUMN_HF_NAME);
        this.hf_dhis = jsonObject.getLong(singleHF.COLUMN_HF_DHIS);
        this.hf_tehsil = jsonObject.getString(singleHF.COLUMN_HF_TEHSIL_NAME);
        this.hf_uc = jsonObject.getString(singleHF.COLUMN_HF_UC_NAME);
        this.hf_name_govt = jsonObject.getString(singleHF.COLUMN_HF_NAME_GOVT);
//        this.hf_dist_name = jsonObject.getString(singleHF.COLUMN_HF_DISTRICT_NAME);
        this.hf_district_code = jsonObject.getLong(singleHF.COLUMN_HF_DIST_CODE);
        this.hf_uen_code = jsonObject.getLong(singleHF.COLUMN_HF_UEN_CODE);



        return this;
    }

    public HealthFacContract HydrateHF(Cursor cursor) {

//        this.hf_dist_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_DISTRICT_NAME));
        this.hf_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_NAME));

        return this;
    }
    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(singleHF.COLUMN_ID, this.ID == null ? JSONObject.NULL : this.ID);
        json.put(singleHF.COLUMN_HF_NAME, this.hf_name == null ? JSONObject.NULL : this.hf_name);
//        json.put(singleHF.COLUMN_HF_DHIS, this.hf_dhis == null ? JSONObject.NULL : this.hf_dhis);
        json.put(singleHF.COLUMN_HF_TEHSIL_NAME, this.hf_tehsil == null ? JSONObject.NULL : this.hf_tehsil);
        json.put(singleHF.COLUMN_HF_UC_NAME, this.hf_uc == null ? JSONObject.NULL : this.hf_uc);
        json.put(singleHF.COLUMN_HF_NAME_GOVT, this.hf_name_govt == null ? JSONObject.NULL : this.hf_name_govt);
//        json.put(singleHF.COLUMN_HF_DISTRICT_NAME, this.hf_dist_name == null ? JSONObject.NULL : this.hf_dist_name);
//        json.put(singleHF.COLUMN_HF_DIST_CODE, this.hf_district_code == null ? JSONObject.NULL : this.hf_district_code);
//        json.put(singleHF.COLUMN_HF_UEN_CODE, this.hf_uen_code == null ? JSONObject.NULL : this.hf_uen_code);

        return json;
    }

    public HealthFacContract HydrateHF() {
        this.hf_dist_name = "....";
        this.hf_name = "....";
//        this.hf_uen_code = "....";
        return this;
    }

    public long getHf_district_code() {
        return hf_district_code;
    }

    public void setHf_district_code(long hf_district_code) {
        this.hf_district_code = hf_district_code;
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




    public static abstract class singleHF implements BaseColumns {

        public static final String TABLE_NAME = "health_fc";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HF_NAME = "hf_name";
//        public static final String COLUMN_HF_DISTRICT_NAME = "hf_district";
        public static final String COLUMN_HF_TEHSIL_NAME = "hf_tehsil";
        public static final String COLUMN_HF_UC_NAME = "hf_uc";
        public static final String COLUMN_HF_UEN_CODE = "hf_uen_code";
        public static final String COLUMN_HF_DHIS = "hf_dhis";
        public static final String COLUMN_HF_NAME_GOVT = "hf_name_govt";
        public static final String COLUMN_HF_DIST_CODE = "hf_district_code";
    }

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

}