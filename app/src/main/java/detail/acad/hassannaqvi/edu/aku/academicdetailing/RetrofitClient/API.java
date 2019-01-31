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

    //    @Headers({"Accept: application/json"})
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("forms.php")
    Call<ResponseBody> syncForms(@Body JSONArray array);

    @POST("api/userProfile")
    Call<ResponseBody> updateUser(@Body RequestBody mBody);


}
