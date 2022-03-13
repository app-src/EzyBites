package io.github.ashishthehulk.ezybites.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import io.github.ashishthehulk.ezybites.Fragments.RecipeAdapter;
import io.github.ashishthehulk.ezybites.Fragments.recipeCreateAdapter;
import io.github.ashishthehulk.ezybites.R;



public class AddPostScreen extends AppCompatActivity {
    Button save, refresh;
    EditText name;
    ArrayAdapter arrayAdapter;
    RecyclerView recipeList;
    private ListView listView;
    ArrayList array_list;
    @Override
    protected void onCreate(Bundle readdInstanceState) {
        super.onCreate(readdInstanceState);
        setContentView(R.layout.activity_main);
        recipeList = findViewById(R.id.listView);
        String[] temp={"A","B","C","D","E"};
        String[] temp2={"1","2","3","4","5"};

        recipeList.setLayoutManager(new LinearLayoutManager(this));
        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(new recipeCreateAdapter(temp,temp2));




//        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayAdapter.notifyDataSetChanged();
//                listView.invalidateViews();
//                listView.refreshDrawableState();
//            }
//        });
//        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!name.getText().toString().isEmpty()) {
//                    array_list.add(name.getText().toString());
//                    arrayAdapter = new ArrayAdapter(AddPostScreen.this, android.R.layout.simple_list_item_1, array_list);
//                    listView.setAdapter(arrayAdapter);
//                    Toast.makeText(AddPostScreen.this, "Inserted", Toast.LENGTH_LONG).show();
//                } else {
//                    name.setError("Enter NAME");
//                }
//            }
//        });
    }
}