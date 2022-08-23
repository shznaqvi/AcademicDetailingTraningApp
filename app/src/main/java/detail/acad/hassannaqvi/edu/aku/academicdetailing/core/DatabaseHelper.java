package detail.acad.hassannaqvi.edu.aku.academicdetailing.core;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.UserAuth.checkPassword;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.util.Log;

import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.DistrictTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.EntryLogTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.FormsTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.HealthFacilityTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.HealthProviderTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.NextMeetingTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.SessionTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.UsersTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.District;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.EntryLog;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Forms;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.HealthFacility;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.HealthProvider;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.NextMeeting;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Session;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Tehsils;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Users;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.LoginActivity;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "dr_reg.db";
    public static final String DATABASE_COPY = DATABASE_NAME.replace(".", "_copy.");
    public static final String PROJECT_NAME = "DR-REGISTRATION-FORM";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_DELETE_SESSION = "DROP TABLE IF EXISTS " + SessionTable.TABLE_NAME;
    private static final String SQL_DELETE_DISTRICTS = "DROP TABLE IF EXISTS " + DistrictTable.TABLE_NAME;
    private static final String SQL_DELETE_TEHSILS = "DROP TABLE IF EXISTS " + TableContracts.TehsilTable.TABLE_NAME;
    private static final String SQL_DELETE_NMS = "DROP TABLE IF EXISTS " + NextMeetingTable.TABLE_NAME;
    private static final String SQL_DELETE_HF = "DROP TABLE IF EXISTS " + HealthFacilityTable.TABLE_NAME;
    private static final String SQL_DELETE_HP = "DROP TABLE IF EXISTS " + HealthProviderTable.TABLE_NAME;
    //    private static final String SQL_DELETE_TEHSIL = "DROP TABLE IF EXISTS " + TehsilTable.TABLE_NAME;
    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;
    private static final String SQL_DELETE_ENTRYLOGS =
            "DROP TABLE IF EXISTS " + EntryLogTable.TABLE_NAME;


    private final String TAG = "DatabaseHelper";

    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());
    String dtToday = new SimpleDateFormat("MM-dd-yyyy").format(new Date().getTime());
    String dtToday1 = new SimpleDateFormat("MM-dd-yy").format(new Date().getTime());

    DataDownload delegate;
    public static final String DATABASE_PASSWORD = MainApp.IBAHC;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // Data download
        if (context.getClass().getName().equals(LoginActivity.class.getName()))
            delegate = (DataDownload) context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateTable.SQL_CREATE_USERS);
        db.execSQL(CreateTable.SQL_CREATE_FORMS);
        db.execSQL(CreateTable.SQL_CREATE_SESSION_TABLE);
        db.execSQL(CreateTable.SQL_CREATE_NMS);
        db.execSQL(CreateTable.SQL_CREATE_DISTRICT_TABLE);
        db.execSQL(CreateTable.SQL_CREATE_TEHSIL_TABLE);
        db.execSQL(CreateTable.SQL_CREATE_HF_TABLE);
        db.execSQL(CreateTable.SQL_CREATE_HP_TABLE);
        db.execSQL(CreateTable.SQL_CREATE_ENTRYLOGS);
