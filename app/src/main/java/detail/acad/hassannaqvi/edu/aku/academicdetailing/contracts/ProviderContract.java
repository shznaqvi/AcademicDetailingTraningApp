package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;


import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class ProviderContract {

    private static final String TAG = "Villages_CONTRACT";
    String ID;
    String tehsil;
    String uc;
    String hp_name;
    String hp_designation;
    long district_code;
    long hp_uen_code;
    long hf_code;

    public ProviderContract() {
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

    public long getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(long district_code) {
        this.district_code = district_code;
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

    public ProviderContract Sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);

        this.district_code = jsonObject.getLong(singleProvider.COLUMN_HP_DIST_CODE);
        this.tehsil = jsonObject.getString(singleProvider.COLUMN_HP_TEHSIL);
        this.uc = jsonObject.getString(singleProvider.COLUMN_HP_UC_NAME);
        this.hp_uen_code = jsonObject.getLong(singleProvider.COLUMN_HP_UEN_CODE);
        this.hf_code = jsonObject.getLong(singleProvider.COLUMN_HF_CODE);
        this.hp_name = jsonObject.getString(singleProvider.COLUMN_HP_NAME);
        this.hp_designation = jsonObject.getString(singleProvider.COLUMN_HP_DESIGNATION);

        return this;

    }


    public ProviderContract Hydrate(Cursor cursor) {

//        this.hf_dist_name = cursor.getString(cursor.getColumnIndex(singleHF.COLUMN_HF_DISTRICT_NAME));
        this.hp_name = cursor.getString(cursor.getColumnIndex(singleProvider.COLUMN_HP_NAME));
        this.hp_uen_code = cursor.getLong(cursor.getColumnIndex(singleProvider.COLUMN_HP_UEN_CODE));

        return this;
    }



    public static abstract class singleProvider implements BaseColumns {

        public static final String TABLE_NAME = "providers";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HP_NAME = "hp_name";
        public static final String COLUMN_HP_TEHSIL = "tehsil";
        public static final String COLUMN_HP_UC_NAME = "uc";
        public static final String COLUMN_HP_UEN_CODE = "hp_uen_code";
        public static final String COLUMN_HF_CODE = "hf_code";
        public static final String COLUMN_HP_DESIGNATION = "hp_designation";
        public static final String COLUMN_HP_DIST_CODE = "district_code";

    }

}