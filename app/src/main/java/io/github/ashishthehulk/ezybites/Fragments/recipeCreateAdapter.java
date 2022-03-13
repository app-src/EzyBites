package io.github.ashishthehulk.ezybites.Fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.ashishthehulk.ezybites.R;

public class recipeCreateAdapter extends RecyclerView.Adapter<recipeCreateAdapter.recipeViewHolder> {
    private String[] ingredients;
    private String[] amount;


    public recipeCreateAdapter(String[] ingredients, String[] amount)
    {
        this.ingredients = ingredients;
        this.amount = amount;
    }

    @NonNull
    @Override
    public recipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardlayout,parent,false);
        return new recipeCreateAdapter.recipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recipeCreateAdapter.recipeViewHolder holder, int position) {
        String one1 = ingredients[position];
        String two2 = amount[position];
        holder.one.setText(one1);
        holder.two.setText(two2);
    }

    @Override
    public int getItemCount() {
        return ingredients.length;
    }

    public class recipeViewHolder extends RecyclerView.ViewHolder{
        TextView one;
        TextView two;
        public recipeViewHolder(View itemView)
        {


            super(itemView);

         one = itemView.findViewById(R.id.ing);
         two = itemView.findViewById(R.id.am);
        }
    }

}
