package detail.acad.hassannaqvi.edu.aku.academicdetailing.interfaces;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;

public interface Callbacks {


    void loadInfoFragment();
    void loadModuleFragment(FormsContract fc);
    void loadDatabaseManager();
    void uploadDataToServer();
    void downloadData();
    void loadInfo();
    void loadScheduleFragment();
    void uploadAppointment();
}
