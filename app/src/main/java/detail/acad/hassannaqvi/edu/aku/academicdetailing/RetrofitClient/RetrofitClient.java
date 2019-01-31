package detail.acad.hassannaqvi.edu.aku.academicdetailing.RetrofitClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit;
    public static API service;
    private static String baseURL = "http://f46916/webapiphp/uenad/api/";

    public static void createRetrofitInstance(){
//          OkHttpClient client = new OkHttpClient.Builder().readTimeout(15 , TimeUnit.HOURS);
//             OkHttpClient client = new OkHttpClient.Builder().readTimeout(15 , TimeUnit.HOURS);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();


        retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(API.class);



    }
}
