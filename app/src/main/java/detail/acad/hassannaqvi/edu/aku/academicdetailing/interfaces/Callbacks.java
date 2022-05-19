package detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Forms;

public interface Callbacks {


    void loadInfoFragment();
    void loadModuleFragment(Forms fc);
    void loadDatabaseManager();
    void uploadDataToServer();
    void downloadData();
    void loadInfo();
    void loadScheduleFragment();
    void uploadAppointment();
}
