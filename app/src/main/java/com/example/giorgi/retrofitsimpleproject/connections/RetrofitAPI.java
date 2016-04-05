package com.example.giorgi.retrofitsimpleproject.connections;

import com.example.giorgi.retrofitsimpleproject.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by giorgi on 4/5/2016.
 */
public interface RetrofitAPI {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);



    @POST("users/repos")
    Call<List<Repo>> listReposParameter(@Query("user") String user, @Query("age") String age, @Query("asdasda") String asdada);


    @POST("users/delete")
    Call<List<Repo>> deleteAccount(@Query("userid") String userId);

}
