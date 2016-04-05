package com.example.giorgi.retrofitsimpleproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.giorgi.retrofitsimpleproject.connections.RetrofitAPI;
import com.example.giorgi.retrofitsimpleproject.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  "aaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // make retrofit class
       Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com").addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitAPI api = retrofit.create(RetrofitAPI.class);

        //https://api.github.com/users/octocat/repos
        final Call<List<Repo>> repos = api.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                // count jSon objects
                Log.e(TAG, "onResponse: " + response.body().size());

                // first json object name
                Log.d(TAG+TAG, "onResponce:" + response.body().get(0).getName().toString());

                // all names
                StringBuilder sb = new StringBuilder();
                String names= "";
                for(int i=0; i<response.body().size(); i++){
                    names =  response.body().get(i).getName().toString();
                    sb.append(names + "\n");
                }
                Toast.makeText(getBaseContext(),sb, Toast.LENGTH_LONG ).show();
                Log.d(TAG + TAG + TAG, "onResponce:" + sb);

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
        Log.e(TAG, "onCreate: ");


    }

}
