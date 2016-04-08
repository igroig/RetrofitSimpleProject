package com.example.giorgi.retrofitsimpleproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.giorgi.retrofitsimpleproject.connections.RetrofitAPI;
import com.example.giorgi.retrofitsimpleproject.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "aaa";
    private RecyclerView rvRepos;

    List<Repo> lRepo;
    RVAdapter rvAdapter;

    private final String BASE_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();



        //set adapter
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        rvRepos.setLayoutManager(llManager);
        rvAdapter = new RVAdapter();
        rvRepos.setAdapter(rvAdapter);

//        // make retrofit class
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//        RetrofitAPI api = retrofit.create(RetrofitAPI.class);

        RetrofitAPI api = StRetrApi.getInstance().getRetrofitApi();


        //https://api.github.com/users/octocat/repos
        final Call<List<Repo>> repos = api.listRepos("octocat");
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                // count jSon objects
                Log.e(TAG, "onResponse: " + response.body().size());

                // first json object name
                Log.d(TAG + TAG, "onResponce:" + response.body().get(0).getName().toString());


                //recycler

                lRepo = response.body();
                rvAdapter.addAll(lRepo);
                rvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
        Log.e(TAG, "onCreate: ");

    }

    public void setupViews() {
        rvRepos = (RecyclerView) findViewById(R.id.rvRepos);
    }

}
