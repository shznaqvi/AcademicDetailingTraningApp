package detail.acad.hassannaqvi.edu.aku.academicdetailing.core;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import detail.acad.hassannaqvi.edu.aku.academicdetailing.R;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.DistrictsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.FormsContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.contracts.NextMeetingContract;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.model.Result;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.EndingActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.ui.ViewPagerActivity;
import detail.acad.hassannaqvi.edu.aku.academicdetailing.util.Data;

//uid, provider ID,user's ID for sessions table

public class MainApp extends Application {


    //    http://f38158/phpwebapi/uenad/api/
//    public static final String _IP = "43.245.131.159"; // Test PHP server
//    public static final String _IP = "vcoe1.aku.edu/"; // Test PHP server
    public static final String _IP = "f38158/"; // Test PHP server
    public static final Integer _PORT = 8080; // Port - with colon (:)
//    public static final String _HOST_URL = "https://" + MainApp._IP + "uen_ad/api/";
    public static final String _HOST_URL = "http://" + MainApp._IP + "uen_ad/api/";
    // public static final String TEST_URL = "http://" + MainApp._IP + ":" + MainApp._PORT + "/leapsup/api/";

    //    public static final String _UPDATE_URL = "http://" + MainApp._IP + ":" + MainApp._PORT + "/wfp_recruit_form/app/app-debug.apk";
    public static final String _UPDATE_URL = "https://" + MainApp._IP + "/uen_ad/app/";

    public static final Integer MONTHS_LIMIT = 11;
    public static final Integer DAYS_LIMIT = 29;
    //public static final long MILLISECONDS_IN_5YEAR = (MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR + MILLISECONDS_IN_YEAR);
    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    private static final int TWENTY_MINUTES = 1000 * 60 * 20;
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private static final long MILLIS_IN_SECOND = 1000;
    private static final long SECONDS_IN_MINUTE = 60;
    private static final long MINUTES_IN_HOUR = 60;
    private static final long HOURS_IN_DAY = 24;
    public static final long MILLISECONDS_IN_DAY = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY;
    private static final long DAYS_IN_YEAR = 365;
    public static final long MILLISECONDS_IN_8Days = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * 8;
    public static final long MILLISECONDS_IN_YEAR = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_YEAR;
    private static final long DAYS_IN_5_YEAR = 365 * 5;
    public static final long MILLISECONDS_IN_5Years = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_5_YEAR;
    private static final long DAYS_IN_MONTH = 30;
    public static final long MILLISECONDS_IN_MONTH = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_MONTH;
    private static final long DAYS_IN_9MONTH = 274;
    public static final long MILLISECONDS_IN_9MONTH = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_9MONTH;

    private static final long DAYS_IN_2_YEAR = 365 * 2;
    public static final long MILLISECONDS_IN_2Years = MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_2_YEAR;
    public static String deviceId;
    public static boolean isSlideStart = false;
    public static boolean isScheduleAppointment = false;
    public static boolean isMaternal = false;
    public static boolean isNBorn = false;
    public static boolean isChild = false;
    public static String[] loginMem;
    public static String versionName;
    public static String districtName = "";
    public static String providerName = "";
    public static int versionCode;
    public static int districtCode = 0;
    public static Boolean admin = false;
    public static String userName = "0000";
    public static FormsContract fc;
    public static Result post_result = new Result();
    public static Result pre_result = new Result();
    public static String IMEI;
    public static String logginTime;
    public static NextMeetingContract nmc;
    public static boolean isComplete = false;
    public static String type = "";
    public static int[] slides;
    public static String formsUID = "";
    public static DistrictsContract dContract;


    protected static LocationManager locationManager;

    public static String getTagName(Context mContext) {
        SharedPreferences sharedPref = mContext.getSharedPreferences("tagName", MODE_PRIVATE);
        return sharedPref.getString("tagName", null);
    }

    public static Calendar getCalendarDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(value);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static long ageInYear_MonthByDOB(String dateStr, char type) {
        Calendar cal = getCalendarDate(dateStr);
        long ageInYears;

        if (type == 'y')
            ageInYears = Calendar.getInstance().get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        else
            ageInYears = Calendar.getInstance().get(Calendar.MONTH) - cal.get(Calendar.MONTH);

        return ageInYears;
    }

    public static String getCurrentTime() {
        String currentTime = new SimpleDateFormat(" dd-MM-yyyy HH:mm:ss").format(new Date().getTime());
        return currentTime;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Function to merge multiple arrays in Java
    public static String[] mergeArrays(String[]... arrays) {
        List<String> list = new ArrayList<>();

        for (String[] array : arrays)
            Collections.addAll(list, array);

        return list.toArray(new String[0]);
    }

    public static boolean checkVideoExist(int position, String fName) {

        String Directrory = Environment.getExternalStorageDirectory() + File.separator + DatabaseHelper.PROJECT_NAME;
        File folder = new File(Directrory);
        if (!folder.exists()) return false;
        folder = new File(Directrory + File.separator + getModuleName(position));
        if (!folder.exists()) return false;
        File file = new File(folder.getPath(), fName);
        return file.exists();
    }

    public static String getModuleName(int position) {
        switch (position) {
            case 1:
                return "CHILDHEALTH";
            case 2:
                return "MATERNALHEALTH";
            case 3:
                return "NBORNHEALTH";
            default:
                return "";
        }
    }

    public static int getModulePosition(String module) {
        switch (module) {
            case "CHILDHEALTH":
                return 1;
            case "MATERNALHEALTH":
                return 2;
            case "NBORNHEALTH":
                return 3;
            default:
                return -1;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/JameelNooriNastaleeq.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
//        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/MBLateefi.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf

        deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


//         Requires Permission for GPS -- android.permission.ACCESS_FINE_LOCATION
//         Requires Additional permission for 5.0 -- android.hardware.location.gps
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                 requestPermission();
            } else {
                requestLocationUpdate();
            }
        } else {
            requestLocationUpdate();
        }

    }

