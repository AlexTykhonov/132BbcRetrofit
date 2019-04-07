package com.tae.a132bbcretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.net.ssl.SSLContext;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

ApiBbc apiBbc;
Retrofit retrofit;
RecyclerView recyclerView;
RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = RetrofitClient.callRetrofit();
        apiBbc = retrofit.create(ApiBbc.class);
        

        apiBbc.getBbcData("bbc-news","1ab09308782244538982ed1870f37d82")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);

    }
    public void handleResults(PojoNews pojoNews)
    {
        if (pojoNews != null ) {
            recyclerViewAdapter.setData(pojoNews);
            System.out.println(pojoNews+" _____________________________________________________________");

        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void handleError(Throwable t){
        System.out.println(t+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}

