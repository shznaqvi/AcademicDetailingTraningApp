package detail.acad.hassannaqvi.edu.aku.academicdetailing.fragments;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.adapters.SlidingImageAdapter;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.CONSTANTS;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.DatabaseHelper;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.core.MainApp;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.databinding.FragmentModuleBinding;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Forms;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.MainActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Utils;



public class ModuleFragment extends Fragment {

    View view;
    FragmentModuleBinding bi;
    boolean isChildClicked = true;
    boolean isMaternalClicked = true;
    boolean isChildSubClicked = false;
    boolean isNewBornClicked = true;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    String districtName;
    boolean isAdmin;
    SlidingImageAdapter imageAdapter;
    Forms fc;
    DownloadManager downloadManager;
    Long refID;
    String[] videos;
    TextView progressText;
    ProgressBar progressBar;
    int finalProgress = 0;
    AlertDialog dialog;
    Data.SubMenu moduleToStart;
    boolean fileAlreadyExist = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bi = DataBindingUtil.inflate(inflater, R.layout.fragment_module, container, false);
        view = bi.getRoot();
        districtName = getArguments().getString("district_name");
        isAdmin = getArguments().getBoolean("isAdmin");
        fc = getArguments().getParcelable("forms");
        setupModules();
        initSlider();

        MainApp.hideKeyboard(getActivity());


        setOnClickListener();

