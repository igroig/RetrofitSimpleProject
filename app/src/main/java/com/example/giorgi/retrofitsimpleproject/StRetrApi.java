package com.example.giorgi.retrofitsimpleproject;

import android.util.EventLogTags;
import android.util.Log;

import com.example.giorgi.retrofitsimpleproject.connections.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by giorgi on 4/7/2016.
 */
public class StRetrApi {

    private static StRetrApi ourInstance = null;

    private  RetrofitAPI api;

    private StRetrApi(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(RetrofitAPI.class);
    }

    public static StRetrApi getInstance() {
        if(ourInstance == null){
            ourInstance = new StRetrApi();
        }
        return ourInstance;
    }

    public RetrofitAPI getRetrofitApi(){
        return this.api;
    }
}
