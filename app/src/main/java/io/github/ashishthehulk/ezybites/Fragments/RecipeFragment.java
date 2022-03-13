package io.github.ashishthehulk.ezybites.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.github.ashishthehulk.ezybites.R;
import io.github.ashishthehulk.ezybites.Screens.AddPostScreen;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SearchView searchView;
    private FloatingActionButton spin_fab;
    private RecyclerView recipeList;

    public RecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_recipe, container, false);

        String[] temp={"Baingan ka Bharta","Chicken Handi","Dal fry","Kidney Bean Curry","Lamb Biryani"};
        String[] ingre={"1. Brinjal 100 gm\n2. Ghee 2tbsp\n3. Red Chillis 3-4pcs\n4. Garlic 2 cloves","1. Chicken 1pc\n 2. Ghee 2tbsp\n3. ChickenMasala 2tsp\n4. Garlic 1 clove","1. Toor Dal 200gm\n 2. Ghee 2tbsp\n3. Tomatos 3-4pcs\n4. Coriander 20gm","1. Kidney Beans 200gm\n 2. Onions 100gm\n3. Garam Masala 2tsp\n4. Garlic 2cloves","1. Lamb 500gm\n 2. Oil 2tbsp\n3. Turmeric 10gm\n4. Tomato 3-4pcs"};
        int[] temp2={R.drawable.foodrecipeimage1,R.drawable.foodrecipeimage2,R.drawable.foodrecipeimage3,R.drawable.foodrecipeimage4,R.drawable.foodrecipeimage5};
        int[] temp3={R.drawable.veg,R.drawable.nonveg,R.drawable.veg,R.drawable.veg,R.drawable.nonveg};

        recipeList= (RecyclerView) view.findViewById(R.id.recyclerView);

        recipeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(new RecipeAdapter(temp,ingre,temp2,temp3));





        searchView = view.findViewById(R.id.search_recipe);

        return view;
    }
}