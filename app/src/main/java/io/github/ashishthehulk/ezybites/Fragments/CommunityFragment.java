package io.github.ashishthehulk.ezybites.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import io.github.ashishthehulk.ezybites.Adapters.PostAdapter;
import io.github.ashishthehulk.ezybites.Models.PostModel;
import io.github.ashishthehulk.ezybites.R;
import io.github.ashishthehulk.ezybites.Screens.AddPostActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommunityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommunityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SearchView searchView;
    private SwipeRefreshLayout refreshView;
    private RecyclerView  postRecyclerView;
    private FloatingActionButton spin_fab;
    private DatabaseReference myRef;

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private FirebaseUser user;
    private ArrayList<PostModel> list;

    public CommunityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommunityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommunityFragment newInstance(String param1, String param2) {
        CommunityFragment fragment = new CommunityFragment();
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
        View view =  inflater.inflate(R.layout.fragment_community, container, false);

        searchView = view.findViewById(R.id.search_recipe);
        refreshView = view.findViewById(R.id.refreshView);
        postRecyclerView = view.findViewById(R.id.postRecyclerView);
        spin_fab = view.findViewById(R.id.spin_fab);
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser();




        postRecyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();

        list.add(new PostModel(R.drawable.f1));
        list.add(new PostModel(R.drawable.f2));
        list.add(new PostModel(R.drawable.f3));
        list.add(new PostModel(R.drawable.f4));
        list.add(new PostModel(R.drawable.f5));
//        getDataFromFirebase();
       // postRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PostAdapter adapter = new PostAdapter(list);
        postRecyclerView.setAdapter(adapter);


        spin_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), AddPostActivity.class));
                getActivity().finish();

            }
        });

        return view;
    }


//    private void getDataFromFirebase()
//    {
//        firestore.collection("Posts").document().addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                Log.d("Error Log:","eror");
//
//
//
//                if(value.exists())
//                {
//                    PostModel postModel = new PostModel();
//                    postModel.setImage(value.getString("image").toString());
//                    Log.d("Item:", value.getString("image"));
//                    list.add(postModel);
//                }
//                adapter = new PostAdapter(getContext(), list);
//                postRecyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//
//            }
//        });
//    }

//    public void getDataFromFirebase()
//    {
//
//        Runnable objRunnable = new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    Query query = myRef.child("FoodData").orderByChild("serial");
//                    query.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            clearAll();
//
//                            for(DataSnapshot snapshot1 : snapshot.getChildren())
//                            {
//
//                                PostModel model = new PostModel();
//                                model.setImage(snapshot1.child("image").getValue().toString());
//                                list.add(model);
//                            }
//
//                            adapter = new PostAdapter(getActivity(), list);
//                            postRecyclerView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        Thread objBgThread = new Thread(objRunnable);
//        objBgThread.start();
//
//
//    }
//
//    private void clearAll()
//    {
//        if(list != null)
//        {
//            list.clear();
//
//            if(adapter != null)
//            {
//                adapter.notifyDataSetChanged();
//            }
//
//        }
//
//        list = new ArrayList<>();
//    }


}