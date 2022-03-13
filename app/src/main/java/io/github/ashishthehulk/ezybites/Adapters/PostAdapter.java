package io.github.ashishthehulk.ezybites.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import io.github.ashishthehulk.ezybites.Models.PostModel;
import io.github.ashishthehulk.ezybites.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostModel> mList = new ArrayList<>();


    public PostAdapter(List<PostModel> mList)
    {


        this.mList = mList;
    }

    public PostAdapter(ArrayList<PostModel> list) {
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);


        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {



        holder.imageView.setImageResource(mList.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder
    {

        RoundedImageView imageView;

        public PostViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.postItem);

        }
    }

}
