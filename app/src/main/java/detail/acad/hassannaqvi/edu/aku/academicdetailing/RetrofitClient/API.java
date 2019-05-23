package detail.acad.hassannaqvi.edu.aku.academicdetailing.RetrofitClient;

import org.json.JSONArray;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("forms.php")
    Call<ResponseBody> syncForms(@Body String array);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("session_table.php")
    Call<ResponseBody>  syncSession(@Body JSONArray array);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("next_meeting.php")
    Call<ResponseBody> syncNextMeeting(@Body JSONArray array);

    @GET("healthfacilities.php")
    Call<ResponseBody> synHfData();

    @GET("api/providers.php")
    Call<ResponseBody> synHPData();

    @GET("api/users.php")
    Call<ResponseBody> getUsers();

    @GET("app/output.json")
    Call<ResponseBody> getAppVersion();

    @GET("api/districts.php")
    Call<ResponseBody> getDistricts();

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("api/appointments.php")
    Call<ResponseBody> getAppointments(@Body RequestBody object);


}
