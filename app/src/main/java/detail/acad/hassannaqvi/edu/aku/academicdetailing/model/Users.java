package detail.acad.hassannaqvi.edu.aku.academicdetailing.model;

import static detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp._EMPTY_;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.TableContracts.UsersTable;

/**
 * Created by hassan.naqvi on 11/30/2016.
 */

public class Users {


    private static final String TAG = "Users_CONTRACT";
    Long _ID;
    String userName   = _EMPTY_;
    String password   = _EMPTY_;
    String fullname   = _EMPTY_;
    String designation= _EMPTY_;
    String enabled    = _EMPTY_;
    String pwdExpiry  = _EMPTY_;
    String dist_id    = _EMPTY_;
    String newUser    = _EMPTY_;

    public Users() {
        // Default Constructor
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPwdExpiry() {
        return pwdExpiry;
    }

    public void setPwdExpiry(String pwdExpiry) {
        this.pwdExpiry = pwdExpiry;
    }

    public String getDist_id() {
        return dist_id;
    }

    public void setDist_id(String dist_id) {
        this.dist_id = dist_id;
    }

    public String getNewUser() {
        return newUser;
    }

    public void setNewUser(String newUser) {
        this.newUser = newUser;
    }

    public Users sync(JSONObject jsonObject) throws JSONException {
        //this._ID= jsonObject.getLong(UsersTable._ID);
        userName = jsonObject.getString(UsersTable.COLUMN_USERNAME);
        password = jsonObject.getString(UsersTable.COLUMN_PASSWORD);
        fullname = jsonObject.getString(UsersTable.COLUMN_FULLNAME);
        designation = jsonObject.getString(UsersTable.COLUMN_DESIGNATION);
        newUser = jsonObject.getString(UsersTable.COLUMN_ISNEW_USER);
        pwdExpiry = jsonObject.getString(UsersTable.COLUMN_PWD_EXPIRY);
        enabled = jsonObject.getString(UsersTable.COLUMN_ENABLED);
        dist_id = jsonObject.getString(UsersTable.COLUMN_DIST_ID);

        return this;

    }

    public Users hydrate(Cursor cursor) {
        _ID = cursor.getLong(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_ID));
        userName = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_USERNAME));
        password = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_PASSWORD));
        fullname = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_FULLNAME));
        designation = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_DESIGNATION));
        enabled = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_ENABLED));
        pwdExpiry = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_PWD_EXPIRY));
        newUser = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_ISNEW_USER));
        dist_id = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_DIST_ID));

        return this;

    }


}
