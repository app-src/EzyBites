package io.github.ashishthehulk.ezybites.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.ashishthehulk.ezybites.R;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private String[] data;
    private int[] temp;
    private int[] temp2;

    public RecipeAdapter(String[] temp, int[] temp2, int[] temp3)
    {
         this.data=temp;
         this.temp= temp2;
         this.temp2= temp3;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardlayout,parent,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        String title= data[position];
        int image1= temp[position];
        int image2= temp2[position];
        holder.dishDesc.setText(title);
        holder.dishImage.setImageResource(image1);
        holder.vegNon.setImageResource(image2);
    }

    @Override
    public int getItemCount() {
        return temp.length;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder{
        ImageView dishImage;
        TextView dishDesc;
        ImageView vegNon;
        public RecipeViewHolder(View itemView)
        {


            super(itemView);

            dishImage= itemView.findViewById(R.id.DishImage);
            dishDesc=itemView.findViewById(R.id.DishName);
            vegNon=itemView.findViewById(R.id.VegNonVegIndicator);
        }
    }
}
