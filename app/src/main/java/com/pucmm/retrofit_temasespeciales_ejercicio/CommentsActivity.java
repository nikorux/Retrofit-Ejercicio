package com.pucmm.retrofit_temasespeciales_ejercicio;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView general_view;
    private List<CommentResponse> responseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<CommentResponse>> listCall = retrofit.create(MyAPIService.class).getCommentsFromPost(getIntent().getExtras().getInt("Position") + 1);

        listCall.enqueue(new Callback<List<CommentResponse>>() {
            @Override
            public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                responseList = response.body();
                assert responseList != null;
                for (CommentResponse comment: responseList
                ) {
                    System.out.println("Comentario: " + comment.getId());
                }
                CommentsAdapter adapter = new CommentsAdapter(getApplicationContext(), responseList);
                general_view = findViewById(R.id.comment_p_post);
                general_view.setLayoutManager(new LinearLayoutManager(CommentsActivity.this));
                general_view.setHasFixedSize(true);
                general_view.setItemAnimator(new DefaultItemAnimator());
                general_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CommentResponse>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }
}
