package com.pucmm.retrofit_temasespeciales_ejercicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyAPIService {

    @GET("posts")
    Call<List<PostResponse>> getPost();

    @GET("posts/{postId}/comments")
    Call<List<CommentResponse>> getCommentsFromPost(@Path("postId") int postId);

}
