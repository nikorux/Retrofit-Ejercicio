package com.pucmm.retrofit_temasespeciales_ejercicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PostAdapter.ClickOnRowListener {

    private RecyclerView general_view;
    private List<PostResponse> responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<PostResponse>> listCall = retrofit.create(MyAPIService.class).getPost();

        listCall.enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                responseList = response.body();
                PostAdapter adapter = new PostAdapter(getApplicationContext(), responseList, MainActivity.this);
                general_view = findViewById(R.id.general_view);
                general_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                general_view.setHasFixedSize(true);
                general_view.setItemAnimator(new DefaultItemAnimator());
                general_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }

    @Override
    public void ClickOnRow(int position) {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("Position", position);
        startActivity(intent);
    }
}
