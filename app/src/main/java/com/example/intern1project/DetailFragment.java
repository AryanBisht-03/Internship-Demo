package com.example.intern1project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern1project.databinding.FragmentDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }

    FragmentDetailBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserAdapter adapter;
    ArrayList<UserModel>  users;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        View view = binding.getRoot();


        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        users = new ArrayList<>();
        adapter = new UserAdapter(getContext(),users);
        binding.recyclerView.setAdapter(adapter);

        reference.child(getString(R.string.internkey)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren())
                {
                    UserModel values = data.getValue(UserModel.class);
                    users.add(values);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}