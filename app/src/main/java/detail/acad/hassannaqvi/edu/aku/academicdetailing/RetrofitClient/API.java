package detail.acad.hassannaqvi.edu.aku.academicdetailing.RetrofitClient;

import org.json.JSONArray;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("forms.php")
    Call<ResponseBody> syncForms(@Body String array);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("session_table.php")
    Call<ResponseBody> syncSession(@Body JSONArray array);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("next_meeting.php")
    Call<ResponseBody> syncNextMeeting(@Body JSONArray array);


}