    public void requestLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MINIMUM_TIME_BETWEEN_UPDATES,
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new GPSLocationListener() // Implement this class from code
        );
    }


    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;

            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else return isNewer && !isSignificantlyLessAccurate && isFromSameProvider;
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    protected void showCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
        }

    }

    public static void endActivity(final Context context, String message, final boolean status) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                Intent end_intent = new Intent(context, EndingActivity.class);
                                end_intent.putExtra("complete", status);
                                context.startActivity(end_intent);
                                ((Activity) context).finish();
                            }
                        });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public class GPSLocationListener implements LocationListener {
        public void onLocationChanged(Location location) {

            SharedPreferences sharedPref = getSharedPreferences("GPSCoordinates", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            String dt = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(sharedPref.getString("Time", "0"))).toString();

            Location bestLocation = new Location("storedProvider");
            bestLocation.setAccuracy(Float.parseFloat(sharedPref.getString("Accuracy", "0")));
            bestLocation.setTime(Long.parseLong(sharedPref.getString(dt, "0")));
            bestLocation.setLatitude(Float.parseFloat(sharedPref.getString("Latitude", "0")));
            bestLocation.setLongitude(Float.parseFloat(sharedPref.getString("Longitude", "0")));

            if (isBetterLocation(location, bestLocation)) {
                editor.putString("Longitude", String.valueOf(location.getLongitude()));
                editor.putString("Latitude", String.valueOf(location.getLatitude()));
                editor.putString("Accuracy", String.valueOf(location.getAccuracy()));
                editor.putString("Time", String.valueOf(location.getTime()));
                editor.putString("Elevation", String.valueOf(location.getAltitude()));
                String date = DateFormat.format("dd-MM-yyyy HH:mm", Long.parseLong(String.valueOf(location.getTime()))).toString();
                editor.apply();
            }
        }


        public void onStatusChanged(String s, int i, Bundle b) {
            showCurrentLocation();
        }

        public void onProviderDisabled(String s) {

        }

        public void onProviderEnabled(String s) {

        }
    }

    public static void showDialog(final Context context, String message, final String type, final boolean status) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ready_for_training, null);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        TextView text = view.findViewById(R.id.text);
        text.setText(message);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setView(view);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("pre")) {
                    context.startActivity(new Intent(context, ViewPagerActivity.class).putExtra("slides", slides));
                    dialog.dismiss();
                    ((Activity) context).finish();
                } else {
                    Intent end_intent = new Intent(context, EndingActivity.class);
                    end_intent.putExtra("complete", status);
                    context.startActivity(end_intent);
                    ((Activity) context).finish();
                }


            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void showDialog(final Context context, String message, final String type, final Boolean status, final Data.SubMenu item) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ready_for_training, null);
        Button yes = view.findViewById(R.id.yes);
        Button no = view.findViewById(R.id.no);
        TextView text = view.findViewById(R.id.text);
        text.setText(message);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setView(view);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent end_intent;
                if (type.equals("pre")) {
                    end_intent = new Intent(context, ViewPagerActivity.class).putExtra(CONSTANTS.URI_SUBMENU_DT, item);
                } else {
                    end_intent = new Intent(context, EndingActivity.class).putExtra(CONSTANTS.URI_SUBMENU_DT, item).putExtra("complete", status);
                }
                dialog.dismiss();
                context.startActivity(end_intent);
                ((Activity) context).finish();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void showDialogeWithResult(final Context context, Result result, final Data.SubMenu item) {


        int correct_number = (int) result.getCorrect();
        int wrong_number = (int) result.getWrong();
        View view = LayoutInflater.from(context).inflate(R.layout.result_dialog, null);
        Button okay = view.findViewById(R.id.okay);
        TextView correct = view.findViewById(R.id.correct);
        TextView percentage = view.findViewById(R.id.percentage);
        TextView finalText = view.findViewById(R.id.finalText);
        TextView wrong = view.findViewById(R.id.wrong);

        percentage.setText(String.valueOf(MainApp.roundOffFigure(result.getPercentage(), 2) + "%"));
        correct.setText(String.valueOf(correct_number));
        wrong.setText(String.valueOf(wrong_number));
        ImageView icon = view.findViewById(R.id.resultImage);

        final boolean sessionCondition = result.getPercentage() < 70.0;
        if (sessionCondition) {
            icon.setImageResource(R.drawable.sad);
            percentage.setTextColor(context.getResources().getColor(R.color.red));
            finalText.setText("You are required to reschedule this session again!");
            finalText.setTextColor(context.getResources().getColor(R.color.red));
        } else {
            percentage.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            icon.setImageResource(R.drawable.smile);
            finalText.setText("Awesome!");
            finalText.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setView(view);
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.show();
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent end_intent;
                if (sessionCondition) {
                    end_intent = new Intent(context, EndingActivity.class).putExtra(CONSTANTS.URI_SUBMENU_DT, item).putExtra("complete", false);
                } else {
                    end_intent = new Intent(context, EndingActivity.class).putExtra(CONSTANTS.URI_SUBMENU_DT, item).putExtra("complete", true);
                }
                dialog.dismiss();
                context.startActivity(end_intent);
                ((Activity) context).finish();
            }
        });

    }

    public static float roundOffFigure(double number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }


}
