package com.pucmm.retrofit_temasespeciales_ejercicio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> implements Serializable {

    private final Context context;
    private List<PostResponse> responseList;
    private ClickOnRowListener clickOnRowListener;
    //private ClickedItem clickedItem;

    /*public UsersAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }


    public void setData(Context context, List<PostResponse> responseList, ClickOnRowListener clickOnRowListener) {
        this.context = context;
        this.responseList = responseList;
        this.clickOnRowListener = clickOnRowListener;
     }*/

    PostAdapter(Context context, List<PostResponse> responseList, ClickOnRowListener clickOnRowListener)
    {
        this.context = context;
        this.responseList = responseList;
        this.clickOnRowListener = clickOnRowListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView IdPost, TitlePost, BodyPost;
        ClickOnRowListener clickOnRowListener;

        public MyViewHolder(@NonNull View itemView, ClickOnRowListener clickOnRowListener) {
            super(itemView);
            IdPost = itemView.findViewById(R.id.IdPost);
            TitlePost = itemView.findViewById(R.id.TitlePost);
            BodyPost = itemView.findViewById(R.id.BodyPost);
            this.clickOnRowListener = clickOnRowListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickOnRowListener.ClickOnRow(getAdapterPosition());
        }

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.response_list_view, parent, false);
        return new MyViewHolder(view, clickOnRowListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        PostResponse element = responseList.get(position);
        holder.IdPost.setText(String.valueOf(element.getId()));
        holder.TitlePost.setText(element.getTitle());
        holder.BodyPost.setText(element.getBody());
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    interface ClickOnRowListener {
        void ClickOnRow(int position);
    }
}
