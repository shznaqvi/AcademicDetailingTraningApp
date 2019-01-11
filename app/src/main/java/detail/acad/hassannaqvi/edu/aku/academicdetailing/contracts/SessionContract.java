package detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts;

import android.provider.BaseColumns;

public class SessionContract {

    private String session = "";
    private String module = "";
    private String sessionTime = "";
    private int slideNumber;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    public int getSlideNumber() {
        return slideNumber;
    }

    public void setSlideNumber(int slideNumber) {
        this.slideNumber = slideNumber;
    }

    public abstract class SessionTable implements BaseColumns{

        public static final String TABLE_NAME = "sessionsTable";
        public static final String COLUMN_SESSION = "session";
        public static final String COLUMN_MODULE = "module";
        public static final String COLUMN_SESSION_TIME = "session_time";
        public static final String COLUMN_SLIDE_NUMBER = "slide_number";

    }
}