//        db.execSQL(SQL_CREATE_TEHSIL);
        /*db.execSQL(SQL_CREATE_TEHSILS);
        db.execSQL(SQL_CREATE_UCS);
        db.execSQL(SQL_CREATE_LHWS);*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_DISTRICTS);
        db.execSQL(SQL_DELETE_TEHSILS);
        db.execSQL(SQL_DELETE_SESSION);
        db.execSQL(SQL_DELETE_NMS);
        db.execSQL(SQL_DELETE_HF);
        db.execSQL(SQL_DELETE_HP);
        db.execSQL(SQL_DELETE_ENTRYLOGS);

//        db.execSQL(SQL_DELETE_TEHSIL);

    }

    public ArrayList<Users> getAllUsers() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        ArrayList<Users> userList = null;
            userList = new ArrayList<Users>();
            String QUERY = "SELECT * FROM " + UsersTable.TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            int num = cursor.getCount();
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    Users user = new Users().hydrate(cursor);
                    userList.add(user);
                }
            }
        return userList;
    }

    //get UnSyncedTables
    public JSONArray getUnsyncedForms() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        //whereClause = null;
        whereClause = FormsTable.COLUMN_SYNCED + " = '' AND " +
                FormsTable.COLUMN_ISTATUS + "!= ''";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = FormsTable.COLUMN_ID + " ASC";

        JSONArray allForms = new JSONArray();
        c = db.query(
                FormsTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedForm: " + c.getCount());
            Forms form = new Forms();
            allForms.put(form.Hydrate(c).toJSONObject());


        }

        c.close();

        Log.d(TAG, "getUnsyncedForm: " + allForms.toString().length());
        Log.d(TAG, "getUnsyncedForm: " + allForms);
        return allForms;
    }




    public JSONArray getUnsyncedSessions() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        //whereClause = null;
        whereClause = SessionTable.COLUMN_SYNCED + " = ''";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = SessionTable._ID + " ASC";

        JSONArray allSession= new JSONArray();
        c = db.query(
                SessionTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedSession: " + c.getCount());
            Session session = new Session();
            allSession.put(session.Hydrate(c).toJSONObject());


        }

        c.close();


        return allSession;
    }


    public JSONArray getUnsyncedNextMeetings() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        Cursor c = null;
        String[] columns = null;

        String whereClause;
        whereClause = null;
       //whereClause = NextMeetingTable.COLUMN_SYNCED + " is null ";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = NextMeetingTable._ID + " ASC";

        JSONArray allNextMeeting= new JSONArray();
        c = db.query(
                NextMeetingTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedNextMeeting: " + c.getCount());
            /*NextMeeting nextMeeting = new NextMeeting();
            allNextMeeting.put(nextMeeting.Hydrate(c).toJSONObject());
*/

            String syncedValue = c.getString(c.getColumnIndexOrThrow(NextMeetingTable.COLUMN_SYNCED));
            if (syncedValue == null || syncedValue.equals("")) {
                NextMeeting nextMeeting = new NextMeeting();
                allNextMeeting.put(nextMeeting.Hydrate(c).toJSONObject());
            }
       }

        c.close();
        
        return allNextMeeting;
    }

    public JSONArray getUnsyncedEntryLogs() throws JSONException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;

        String whereClause;
        //whereClause = null;
        whereClause = EntryLogTable.COLUMN_SYNCED + " = ''";

        String[] whereArgs = null;

        String groupBy = null;
        String having = null;

        String orderBy = EntryLogTable._ID + " ASC";

        JSONArray allEntryLog= new JSONArray();
        c = db.query(
                EntryLogTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            /** WorkManager Upload
             /*Form fc = new Form();
             allFC.add(fc.Hydrate(c));*/
            Log.d(TAG, "getUnsyncedEntryLog: " + c.getCount());
            EntryLog entryLog = new EntryLog();
            allEntryLog.put(entryLog.Hydrate(c).toJSONObject());


        }

        c.close();


        return allEntryLog;
    }

    /*public void syncUsers(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.execSQL(" DELETE FROM " + UsersTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'user'");

        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                if (jsonObjectUser != null) {

                    Users users = new Users();
                    users.sync(jsonObjectUser);

                    ContentValues values = new ContentValues();

                    values.put(UsersTable.COLUMN_USERNAME, users.getUserName());
                    values.put(UsersTable.COLUMN_PASSWORD, users.getPassword());
                    values.put(UsersTable.COLUMN_FULLNAME, users.getFullname());
                    values.put(UsersTable.COLUMN_ENABLED, users.getEnabled());
                    values.put(UsersTable.COLUMN_ISNEW_USER, users.getNewUser());
                    values.put(UsersTable.COLUMN_PWD_EXPIRY, users.getPwdExpiry());
                    values.put(UsersTable.COLUMN_DESIGNATION, users.getDesignation());
                    values.put(UsersTable.COLUMN_DIST_ID, users.getDist_id());
                    db.insert(UsersTable.TABLE_NAME, null, values);
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

*/
    public boolean checkingUser(String username, long distCode) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
// New value for one column
        String[] columns = {
                UsersTable._ID,
                UsersTable.COLUMN_DIST_ID
        };

