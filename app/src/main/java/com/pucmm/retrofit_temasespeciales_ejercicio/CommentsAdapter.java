package com.pucmm.retrofit_temasespeciales_ejercicio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> implements Serializable {

    private List<CommentResponse> responseList;
    private final Context context;
    //TextView IdPost, TitlePost, BodyPost;

   /* public void setData(List<CommentResponse> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }*/

    public CommentsAdapter(Context context, List<CommentResponse> responseList)
    {
        this.context = context;
        this.responseList = responseList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView IdPost, TitlePost, BodyPost;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IdPost = itemView.findViewById(R.id.IdPost);
            TitlePost = itemView.findViewById(R.id.TitlePost);
            BodyPost = itemView.findViewById(R.id.BodyPost);
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CommentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.response_list_view, parent, false);
        return new CommentsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CommentResponse element = responseList.get(position);
        holder.IdPost.setText(String.valueOf(element.getId()));
        holder.TitlePost.setText(element.getName());
        holder.BodyPost.setText(element.getBody());
    }


    @Override
    public int getItemCount() {
        return responseList.size();
    }

}