        return view;
    }

    private void initSlider() {

        imageAdapter = new SlidingImageAdapter(getContext(), Data.mainSlides);
        bi.pager.setAdapter(imageAdapter);
        bi.indicator.setViewPager(bi.pager);


        NUM_PAGES = Data.mainSlides.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                bi.pager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        bi.indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    private void setupModules() {
        if (!isAdmin && !MainApp.userName.equals("test1234")) {
            switch (MainApp.districtCode) {
                case 113:
                case 123:
                case 432:
                case 234:
                    bi.newBornHealth.setVisibility(View.GONE);
                    bi.maternalHealth.setVisibility(View.GONE);
                    break;
                case 252:
                    bi.childHealth.setVisibility(View.GONE);
                    bi.newBornHealth.setVisibility(View.GONE);
                    break;
                case 434:
                    bi.maternalHealth.setVisibility(View.GONE);
                    bi.childHealth.setVisibility(View.GONE);
                    break;
                case 211:
                case 414:
                    bi.childHealth.setVisibility(View.GONE);
                    break;
            }
        }

    }

    private void setOnClickListener() {


        bi.childHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isChildClicked) {
                    showChildModule();
                    isChildClicked = false;
                } else {
                    removeChildModule();
                }

            }
        });

        bi.maternalHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isMaternalClicked) {
                    showMaternalModule();
                    isMaternalClicked = false;
                } else {
                    removeMaternalModule();
                }

            }
        });

        bi.newBornHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNewBornClicked) {
                    showNewBornModule();
                    isNewBornClicked = false;
                } else {
                    removeNBModule();
                }

            }
        });
    }

    private void showNewBornModule() {


        removeMaternalModule();
        removeChildModule();
        openModuleHandler(getContext(), bi.nbornModule, 2);

    }


    private void showMaternalModule() {


        removeChildModule();
        removeNBModule();
        openModuleHandler(getContext(), bi.maternalModule, 1);

    }

    private void showChildModule() {
        removeMaternalModule();
        removeNBModule();
        openModuleHandler(getContext(), bi.childModule, 0);

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {

                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(sharedPrefDownload.getL  5 7nong("refID", 0));

                downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Cursor cursor = downloadManager.query(query);
                if (cursor.moveToFirst()) {
                    int colIndex = cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(colIndex)) {

                        finalProgress++;
                        progressText.setText(finalProgress + "/" + moduleToStart.getVideosName().length);
                        if (finalProgress == moduleToStart.getVideosName().length) {
                            dialog.dismiss();
                            Utils.showPreDialogue(getActivity(), moduleToStart, fc);
                        }
                        ActivityManager am = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
                        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);

                        if (taskInfo.get(0).topActivity.getClassName().equals(MainActivity.class.getName())) {

                        }
                    }
                }
            }
        }
    };

    private void showModules(final Context mContext, final LinearLayout llModule, int type) {

        Data.fillingMenus(type);
        llModule.removeAllViews();
        for (final String key : Data.newMenuModule.keySet()) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.single_module_item, null);
            TextView moduleNameTxt = v.findViewById(R.id.modNSpinner);
            final LinearLayout subModule = v.findViewById(R.id.subModule);
            moduleNameTxt.setText(key);
            llModule.addView(v);
            v.animate().translationY(v.getHeight());
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Data.SubMenu[] submenu = Data.newMenuModule.get(key);
                    removeSubGroups(llModule);
                    showSubMenus(mContext, subModule, submenu);
                }
            });
        }

    }

    private void showVideodownloadDialoge(String[] videosName, String moduleName) {

        finalProgress = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.downloading_dialog_layout, null);
        progressText = view.findViewById(R.id.progressTextView);
        progressText.setText("0/" + videosName.length);
        progressBar = view.findViewById(R.id.progressBar);
        builder.setView(view);
        progressBar.setMax(100);
        dialog = builder.create();
        dialog.show();

    }

    private boolean downloadVideos(String[] videosName, String moduleName) {

        boolean fileExist = true;

        String Directrory = Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME;
        File folder = new File(Directrory);
        boolean success = true;
        if (!folder.exists())
            success = folder.mkdirs();
        if (!success) return false;

        folder = new File(Directrory + File.separator + moduleName);
        if (!folder.exists())
            success = folder.mkdirs();
        if (!success) return false;

        for (int i = 0; i < videosName.length; i++) {
            String fileName = videosName[i];
            File file = new File(folder.getPath(), fileName);
//            fileAlreadyExist = file.exists();
            if (file.exists()) {
                continue;
            } else {
                NetworkInfo networkInfo = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(CONSTANTS.Video_URL + fileName + ".mp4");
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    request.setDestinationInExternalPublicDir(DatabaseHelper.PROJECT_NAME + File.separator + moduleName, fileName)
//                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setTitle("Downloading " + fileName);
                    refID = downloadManager.enqueue(request);
                    fileExist = false;

                } else {
                    Toast.makeText(getContext(), "Internet connectivity issue", Toast.LENGTH_SHORT).show();
                }
            }

        }

        getActivity().registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        return fileExist;

    }

    private void showSubMenus(Context mContext, LinearLayout llSubModule, Data.SubMenu[] subMenuItem) {

        if (subMenuItem.length == 1) {
            if (videoDownload(mContext, subMenuItem[0]))
                Utils.showPreDialogue(getActivity(), subMenuItem[0], fc);
            return;
        }

        llSubModule.removeAllViews();
        for (final Data.SubMenu subMenu : subMenuItem) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.single_sub_module_item, null);
            TextView moduleName = v.findViewById(R.id.subModuleName);
            moduleName.setText(subMenu.getName());
            llSubModule.addView(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoDownload(mContext, subMenu))
                        Utils.showPreDialogue(getActivity(), subMenu, fc);

                }
            });
        }

    }

    private boolean videoDownload(Context mContext, Data.SubMenu subMenu) {
        boolean flag = true;
        for (String videoName : subMenu.getVideosName()) {
            if (!MainApp.checkVideoExist(MainApp.getModulePosition(subMenu.getModuleName().toUpperCase()), videoName)) {
                flag = false;
                break;
            }
        }

        if (!flag) Toast.makeText(mContext,
                "Please Download all videos for this section!!",
                Toast.LENGTH_SHORT).show();

        return flag;
    }

    private void removeSubGroups(LinearLayout llModule) {
        for (byte i = 0; i < llModule.getChildCount(); i++) {
            View child = llModule.getChildAt(i);
            if (child instanceof LinearLayout) {
                for (byte j = 1; j < ((LinearLayout) child).getChildCount(); j++) {
                    View subChild = ((LinearLayout) child).getChildAt(j);
                    ((LinearLayout) subChild).removeAllViews();
                }

            }
        }
    }

    private void openModuleHandler(Context mContext, final ViewGroup view, final int type) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showModules(mContext, (LinearLayout) view, type);
            }
        }, 300);
    }

    private void removeChildModule() {
        bi.childModule.animate().translationY(0);
        bi.childModule.removeAllViews();
        isChildClicked = true;
    }

    private void removeMaternalModule() {
        bi.maternalModule.animate().translationY(0);
        bi.maternalModule.removeAllViews();
        isMaternalClicked = true;
    }

    private void removeNBModule() {
        bi.nbornModule.animate().translationY(0);
        bi.nbornModule.removeAllViews();
        isNewBornClicked = true;
    }


}