// Which row to update, based on the ID
        String selection = UsersTable.COLUMN_USERNAME + " = ?" + " AND " + UsersTable.COLUMN_DIST_ID + " = ?";
        String[] selectionArgs = {username, String.valueOf(distCode)};
        Cursor cursor = db.query(UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        if (cursor.moveToFirst()) {
            cursor.moveToNext();
        }
        int count = cursor.getCount();
        cursor.close();
        return count > 0;

    }

    public int syncAppUser(JSONArray userList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.delete(UsersTable.TABLE_NAME, null, null);
        int insertCount = 0;
        for (int i = 0; i < userList.length(); i++) {

            JSONObject jsonObjectUser = userList.getJSONObject(i);

            Users user = new Users();
            user.sync(jsonObjectUser);
            ContentValues values = new ContentValues();

            values.put(UsersTable.COLUMN_USERNAME, user.getUserName());
            values.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
            values.put(UsersTable.COLUMN_FULLNAME, user.getFullname());
            values.put(UsersTable.COLUMN_ENABLED, user.getEnabled());
            values.put(UsersTable.COLUMN_ISNEW_USER, user.getNewUser());
            values.put(UsersTable.COLUMN_PWD_EXPIRY, user.getPwdExpiry());
            values.put(UsersTable.COLUMN_DESIGNATION, user.getDesignation());
            values.put(UsersTable.COLUMN_DIST_ID, user.getDist_id());
            //values.put(UsersTable.COLUMN_UC_CODE, user.getUccode());
            long rowID = db.insert(UsersTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;
        }
        return insertCount;
    }

    public int syncHealthFacility(JSONArray hfList) {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.execSQL(" DELETE FROM " + HealthFacilityTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'health_fc'");    //TODO you have to add table name manually in order to reset primary key
        int insertCount = 0;
        try {
            JSONArray jsonArray = hfList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                HealthFacility healthFacility = new HealthFacility();
                healthFacility.Sync(jsonObjectUser);

                ContentValues values = new ContentValues();

                //   values.put(HealthFacilityTable.COLUMN_HF_DHIS, healthFacility.getHf_dhis());
                values.put(HealthFacilityTable.COLUMN_HF_DIST_CODE, healthFacility.getHf_COLUMN_DIST_ID());
                values.put(HealthFacilityTable.COLUMN_HF_TEHSIL_NAME, healthFacility.getHf_tehsil());
                values.put(HealthFacilityTable.COLUMN_HF_UC_NAME, healthFacility.getHf_uc());
                values.put(HealthFacilityTable.COLUMN_HF_NAME, healthFacility.getHf_name());
                //   values.put(HealthFacilityTable.COLUMN_HF_NAME_GOVT, healthFacility.getHf_name_govt());
                values.put(HealthFacilityTable.COLUMN_HF_CODE, healthFacility.getHf_uen_code());
                long rowID = db.insert(HealthFacilityTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;

                //    Log.d(TAG, "syncHF: count " + count);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return insertCount;

    }

    public int syncversionApp(JSONArray VersionList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        long count = 0;

        JSONObject jsonObjectVersion = ((JSONArray) VersionList.getJSONObject(0).get("elements")).getJSONObject(0);

        String appPath = jsonObjectVersion.getString("outputFile");
        String versionCode = jsonObjectVersion.getString("versionCode");

        MainApp.editor.putString("outputFile", jsonObjectVersion.getString("outputFile"));
        MainApp.editor.putString("versionCode", jsonObjectVersion.getString("versionCode"));
        MainApp.editor.putString("versionName", jsonObjectVersion.getString("versionName") + ".");
        MainApp.editor.apply();
        count++;
          /*  VersionApp Vc = new VersionApp();
            Vc.sync(jsonObjectVersion);

            ContentValues values = new ContentValues();

            values.put(VersionTable.COLUMN_PATH_NAME, Vc.getPathname());
            values.put(VersionTable.COLUMN_VERSION_CODE, Vc.getVersioncode());
            values.put(VersionTable.COLUMN_VERSION_NAME, Vc.getVersionname());

            count = db.insert(VersionTable.TABLE_NAME, null, values);
            if (count > 0) count = 1;

        } catch (Exception ignored) {
        } finally {
        }*/

        return (int) count;
    }

    public int synchealthcare_providers(JSONArray hpList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.execSQL(" DELETE FROM " + HealthProviderTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'providers'");    //TODO you have to add table name manually in order to reset primary key
        int insertCount = 0;
            JSONArray jsonArray = hpList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                HealthProvider healthProvider = new HealthProvider();
                healthProvider.Sync(jsonObjectUser);
                ContentValues values = new ContentValues();
                values.put(HealthProviderTable.COLUMN_HP_DIST_CODE, healthProvider.getCOLUMN_DIST_ID());
                values.put(HealthProviderTable.COLUMN_HP_TEHSIL, healthProvider.getTehsil());
                //   values.put(HealthProviderTable.COLUMN_HP_UC_NAME, healthProvider.getUc());
                values.put(HealthProviderTable.COLUMN_HP_UEN_CODE, healthProvider.getHp_uen_code());
                values.put(HealthProviderTable.COLUMN_HF_CODE, healthProvider.getHf_code());
                values.put(HealthProviderTable.COLUMN_HP_NAME, healthProvider.getHp_name());
                //   values.put(HealthProviderTable.COLUMN_HP_DESIGNATION, healthProvider.getHp_designation());
                long rowID = db.insert(HealthProviderTable.TABLE_NAME, null, values);
                if (rowID != -1) insertCount++;

            }
        return insertCount;
    }

    public int syncdistrict(JSONArray districList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.execSQL(" DELETE FROM " + DistrictTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'districts'");


        JSONArray jsonArray = districList;
        int insertCount = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            District dc = new District();
            dc.Sync(jsonObject);
            ContentValues values = new ContentValues();
            values.put(DistrictTable.COLUMN_DIST_ID, dc.getDICTRICT_CODE());
            values.put(DistrictTable.DISTRICT_NAME, dc.getDistrict_name());
            long rowID = db.insert(DistrictTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;

        }

        return insertCount;

    }

    public int synctehsils(JSONArray tehsilList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        db.execSQL(" DELETE FROM " + TableContracts.TehsilTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'tehsils'");


        JSONArray jsonArray = tehsilList;
        int insertCount = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Tehsils dc = new Tehsils();
            dc.Sync(jsonObject);
            ContentValues values = new ContentValues();
            values.put(TableContracts.TehsilTable.TEHSIL_CODE, dc.getTEHSIL_CODE());
            values.put(TableContracts.TehsilTable.TEHSIL_NAME, dc.getTehsil_name());
            long rowID = db.insert(TableContracts.TehsilTable.TABLE_NAME, null, values);
            if (rowID != -1) insertCount++;

        }

        return insertCount;

    }

    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
// New value for one column
        String[] columns = {
                UsersTable._ID,
                UsersTable.COLUMN_DIST_ID
        };

// Which row to update, based on the ID
        String selection = UsersTable.COLUMN_USERNAME + " = ?" + " AND " + UsersTable.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        if (cursor.moveToFirst()) {
            MainApp.districtCode = cursor.getInt(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_DIST_ID));
            cursor.moveToNext();
        }

        int count = cursor.getCount();

        cursor.close();
        return count > 0;
    }


    public District getDistrict(int distCode) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        // New value for one column
        String[] columns = {
                DistrictTable.DISTRICT_NAME,
                DistrictTable.COLUMN_DIST_ID,
        };

        // Which row to update, based on the ID
        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = null;
        District district = null;

        if (distCode > 0) {
            selection = DistrictTable.COLUMN_DIST_ID + " = ?";
            selectionArgs = new String[]{String.valueOf(distCode)};
            cursor = db.query(DistrictTable.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order


            if (cursor.moveToFirst()) {
                district = new District().Hydrate(cursor);
                cursor.moveToNext();
            }

            cursor.close();
        }
        return district;
    }


    public Tehsils getTehsils(int tehsilCode) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        // New value for one column
        String[] columns = {
                TableContracts.TehsilTable.TEHSIL_NAME,
                TableContracts.TehsilTable.TEHSIL_CODE,
        };

        // Which row to update, based on the ID
        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = null;
        Tehsils tehsils = null;

        if (tehsilCode > 0) {
            selection = TableContracts.TehsilTable.TEHSIL_CODE + " = ?";
            selectionArgs = new String[]{String.valueOf(tehsilCode)};
            cursor = db.query(TableContracts.TehsilTable.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order


            if (cursor.moveToFirst()) {
                tehsils = new Tehsils().Hydrate(cursor);
                cursor.moveToNext();
            }

            cursor.close();
        }
        return tehsils;
    }



    public Users getUser(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        // New value for one column
        String[] columns = {
                UsersTable.COLUMN_USERNAME,
                UsersTable.COLUMN_PASSWORD,
                UsersTable.COLUMN_DIST_ID,
        };

        // Which row to update, based on the ID
        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = null;
        Users user = null;

        selection = UsersTable.COLUMN_USERNAME + " = ?  " + " AND " + UsersTable.COLUMN_PASSWORD + " = ?";
        selectionArgs = new String[]{username, password};
        cursor = db.query(UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order


        cursor.moveToFirst();
        do {
            user = new Users().hydrate(cursor);
        } while (cursor.moveToNext());
        return user;
    }

    public List<HealthFacility> getHealthFacilityData(String id) {
        List<HealthFacility> formList = new ArrayList<>();

        String[] columns = {
                HealthFacilityTable.COLUMN_HF_NAME,
                HealthFacilityTable.COLUMN_HF_CODE
        };
        String selection = HealthFacilityTable.COLUMN_HF_DIST_CODE + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        String orderBy =
                HealthFacilityTable.COLUMN_HF_NAME + " COLLATE NOCASE ASC;";

        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        Cursor c = db.query(
                HealthFacilityTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy);

        if (c.moveToFirst()) {
            do {
                HealthFacility fc = new HealthFacility();
                fc.setHf_name(c.getString(c.getColumnIndexOrThrow(HealthFacilityTable.COLUMN_HF_NAME)));
                fc.setHf_uen_code(c.getString(c.getColumnIndexOrThrow(HealthFacilityTable.COLUMN_HF_CODE)));
                formList.add(fc.HydrateHF(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public List<HealthProvider> getHPData(String  id) {
        List<HealthProvider> formList = new ArrayList<>();

        String[] columns = {
                HealthProviderTable.COLUMN_HP_NAME,
                HealthProviderTable.COLUMN_HP_UEN_CODE
        };
        String selection = HealthProviderTable.COLUMN_HP_UEN_CODE + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        String orderBy =
                HealthProviderTable.COLUMN_HP_NAME + " COLLATE NOCASE ASC;";

        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        Cursor c = db.query(
                HealthProviderTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy);

        if (c.moveToFirst()) {
            do {
                HealthProvider fc = new HealthProvider();
                fc.setHp_name(c.getString(c.getColumnIndexOrThrow(HealthProviderTable.COLUMN_HP_NAME)));
                fc.setHp_uen_code(c.getString(c.getColumnIndexOrThrow(HealthProviderTable.COLUMN_HP_UEN_CODE)));
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }


    public ArrayList<HealthProvider> getHealthProviderByFacility(String fcCode) {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause = HealthProviderTable.COLUMN_HF_CODE + " = ? ";
        String[] whereArgs = {fcCode};
        String groupBy = null;
        String having = null;

        String orderBy = HealthProviderTable.COLUMN_HP_UEN_CODE + " ASC";
        ArrayList<HealthProvider> hp = new ArrayList<>();

        c = db.query(
                HealthProviderTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return8
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            hp.add(new HealthProvider().Hydrate(c));
        }
        return hp;
    }


    public List<District> getDistrictList(int id) {
        List<District> formList = new ArrayList<>();

        String[] columns = {
                DistrictTable.DISTRICT_NAME,
                DistrictTable.COLUMN_DIST_ID
        };
        String selection = DistrictTable.COLUMN_DIST_ID + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(id)};

        String orderBy =
                DistrictTable.COLUMN_DIST_ID + " COLLATE NOCASE ASC;";

        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        Cursor c = db.query(
                DistrictTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy);

        if (c.moveToFirst()) {
            do {
                District fc = new District();
                fc.setDistrict_name(c.getString(c.getColumnIndexOrThrow(DistrictTable.DISTRICT_NAME)));
                fc.setDICTRICT_CODE(c.getString(c.getColumnIndexOrThrow(DistrictTable.COLUMN_DIST_ID)));
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    /*public List<District> getDistrictList(int id) {
        List<District> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DistrictTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                District fc = new District();
                fc.setDICTRICT_CODE(c.getString(c.getColumnIndexOrThrow(DistrictTable.COLUMN_DIST_ID)));
                fc.setDistrict_name(c.getString(c.getColumnIndexOrThrow(DistrictTable.DISTRICT_NAME)));
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }
*/
    //Functions that dealing with table data
    public boolean doLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalArgumentException {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = null;
        String whereClause = UsersTable.COLUMN_USERNAME + "=? ";
        String[] whereArgs = {username};
        String groupBy = null;
        String having = null;
        String orderBy = UsersTable.COLUMN_ID + " ASC";

        Users loggedInUser = new Users();
        c = db.query(
                UsersTable.TABLE_NAME,  // The table to query
                columns,                   // The columns to return
                whereClause,               // The columns for the WHERE clause
                whereArgs,                 // The values for the WHERE clause
                groupBy,                   // don't group the rows
                having,                    // don't filter by row groups
                orderBy                    // The sort order
        );
        while (c.moveToNext()) {
            loggedInUser = new Users().hydrate(c);

        }

        c.close();

        if (checkPassword(password, loggedInUser.getPassword())) {
            MainApp.user = loggedInUser;
            MainApp.district = getDistrict(Integer.parseInt(loggedInUser.getDist_id()));
            return c.getCount() > 0;
        } else {
            return false;
        }
    }

    public int updatePassword(String hashedPassword) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(UsersTable.COLUMN_PASSWORD, hashedPassword);
        values.put(UsersTable.COLUMN_ISNEW_USER, "");

        String selection = UsersTable.COLUMN_USERNAME + " =? ";
        String[] selectionArgs = {MainApp.user.getUserName()};

        return db.update(UsersTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public Long addEntryLog(EntryLog entryLog) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
        ContentValues values = new ContentValues();
        values.put(EntryLogTable.COLUMN_PROJECT_NAME, entryLog.getProjectName());
        values.put(EntryLogTable.COLUMN_UUID, entryLog.getUuid());
        values.put(EntryLogTable.COLUMN_HHID, entryLog.getHhid());
        values.put(EntryLogTable.COLUMN_USERNAME, entryLog.getUserName());
        values.put(EntryLogTable.COLUMN_SYSDATE, entryLog.getSysDate());
        values.put(EntryLogTable.COLUMN_ISTATUS, entryLog.getiStatus());
        values.put(EntryLogTable.COLUMN_ISTATUS96x, entryLog.getiStatus96x());
        values.put(EntryLogTable.COLUMN_ENTRY_TYPE, entryLog.getEntryType());
        values.put(EntryLogTable.COLUMN_ENTRY_DATE, entryLog.getEntryDate());
        values.put(EntryLogTable.COLUMN_DEVICEID, entryLog.getDeviceId());
        values.put(EntryLogTable.COLUMN_SYNCED, entryLog.getSynced());
        values.put(EntryLogTable.COLUMN_SYNC_DATE, entryLog.getSyncDate());
        values.put(EntryLogTable.COLUMN_APPVERSION, entryLog.getAppver());

        long newRowId;
        newRowId = db.insertOrThrow(
                EntryLogTable.TABLE_NAME,
                EntryLogTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }


    public int updatesEntryLogColumn(String column, String value, String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(column, value);

        String selection = EntryLogTable._ID + " =? ";
        String[] selectionArgs = {id};

        return db.update(EntryLogTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public interface DataDownload {
        void downloded(boolean flag);
    }

    public Long addForm(Forms fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECTNAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_SURVEYTYPE, fc.getSurveyType());
        values.put(FormsTable.COLUMN_UID, fc.getUID());
//        values.put(FormsTable._ID, forms.get_ID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
        values.put(FormsTable.COLUMN_USER, fc.getUsername());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88X, fc.getIstatus88x());
//        values.put(FormsTable.COLUMN_SA, forms.getsA());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, fc.getEndingdatetime());
        values.put(FormsTable.COLUMN_GPSLAT, fc.getGpsLat());
        values.put(FormsTable.COLUMN_GPSLNG, fc.getGpsLng());
        values.put(FormsTable.COLUMN_GPSDT, fc.getGpsDT());
        values.put(FormsTable.COLUMN_GPSACC, fc.getGpsAcc());
        values.put(FormsTable.COLUMN_GPSELEV, fc.getGpsElev());
        values.put(FormsTable.COLUMN_DEVICEID, fc.getDeviceID());
        values.put(FormsTable.COLUMN_GPSTIME, fc.getGpsTime());
        values.put(FormsTable.COLUMN_DEVICETAGID, fc.getDevicetagID());
        values.put(FormsTable.COLUMN_SYNCED, fc.getSynced());
        values.put(FormsTable.COLUMN_SYNCED_DATE, fc.getSynced_date());
        values.put(FormsTable.COLUMN_APPVERSION, fc.getAppversion());
        values.put(FormsTable.COLUMN_DIST_ID, fc.getDistrictID());
        values.put(FormsTable.COLUMN_loggin_TIME, fc.getLogginTime());
        values.put(FormsTable.COLUMN_HFACILITY_NAME, fc.getHealthFacilityCode());
        values.put(FormsTable.COLUMN_PROVIDER_NAME, fc.getProviderName());
        values.put(FormsTable.COLUMN_PROVIDER_ID, fc.getProviderID());
        values.put(FormsTable.COLUMN_PRETEST_START_TIME, fc.getPreTestStartTime());
        values.put(FormsTable.COLUMN_PRETEST_END_TIME, fc.getPreTestEndTime());
        values.put(FormsTable.COLUMN_POSTTEST_START_TIME, fc.getPostTestEndTime());
        values.put(FormsTable.COLUMN_USER, MainApp.user.getUserName());



        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public void updateSyncedformsAD(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_SYNCED, true);
        values.put(FormsTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = FormsTable.COLUMN_ID + " =?";
        String[] whereArgs = {id};

        int count = db.update(
                FormsTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedsessions(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SessionTable.COLUMN_SYNCED, true);
        values.put(SessionTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = SessionTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                SessionTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncednext_meeting(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(NextMeetingTable.COLUMN_SYNCED, true);
        values.put(NextMeetingTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = NextMeetingTable.COLUMN_ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                NextMeetingTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public void updateSyncedEntryLog(String id) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(EntryLogTable.COLUMN_SYNCED, true);
        values.put(EntryLogTable.COLUMN_SYNC_DATE, new Date().toString());

// Which row to update, based on the title
        String where = EntryLogTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                EntryLogTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public int updateFormID(Forms fc) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " =?";
        String[] selectionArgs = {String.valueOf(fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateNMCFormID(NextMeeting nmc) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(NextMeetingTable.COLUMN_UID, nmc.get_UID());

// Which row to update, based on the ID
        String selection = NextMeetingTable.COLUMN_ID + " =?";
        String[] selectionArgs = {String.valueOf(nmc.get_id())};

        int count = db.update(NextMeetingTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updateSessionFormID(Session fc) {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(SessionTable.COLUMN_UID, fc.get_UID());


// Which row to update, based on the ID
        String selection = SessionTable._ID + " =?";
        String[] selectionArgs = {String.valueOf(fc.get_id())};

        int count = db.update(SessionTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public Collection<Forms> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = {
                FormsTable._ID,
                //ChildTable.COLUMN_DSSID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_SYNCED,

        };
        String whereClause = FormsTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<Forms> allFC = new ArrayList<>();
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                Forms fc = new Forms();
                fc.set_ID(c.getString(c.getColumnIndexOrThrow(FormsTable._ID)));
                fc.setFormDate(c.getString(c.getColumnIndexOrThrow(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndexOrThrow(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndexOrThrow(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase(DATABASE_PASSWORD);
        String[] columns = new String[]{"message"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try {
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        } catch (SQLException sqlEx) {
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        } catch (Exception ex) {

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }

    public int updateEnding() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.forms.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88X, MainApp.forms.getIstatus88x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.forms.getEndingdatetime());
        values.put(FormsTable.COLUMN_SESSION_START_TIME, MainApp.forms.getSessionStartTime());
        values.put(FormsTable.COLUMN_SESSION_END_TIME, MainApp.forms.getSessionEndTime());
        values.put(FormsTable.COLUMN_SESSION_CODE, MainApp.forms.getSessionCode());
        values.put(FormsTable.COLUMN_SCORE_PRE, MainApp.forms.getScore_pre());
        values.put(FormsTable.COLUMN_SCORE_POST, MainApp.forms.getScore_post());
        values.put(FormsTable.COLUMN_PER_PRE, MainApp.forms.getPercentage_pre());
        values.put(FormsTable.COLUMN_PER_POST, MainApp.forms.getPercentage_post());
        values.put(FormsTable.COLUMN_TOTAL, MainApp.forms.getTotal());
        values.put(FormsTable.COLUMN_MODULE_CODE, MainApp.forms.getModuleCode());
        values.put(FormsTable.COLUMN_WRONG_PRE, MainApp.forms.getWrong_pre());
        values.put(FormsTable.COLUMN_WRONG_POST, MainApp.forms.getWrong_post());


// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.forms.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatePreTest() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PRETEST_START_TIME, MainApp.forms.getPreTestStartTime());
        values.put(FormsTable.COLUMN_PRETEST_END_TIME, MainApp.forms.getPreTestEndTime());
        values.put(FormsTable.COLUMN_PRE_TEST, MainApp.forms.getPre_test());


// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.forms.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatePostTest() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_POSTTEST_START_TIME, MainApp.forms.getPostTestStartTime());
        values.put(FormsTable.COLUMN_POSTTEST_END_TIME, MainApp.forms.getPostTestEndTime());
        values.put(FormsTable.COLUMN_POST_TEST, MainApp.forms.getPost_test());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.forms.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Long addSessionData(Session sc) {

        long count;
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);

        ContentValues values = new ContentValues();
        values.put(SessionTable.COLUMN_MODULE_CODE, sc.getModule());
        values.put(SessionTable.COLUMN_SESSION_CODE, sc.getSession());
        values.put(SessionTable.COLUMN_SESSION_TIME, sc.getSessionTime());
        values.put(SessionTable.COLUMN_SLIDE_NUMBER, sc.getSlideNumber());
        values.put(SessionTable.COLUMN_DEVICEID, sc.getDeviceid());
        values.put(SessionTable.COLUMN_FORMDATE, sc.getFormdate());
        values.put(SessionTable.COLUMN_DEVICETAGID, sc.getDevicetagID());
        values.put(SessionTable.COLUMN_USER, sc.getUsername());
        values.put(SessionTable.COLUMN_UUID, sc.get_UUID());
        values.put(SessionTable.COLUMN_SYNCED, sc.getSynced());
        values.put(SessionTable.COLUMN_SYNCED_DATE, sc.getSyncDate());
        values.put(SessionTable.COLUMN_USER, MainApp.user.getUserName());

        return db.insert(SessionTable.TABLE_NAME, null, values);


    }

    public String getProviderName() {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);

        String providerName;
        Cursor cursor = db.query(FormsTable.TABLE_NAME, new String[]{FormsTable.COLUMN_PROVIDER_NAME},
                FormsTable._ID + "=?", new String[]{String.valueOf(MainApp.forms.get_ID())},
                null, null, null);
        cursor.moveToFirst();
        do {
            providerName = cursor.getString(cursor.getColumnIndexOrThrow(FormsTable.COLUMN_PROVIDER_NAME));
        } while (cursor.moveToNext());


        return providerName;

    }

    public long updateNMS() {

        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);

        long count;
// New value for one column
        ContentValues values = new ContentValues();
        values.put(NextMeetingTable.COLUMN_DATE, MainApp.nmc.getBook_date());
        values.put(NextMeetingTable.COLUMN_TIME, MainApp.nmc.getBook_time());
        values.put(NextMeetingTable.COLUMN_UID, MainApp.nmc.get_UID());
        values.put(NextMeetingTable.COLUMN_MODULE_CODE, MainApp.nmc.getModule());
        values.put(NextMeetingTable.COLUMN_SESSION_CODE, MainApp.nmc.getSession());
        values.put(NextMeetingTable.COLUMN_BOOKBY, MainApp.nmc.getBookBy());
        values.put(NextMeetingTable.COLUMN_DEVICEID, MainApp.nmc.getDeviceid());
        values.put(NextMeetingTable.COLUMN_USER, MainApp.user.getUserName());
        values.put(NextMeetingTable.COLUMN_LAT, MainApp.nmc.getGpsLat());
        values.put(NextMeetingTable.COLUMN_LNG, MainApp.nmc.getGpsLng());
        values.put(NextMeetingTable.COLUMN_BTYPE, MainApp.nmc.getBookingtype());
        values.put(NextMeetingTable.COLUMN_SYNCED, MainApp.nmc.getSynced());
        values.put(NextMeetingTable.COLUMN_SYNCED_DATE, MainApp.nmc.getSyncDate());
        values.put(NextMeetingTable.COLUMN_GPSTIME, MainApp.nmc.getGps_time());
        values.put(NextMeetingTable.COLUMN_FORMDATE, MainApp.nmc.getFormdate());
        values.put(NextMeetingTable.COLUMN_HF_NAME, MainApp.nmc.getHf_name());
        values.put(NextMeetingTable.COLUMN_HP_NAME, MainApp.nmc.getHp_name());
        values.put(NextMeetingTable.COLUMN_HP_CODE, MainApp.nmc.getHp_code());
        values.put(NextMeetingTable.COLUMN_DIST_CODE, MainApp.nmc.getDist_id());
        values.put(NextMeetingTable.COLUMN_DEVICETAGID, MainApp.nmc.getDevicetagID());

        long newRowId;
        newRowId = db.insert(
                NextMeetingTable.TABLE_NAME,
                NextMeetingTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
        //return db.insert(NextMeetingTable.TABLE_NAME, null, values);



    }

    public int syncnext_meeting(JSONArray appList) throws JSONException {
        SQLiteDatabase db = this.getWritableDatabase(DATABASE_PASSWORD);
//        db.execSQL(" DELETE FROM " + UsersTable.TABLE_NAME);
//        db.execSQL(" DELETE FROM sqlite_sequence where name = 'user'");

        //DELETING Data with synced = 2
        db.delete(NextMeetingTable.TABLE_NAME, NextMeetingTable.COLUMN_SYNCED + "=?", new String[]{"2"});
        int insertCount = 0;
            for (int i = 0; i < appList.length(); i++) {
                JSONObject jsonObjectUser = appList.getJSONObject(i);
                if (jsonObjectUser != null) {

                    NextMeeting nextMeeting = new NextMeeting();
                    nextMeeting.Sync(jsonObjectUser);

                    ContentValues values = new ContentValues();

                    values.put(NextMeetingTable.COLUMN_UID, nextMeeting.get_UID());
                    values.put(NextMeetingTable.COLUMN_BTYPE, nextMeeting.getBookingtype());
                    values.put(NextMeetingTable.COLUMN_BOOKBY, nextMeeting.getBookBy());
                    values.put(NextMeetingTable.COLUMN_DATE, nextMeeting.getBook_date());
                    values.put(NextMeetingTable.COLUMN_DIST_CODE, nextMeeting.getDist_id());
                    values.put(NextMeetingTable.COLUMN_FORMDATE, nextMeeting.getFormdate());
                    values.put(NextMeetingTable.COLUMN_HF_NAME, nextMeeting.getHf_name());
                    values.put(NextMeetingTable.COLUMN_HP_CODE, nextMeeting.getHp_code());
                    values.put(NextMeetingTable.COLUMN_HP_NAME, nextMeeting.getHp_name());
                    values.put(NextMeetingTable.COLUMN_MODULE_CODE, nextMeeting.getModule());
                    values.put(NextMeetingTable.COLUMN_SESSION_CODE, nextMeeting.getSession());
                    values.put(NextMeetingTable.COLUMN_TIME, nextMeeting.getBook_time());
                    values.put(NextMeetingTable.COLUMN_SYNCED, nextMeeting.getSynced());
                    long rowID = db.insert(NextMeetingTable.TABLE_NAME, null, values);
                    if (rowID != -1) insertCount++;
                }

            }
        return insertCount;
        }



/*    public long getUsersCount() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        long count = DatabaseUtils.queryNumEntries(db, UsersTable.TABLE_NAME);
        db.close();
        return count;
    }*/

    public long getDistrictCount() {

        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        // New value for one column
        String[] columns = null;
        // Which row to update, based on the ID
        String selection = null;
        String[] selectionArgs = null;
        int distCount = 0;
        Cursor cursor;

        selection = null;
        cursor = db.query(DistrictTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        distCount = cursor.getCount();
        return distCount;
    }

    public List<NextMeeting> getAppointmentsList() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = {
                NextMeetingTable.COLUMN_DATE,
                NextMeetingTable.COLUMN_TIME,
                NextMeetingTable.COLUMN_MODULE_CODE,
                NextMeetingTable.COLUMN_SESSION_CODE,
                NextMeetingTable.COLUMN_BOOKBY,
                NextMeetingTable.COLUMN_BTYPE,
                NextMeetingTable.COLUMN_ID,
                NextMeetingTable.COLUMN_HP_CODE,
                NextMeetingTable.COLUMN_HF_NAME,
                NextMeetingTable.COLUMN_DIST_CODE,
                NextMeetingTable.COLUMN_HP_NAME,
                NextMeetingTable.COLUMN_USER,
                NextMeetingTable.COLUMN_FORMDATE

        };
        String whereClause = NextMeetingTable.COLUMN_SYNCED + " is not 2 and " + NextMeetingTable.COLUMN_FORMDATE + " Like ? ";
        String[] whereArgs = new String[]{"%" + spDateT.substring(0, 8).trim() + "%"};
        String groupBy = null;
        String having = null;

        String orderBy =
                NextMeetingTable.COLUMN_ID + " DESC";

        List<NextMeeting> allSc = new ArrayList<NextMeeting>();
        try {
            c = db.query(
                    NextMeetingTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    NextMeeting nC = new NextMeeting();
                    allSc.add(nC.HydrateForAppointment(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
        }
        return allSc;
    }

    public List<NextMeeting> getTodaysAppointment() {
        SQLiteDatabase db = this.getReadableDatabase(DATABASE_PASSWORD);
        Cursor c = null;
        String[] columns = {
                NextMeetingTable.COLUMN_DATE,
                NextMeetingTable.COLUMN_TIME,
                NextMeetingTable.COLUMN_MODULE_CODE,
                NextMeetingTable.COLUMN_SESSION_CODE,
                NextMeetingTable.COLUMN_BOOKBY,
                NextMeetingTable.COLUMN_BTYPE,
                NextMeetingTable.COLUMN_ID,
                NextMeetingTable.COLUMN_HP_CODE,
                NextMeetingTable.COLUMN_HF_NAME,
                NextMeetingTable.COLUMN_DIST_CODE,
                NextMeetingTable.COLUMN_HP_NAME,
                NextMeetingTable.COLUMN_USER,
                NextMeetingTable.COLUMN_FORMDATE

        };
        String whereClause = NextMeetingTable.COLUMN_SYNCED + " is 2 ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                NextMeetingTable.COLUMN_ID + " ASC";

        List<NextMeeting> allFC = new ArrayList<>();
        try {
            c = db.query(
                    NextMeetingTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );
            while (c.moveToNext()) {
                NextMeeting nC = new NextMeeting();
                allFC.add(nC.HydrateForAppointment(c));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return allFC;
    }


}
