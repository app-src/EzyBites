package io.github.ashishthehulk.ezybites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.github.ashishthehulk.ezybites.Fragments.CommunityFragment;
import io.github.ashishthehulk.ezybites.Fragments.ProfileFragment;
import io.github.ashishthehulk.ezybites.Fragments.RecipeFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private int frag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigation = findViewById(R.id.bottomNavigation);


        switch (frag)
        {
            case 1:
                replaceFragment(new RecipeFragment());
                bottomNavigation.setSelectedItemId(R.id.recipe);
                break;

            case 2:
                replaceFragment(new CommunityFragment());
                bottomNavigation.setSelectedItemId(R.id.community);
                break;

            case 3:
                replaceFragment(new ProfileFragment());
                bottomNavigation.setSelectedItemId(R.id.profile);
                break;



        }



        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {




                switch (item.getItemId())

                {
                    case R.id.recipe:

                        replaceFragment(new RecipeFragment());
                        break;

                    case R.id.community:


                        replaceFragment(new CommunityFragment());
                        break;

                    case R.id.profile:

                        replaceFragment(new ProfileFragment());
                        break;

                }
                return true;
            }


        });


    }


    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrameLayout, fragment);
        transaction.commit();

    }


}