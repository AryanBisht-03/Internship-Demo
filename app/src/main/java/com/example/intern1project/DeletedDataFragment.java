package com.example.intern1project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern1project.databinding.FragmentDeletedDataBinding;
import com.example.intern1project.databinding.FragmentDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class DeletedDataFragment extends Fragment {


    public DeletedDataFragment() {
        // Required empty public constructor
    }
    FragmentDeletedDataBinding binding;
    deleteAdapter adapter;
    ArrayList<deleteModel> users;
    FirebaseDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater,container,false);
        View view = binding.getRoot();

        database = FirebaseDatabase.getInstance();

        users = new ArrayList<>();
        adapter = new deleteAdapter(getContext(),users);
        binding.recyclerView.setAdapter(adapter);

        database.getReference().child(getString(R.string.internkey)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for(DataSnapshot data:snapshot.getChildren())
                {
                    deleteModel values = new deleteModel();
                    values.setName(data.child("name").getValue().toString());
                    values.setUid(data.getKey());
                    users.add(values);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return view;
    }
}