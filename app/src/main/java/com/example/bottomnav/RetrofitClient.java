package com.example.bottomnav;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static final String BASE_URL = new ServerAPI().BASE_URL;
    private static Retrofit retrofit = null;

    public static RegisterAPI getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(RegisterAPI.class);
    }
}
