package com.tae.a132bbcretrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit = null;

    public static final String BASE_URL = "https://newsapi.org/v2/";;

    public static Retrofit callRetrofit() {
        if (retrofit == null) {
             retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } return retrofit;
    }


}
