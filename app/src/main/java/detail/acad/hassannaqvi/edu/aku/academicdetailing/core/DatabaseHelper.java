package detail.acad.hassannaqvi.edu.aku.academicdetailing.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.DistrictsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.DistrictsContract.DistrictTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract.FormsTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.HealthFacContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.HealthFacContract.singleHF;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.NextMeetingContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.NextMeetingContract.NMCTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.SessionContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.SessionContract.SessionTable;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.UsersContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.UsersContract.UsersTable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_USERS = "CREATE TABLE " + UsersTable.TABLE_NAME + "("
            + UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + UsersTable.DISTRICT_CODE + " LONG,"
            + UsersTable.ROW_USERNAME + " TEXT,"
            + UsersTable.ROW_PASSWORD + " TEXT );";
    public static final String DATABASE_NAME = "dr_reg.db";
    public static final String DB_NAME = DATABASE_NAME.replace(".", "_copy.");
    public static final String PROJECT_NAME = "DR-REGISTRATION-FORM";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_FORMS = "CREATE TABLE "
            + FormsTable.TABLE_NAME + "("
            + FormsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FormsTable.COLUMN_PROJECTNAME + " TEXT," +
            FormsTable.COLUMN_loggin_TIME + " TEXT, " +
            FormsTable.COLUMN_SURVEYTYPE + " TEXT," +
            FormsTable.COLUMN_MODULE + " TEXT, " +
            FormsTable.COLUMN_SESSION + " TEXT, " +
            FormsTable.COLUMN_UID + " TEXT," +
            FormsTable.COLUMN_FORMDATE + " TEXT," +
            FormsTable.COLUMN_USER + " TEXT," +
            FormsTable.COLUMN_ISTATUS + " TEXT," +
            FormsTable.COLUMN_ISTATUS88X + " TEXT," +
            FormsTable.COLUMN_ENDINGDATETIME + " TEXT," +
            FormsTable.COLUMN_GPSLAT + " TEXT," +
            FormsTable.COLUMN_GPSLNG + " TEXT," +
            FormsTable.COLUMN_GPSDT + " TEXT," +
            FormsTable.COLUMN_GPSACC + " TEXT," +
            FormsTable.COLUMN_GPSELEV + " TEXT," +
            FormsTable.COLUMN_GPSTIME + " TEXT," +
            FormsTable.COLUMN_DEVICEID + " TEXT," +
            FormsTable.COLUMN_DEVICETAGID + " TEXT," +
            FormsTable.COLUMN_SYNCED + " TEXT," +
            FormsTable.COLUMN_SYNCED_DATE + " TEXT," +
            FormsTable.COLUMN_APPVERSION + " TEXT," +
            FormsTable.COLUMN_DIST_ID + " TEXT," +
            FormsTable.COLUMN_HFACILITY_NAME + " TEXT," +
            FormsTable.COLUMN_PROVIDER_NAME + " TEXT," +
            FormsTable.COLUMN_PROVIDER_ID + " TEXT ," +
            FormsTable.COLUMN_PRETEST_START_TIME + " TEXT ," +
            FormsTable.COLUMN_PRETEST_END_TIME + " TEXT ," +
            FormsTable.COLUMN_POSTTEST_START_TIME + " TEXT ," +
            FormsTable.COLUMN_POSTTEST_END_TIME + " TEXT ," +
            FormsTable.COLUMN_SESSION_START_TIME + " TEXT ," +
            FormsTable.COLUMN_SESSION_END_TIME + " TEXT, " +
            FormsTable.COLUMN_PRE_TEST + " TEXT ," +
            FormsTable.COLUMN_POST_TEST + " TEXT"
            + " ); ";

    private static final String SQL_CREATE_SESSION_TABLE = " CREATE TABLE " + SessionTable.TABLE_NAME
            + " ( " + SessionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SessionTable.COLUMN_FORMDATE + " TEXT, "
            + SessionTable.COLUMN_DEVICEID + " TEXT, "
            + SessionTable.COLUMN_SLIDE_NUMBER + " INTEGER,"
            + SessionTable.COLUMN_MODULE + " TEXT," + SessionTable.COLUMN_SESSION
            + " TEXT,"
            + SessionTable.COLUMN_SESSION_TIME + " TEXT,"
            + SessionTable.COLUMN_SYNCED + " TEXT,"
            + SessionTable.COLUMN_SYNCED_DATE + " TEXT" + ");";

    private static final String SQL_CREATE_DISTRICT_TABLE = " CREATE TABLE " + DistrictTable.TABLE_NAME
            + " ( " + DistrictTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + DistrictTable.DISTRICT_CODE + " Long," +
            DistrictTable.DISTRICT_NAME + " TEXT" + ");";

    private static final String SQL_CREATE_HF_TABLE = " CREATE TABLE " + singleHF.TABLE_NAME
            + " ( " + singleHF._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + singleHF.COLUMN_HF_DHIS + " LONG, "
            + singleHF.COLUMN_HF_DIST_CODE + " LONG, "
            + singleHF.COLUMN_HF_TEHSIL_NAME + " TEXT, "
            + singleHF.COLUMN_HF_UC_NAME + " TEXT, "
            + singleHF.COLUMN_HF_NAME + " TEXT, "
            + singleHF.COLUMN_HF_NAME_GOVT + " TEXT, "
            + singleHF.COLUMN_HF_UEN_CODE + " LONG " + ");";


    private static final String SQL_CREATE_NMS = "CREATE TABLE " + NMCTable.TABLE_NAME + "("
            + NMCTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NMCTable.COLUMN_DEVICEID + " TEXT, "
            + NMCTable.COLUMN_LAT + " TEXT, "
            + NMCTable.COLUMN_FORMDATE + " TEXT, "
            + NMCTable.COLUMN_LNG + " TEXT, "
            + NMCTable.COLUMN_GPSTIME + " TEXT, "
            + NMCTable.COLUMN_BTYPE + " TEXT, "
            + NMCTable.COLUMN_BOOK_DATE + " TEXT, "
            + NMCTable.COLUMN_BOOKBY + " TEXT, "
            + NMCTable.COLUMN_DOCTOR_NAME + " TEXT, "
            + NMCTable.COLUMN_DATE + " TEXT, "
            + NMCTable.COLUMN_TIME + " TEXT, "
            + NMCTable.COLUMN_MOD + " TEXT, "
            + NMCTable.COLUMN_SUBMOD + " TEXT, "
            + NMCTable.COLUMN_SESSION + " TEXT, "
            + NMCTable.COLUMN_SYNCED + " TEXT, "
            + NMCTable.COLUMN_SYNCED_DATE + " TEXT " + ");";

    private static final String SQL_DELETE_SESSION = "DROP TABLE IF EXISTS " + SessionTable.TABLE_NAME;
    private static final String SQL_DELETE_DISTRICTS = "DROP TABLE IF EXISTS " + DistrictTable.TABLE_NAME;
    private static final String SQL_DELETE_NMS = "DROP TABLE IF EXISTS " + NMCTable.TABLE_NAME;
    private static final String SQL_DELETE_HF = "DROP TABLE IF EXISTS " + singleHF.TABLE_NAME;
    private static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;
    private static final String SQL_DELETE_FORMS =
            "DROP TABLE IF EXISTS " + FormsTable.TABLE_NAME;


    private final String TAG = "DatabaseHelper";

    public String spDateT = new SimpleDateFormat("dd-MM-yy").format(new Date().getTime());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_FORMS);
        db.execSQL(SQL_CREATE_SESSION_TABLE);
        db.execSQL(SQL_CREATE_NMS);
        db.execSQL(SQL_CREATE_DISTRICT_TABLE);
        db.execSQL(SQL_CREATE_HF_TABLE);
        /*db.execSQL(SQL_CREATE_TEHSILS);
        db.execSQL(SQL_CREATE_UCS);
        db.execSQL(SQL_CREATE_LHWS);*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(SQL_DELETE_USERS);
        db.execSQL(SQL_DELETE_FORMS);
        db.execSQL(SQL_DELETE_DISTRICTS);
        db.execSQL(SQL_DELETE_SESSION);
        db.execSQL(SQL_DELETE_NMS);
        db.execSQL(SQL_DELETE_HF);
    }

//    public List<HealthFacContract> getHFData(HealthFacContract.ColumnsClass... columnsClass) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = null;
//        String[] columns = {
//                singleHF.COLUMN_ID,
//                singleHF.COLUMN_HF_NAME,
//                singleHF.COLUMN_HF_TYPE,
//                singleHF.COLUMN_HF_DISTRICT_NAME,
//                singleHF.COLUMN_HF_TEHSIL_NAME,
//                singleHF.COLUMN_HF_PROVINCE_NAME,
//                singleHF.COLUMN_HF_UC_NAME,
//                singleHF.COLUMN_HF_UEN_CODE,
//        };
//
//        String whereClause = null;
//        String[] whereArgs = null;
//
//        if (columnsClass.length > 0) {
//
//            whereClause = "";
//            whereArgs = new String[columnsClass.length];
//
//            for (byte i = 0; i < columnsClass.length; i++) {
//                whereClause += columnsClass[i].getColumnName() + " =?";
//                whereArgs[i] = columnsClass[i].getColumnClause();
//
//                if (columnsClass.length - 1 != i) {
//                    whereClause += " AND ";
//                }
//
//            }
//
//        }
//
//        String groupBy = null;
//        String having = null;
//
//        String orderBy = singleHF.COLUMN_HF_NAME + " ASC";
//
//        List<HealthFacContract> allDC = new ArrayList<>();
//
//        try {
//            c = db.query(
//                    singleHF.TABLE_NAME,  // The table to query
//                    columns,                   // The columns to return
//                    whereClause,               // The columns for the WHERE clause
//                    whereArgs,                 // The values for the WHERE clause
//                    groupBy,                   // don't group the rows
//                    having,                    // don't filter by row groups
//                    orderBy                    // The sort order
//            );
//
//            while (c.moveToNext()) {
//                HealthFacContract dc = new HealthFacContract();
//                allDC.add(dc.HydrateHF(c));
//            }
//        } finally {
//            if (c != null) {
//                c.close();
//            }
//            if (db != null) {
//                db.close();
//            }
//        }
//        return allDC;
//    }


    public ArrayList<UsersContract> getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<UsersContract> userList = null;
        try {
            userList = new ArrayList<UsersContract>();
            String QUERY = "SELECT * FROM " + UsersTable.TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            int num = cursor.getCount();
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    UsersContract user = new UsersContract();
                    user.setId(cursor.getInt(0));
                    user.setUserName(cursor.getString(1));
                    user.setPassword(cursor.getString(2));
                    userList.add(user);
                }
            }
            db.close();
        } catch (Exception e) {
        }
        return userList;
    }

    public Collection<FormsContract> getUnsyncedForms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                FormsTable.COLUMN_PROJECTNAME,
                FormsTable.COLUMN_MODULE,
                FormsTable._ID,
                FormsTable.COLUMN_FORMDATE,
                FormsTable.COLUMN_SESSION,
                FormsTable.COLUMN_ISTATUS,
                FormsTable.COLUMN_ISTATUS88X,
                FormsTable.COLUMN_ENDINGDATETIME,
                FormsTable.COLUMN_GPSLAT,
                FormsTable.COLUMN_GPSLNG,
                FormsTable.COLUMN_GPSTIME,
                FormsTable.COLUMN_GPSACC,
                FormsTable.COLUMN_DEVICEID,
                FormsTable.COLUMN_APPVERSION,
                FormsTable.COLUMN_DIST_ID,
                FormsTable.COLUMN_loggin_TIME,
                FormsTable.COLUMN_HFACILITY_NAME,
                FormsTable.COLUMN_PROVIDER_NAME,
                FormsTable.COLUMN_PROVIDER_ID,
                FormsTable.COLUMN_PRETEST_START_TIME,
                FormsTable.COLUMN_PRETEST_END_TIME,
                FormsTable.COLUMN_POSTTEST_START_TIME,
                FormsTable.COLUMN_POSTTEST_END_TIME,
                FormsTable.COLUMN_SESSION_START_TIME,
                FormsTable.COLUMN_SESSION_END_TIME,
                FormsTable.COLUMN_PRE_TEST,
                FormsTable.COLUMN_POST_TEST,
                FormsTable.COLUMN_SYNCED,
                FormsTable.COLUMN_SYNCED_DATE

        };
        String whereClause = FormsTable.COLUMN_SYNCED + " is null OR " + FormsTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                FormsTable._ID + " ASC";

        Collection<FormsContract> allFC = new ArrayList<FormsContract>();
        try {
            c = db.query(
                    FormsTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    FormsContract fc = new FormsContract();
                    allFC.add(fc.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    public Collection<SessionContract> getUnsyncedSessions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                SessionTable.COLUMN_SESSION,
                SessionTable._ID,
                SessionTable.COLUMN_MODULE,
                SessionTable.COLUMN_SESSION_TIME,
                SessionTable.COLUMN_SLIDE_NUMBER,
                SessionTable.COLUMN_SYNCED,
                SessionTable.COLUMN_SYNCED_DATE,
                SessionTable.COLUMN_DEVICEID,
                SessionTable.COLUMN_FORMDATE

        };
        String whereClause = SessionTable.COLUMN_SYNCED + " is null OR " + SessionTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                SessionTable._ID + " ASC";

        Collection<SessionContract> allSc = new ArrayList<SessionContract>();
        try {
            c = db.query(
                    SessionTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    SessionContract sC = new SessionContract();
                    allSc.add(sC.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allSc;
    }

    public Collection<NextMeetingContract> getUnsyncedNextMeetingForm() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = null;
        String[] columns = {
                NMCTable.COLUMN_DOCTOR_NAME,
                NMCTable.COLUMN_DATE,
                NMCTable.COLUMN_TIME,
                NMCTable.COLUMN_MOD,
                NMCTable.COLUMN_SUBMOD,
                NMCTable.COLUMN_SESSION,
                NMCTable.COLUMN_LAT,
                NMCTable.COLUMN_LNG,
                NMCTable.COLUMN_BOOK_DATE,
                NMCTable.COLUMN_BOOKBY,
                NMCTable.COLUMN_GPSTIME,
                NMCTable.COLUMN_BTYPE,
                NMCTable._ID,
                NMCTable.COLUMN_SYNCED,
                NMCTable.COLUMN_SYNCED_DATE,
                NMCTable.COLUMN_DEVICEID,
                NMCTable.COLUMN_FORMDATE

        };
        String whereClause = NMCTable.COLUMN_SYNCED + " is null OR " + NMCTable.COLUMN_SYNCED + " = '' ";
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;

        String orderBy =
                NMCTable._ID + " ASC";

        Collection<NextMeetingContract> allSc = new ArrayList<NextMeetingContract>();
        try {
            c = db.query(
                    NMCTable.TABLE_NAME,  // The table to query
                    columns,                   // The columns to return
                    whereClause,               // The columns for the WHERE clause
                    whereArgs,                 // The values for the WHERE clause
                    groupBy,                   // don't group the rows
                    having,                    // don't filter by row groups
                    orderBy                    // The sort order
            );

            if (c.moveToFirst())
                do {
                    NextMeetingContract nC = new NextMeetingContract();
                    allSc.add(nC.Hydrate(c));
                } while (c.moveToNext());


        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allSc;
    }

    public void syncUsers(JSONArray userlist) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + UsersTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'users'");

        try {
            JSONArray jsonArray = userlist;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                UsersContract usersContract = new UsersContract();
                usersContract.Sync(jsonObjectUser);

                ContentValues values = new ContentValues();

                values.put(UsersTable.ROW_USERNAME, usersContract.getUserName());
                values.put(UsersTable.ROW_PASSWORD, usersContract.getPassword());
                values.put(UsersTable.DISTRICT_CODE, usersContract.getDICTRICT_CODE());
                db.insert(UsersTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        }
    }


    public void syncHF(JSONArray hfList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + singleHF.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'health_fc'");    //TODO you have to add table name manually in order to reset primary key
        try {
            JSONArray jsonArray = hfList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);

                HealthFacContract healthFacContract = new HealthFacContract();
                healthFacContract.Sync(jsonObjectUser);

                ContentValues values = new ContentValues();

                values.put(singleHF.COLUMN_HF_DHIS, healthFacContract.getHf_dhis());
                values.put(singleHF.COLUMN_HF_DIST_CODE, healthFacContract.getHf_district_code());
                values.put(singleHF.COLUMN_HF_TEHSIL_NAME, healthFacContract.getHf_tehsil());
                values.put(singleHF.COLUMN_HF_UC_NAME, healthFacContract.getHf_uc());
                values.put(singleHF.COLUMN_HF_NAME, healthFacContract.getHf_name());
                values.put(singleHF.COLUMN_HF_NAME_GOVT, healthFacContract.getHf_name_govt());
                values.put(singleHF.COLUMN_HF_UEN_CODE, healthFacContract.getHf_uen_code());
                long count = db.insert(singleHF.TABLE_NAME, null, values);
                Log.d(TAG, "syncHF: count " + count);
            }
            db.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void syncDistricts(JSONArray districList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + DistrictTable.TABLE_NAME);
        db.execSQL(" DELETE FROM sqlite_sequence where name = 'districts'");

        try {
            JSONArray jsonArray = districList;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                DistrictsContract dc = new DistrictsContract();
                dc.Sync(jsonObject);
                ContentValues values = new ContentValues();
                values.put(DistrictTable.DISTRICT_CODE, dc.getDICTRICT_CODE());
                values.put(DistrictTable.DISTRICT_NAME, dc.getDistrict_name());
                db.insert(DistrictTable.TABLE_NAME, null, values);
            }
            db.close();

        } catch (Exception e) {
        }
    }


    public boolean Login(String username, String password) throws SQLException {

        SQLiteDatabase db = this.getReadableDatabase();
// New value for one column
        String[] columns = {
                UsersTable._ID,
                UsersTable.DISTRICT_CODE
        };

// Which row to update, based on the ID
        String selection = UsersTable.ROW_USERNAME + " = ?" + " AND " + UsersTable.ROW_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(UsersTable.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        if (cursor.moveToFirst()) {
            MainApp.districtCode = cursor.getInt(cursor.getColumnIndex(UsersTable.DISTRICT_CODE));
            cursor.moveToNext();
        }

        int count = cursor.getCount();

        cursor.close();
        db.close();
        return count > 0;
    }


    public DistrictsContract getDistrict(int distCode) {

        SQLiteDatabase db = this.getReadableDatabase();
        // New value for one column
        String[] columns = {
                DistrictTable.DISTRICT_NAME,
                DistrictTable.DISTRICT_CODE,
        };

        // Which row to update, based on the ID
        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = null;
        DistrictsContract district = null;

        if (distCode > 0) {
            selection = DistrictTable.DISTRICT_CODE + " = ?";
            selectionArgs = new String[]{String.valueOf(distCode)};
            cursor = db.query(DistrictTable.TABLE_NAME, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order


            if (cursor.moveToFirst()) {
                district = new DistrictsContract().Hydrate(cursor);
                cursor.moveToNext();
            }

            cursor.close();
        }


        db.close();
        return district;
    }

    public List<FormsContract> getFormsByDSS(String dssID) {
        List<FormsContract> formList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + FormsTable.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable._ID)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                formList.add(fc.Hydrate(c));
            } while (c.moveToNext());
        }

        // return contact list
        return formList;
    }

    public Long addForm(FormsContract fc) {

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PROJECTNAME, fc.getProjectName());
        values.put(FormsTable.COLUMN_SURVEYTYPE, fc.getSurveyType());
        values.put(FormsTable.COLUMN_UID, fc.getUID());
//        values.put(FormsTable._ID, fc.get_ID());
        values.put(FormsTable.COLUMN_FORMDATE, fc.getFormDate());
        values.put(FormsTable.COLUMN_USER, fc.getUser());
        values.put(FormsTable.COLUMN_ISTATUS, fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88X, fc.getIstatus88x());
//        values.put(FormsTable.COLUMN_SA, fc.getsA());
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
        values.put(FormsTable.COLUMN_HFACILITY_NAME, fc.getHealthFacilityName());
        values.put(FormsTable.COLUMN_PROVIDER_NAME, fc.getProviderName());
        values.put(FormsTable.COLUMN_PROVIDER_ID, fc.getProviderID());
        values.put(FormsTable.COLUMN_PRETEST_START_TIME, fc.getPreTestStartTime());
        values.put(FormsTable.COLUMN_PRETEST_END_TIME, fc.getPreTestEndTime());
        values.put(FormsTable.COLUMN_POSTTEST_START_TIME, fc.getPostTestEndTime());


        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FormsTable.TABLE_NAME,
                FormsTable.COLUMN_NAME_NULLABLE,
                values);
        return newRowId;
    }

    public void updateSyncedForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

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

    public void updateSyncedSessionForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

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

    public void updateSyncedNMSForms(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(NMCTable.COLUMN_SYNCED, true);
        values.put(NMCTable.COLUMN_SYNCED_DATE, new Date().toString());

// Which row to update, based on the title
        String where = NMCTable._ID + " = ?";
        String[] whereArgs = {id};

        int count = db.update(
                NMCTable.TABLE_NAME,
                values,
                where,
                whereArgs);
    }

    public int updateFormID() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_UID, MainApp.fc.getUID());

// Which row to update, based on the ID
        String selection = FormsTable.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public Collection<FormsContract> getTodayForms() {
        SQLiteDatabase db = this.getReadableDatabase();
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

        Collection<FormsContract> allFC = new ArrayList<>();
        try {
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
                FormsContract fc = new FormsContract();
                fc.set_ID(c.getString(c.getColumnIndex(FormsTable._ID)));
                fc.setFormDate(c.getString(c.getColumnIndex(FormsTable.COLUMN_FORMDATE)));
                fc.setIstatus(c.getString(c.getColumnIndex(FormsTable.COLUMN_ISTATUS)));
                fc.setSynced(c.getString(c.getColumnIndex(FormsTable.COLUMN_SYNCED)));
                allFC.add(fc);
            }
        } finally {
            if (c != null) {
                c.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return allFC;
    }

    // ANDROID DATABASE MANAGER
    public ArrayList<Cursor> getData(String Query) {
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_ISTATUS, MainApp.fc.getIstatus());
        values.put(FormsTable.COLUMN_ISTATUS88X, MainApp.fc.getIstatus88x());
        values.put(FormsTable.COLUMN_ENDINGDATETIME, MainApp.fc.getEndingdatetime());
        values.put(FormsTable.COLUMN_SESSION_START_TIME, MainApp.fc.getSessionStartTime());
        values.put(FormsTable.COLUMN_SESSION_END_TIME, MainApp.fc.getSessionEndTime());
        values.put(FormsTable.COLUMN_SESSION, MainApp.fc.getSession());
        values.put(FormsTable.COLUMN_MODULE, MainApp.fc.getModule());


// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatePreTest() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_PRETEST_START_TIME, MainApp.fc.getPreTestStartTime());
        values.put(FormsTable.COLUMN_PRETEST_END_TIME, MainApp.fc.getPreTestEndTime());
        values.put(FormsTable.COLUMN_PRE_TEST, MainApp.fc.getPre_test());


// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public int updatePostTest() {
        SQLiteDatabase db = this.getReadableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(FormsTable.COLUMN_POSTTEST_START_TIME, MainApp.fc.getPostTestStartTime());
        values.put(FormsTable.COLUMN_POSTTEST_END_TIME, MainApp.fc.getPostTestEndTime());
        values.put(FormsTable.COLUMN_POST_TEST, MainApp.fc.getPost_test());

// Which row to update, based on the ID
        String selection = FormsTable._ID + " =? ";
        String[] selectionArgs = {String.valueOf(MainApp.fc.get_ID())};

        int count = db.update(FormsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public void addSessionData(SessionContract sc) {

        long count;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SessionTable.COLUMN_MODULE, sc.getModule());
        values.put(SessionTable.COLUMN_SESSION, sc.getSession());
        values.put(SessionTable.COLUMN_SESSION_TIME, sc.getSessionTime());
        values.put(SessionTable.COLUMN_SLIDE_NUMBER, sc.getSlideNumber());
        values.put(SessionTable.COLUMN_DEVICEID, sc.getDeviceid());
        values.put(SessionTable.COLUMN_FORMDATE, sc.getFormdate());
        count = db.insert(SessionTable.TABLE_NAME, null, values);

        Log.d(TAG, "addSessionData: count " + count);
    }

    public String getProviderName() {

        SQLiteDatabase db = this.getReadableDatabase();

        String providerName;
        Cursor cursor = db.query(FormsTable.TABLE_NAME, new String[]{FormsTable.COLUMN_PROVIDER_NAME},
                FormsTable._ID + "=?", new String[]{String.valueOf(MainApp.fc.get_ID())},
                null, null, null);
        cursor.moveToFirst();
        do {
            providerName = cursor.getString(cursor.getColumnIndex(FormsTable.COLUMN_PROVIDER_NAME));
        } while (cursor.moveToNext());


        return providerName;

    }

    public void updateNMS() {

        SQLiteDatabase db = this.getWritableDatabase();

        long count;
// New value for one column
        ContentValues values = new ContentValues();
        values.put(NMCTable.COLUMN_DOCTOR_NAME, MainApp.nmc.getDoctorName());
        values.put(NMCTable.COLUMN_DATE, MainApp.nmc.getDate());
        values.put(NMCTable.COLUMN_TIME, MainApp.nmc.getTime());
        values.put(NMCTable.COLUMN_MOD, MainApp.nmc.getModule());
        values.put(NMCTable.COLUMN_SUBMOD, MainApp.nmc.getSubModule());
        values.put(NMCTable.COLUMN_SESSION, MainApp.nmc.getSession());
        values.put(NMCTable.COLUMN_BOOK_DATE, MainApp.nmc.getDoBooking());
        values.put(NMCTable.COLUMN_BOOKBY, MainApp.nmc.getBookBy());
        values.put(NMCTable.COLUMN_DEVICEID, MainApp.deviceId);
        values.put(NMCTable.COLUMN_LAT, MainApp.nmc.getLat());
        values.put(NMCTable.COLUMN_LNG, MainApp.nmc.getLng());
        values.put(NMCTable.COLUMN_BTYPE, MainApp.nmc.getBookingtype());
        values.put(NMCTable.COLUMN_GPSTIME, MainApp.nmc.getGpsTime());
        values.put(NMCTable.COLUMN_FORMDATE, MainApp.nmc.getFormdate());
        count = db.insert(NMCTable.TABLE_NAME, null, values);

        Log.d(TAG, "updateNMS: count " + count);
    }

    public interface DBConnection {
        String DB_NAME = "acad_detail";
    }

    public long getUsersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, UsersTable.TABLE_NAME);
        db.close();
        return count;
    }


}
