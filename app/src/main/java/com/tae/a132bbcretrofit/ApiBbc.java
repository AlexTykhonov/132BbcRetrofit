package com.tae.a132bbcretrofit;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiBbc {

        @GET("top-headlines")
        Observable<PojoNews> getBbcData(@Query("sources") String site, @Query("apiKey") String name);
    }
