package com.example.intern1project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.intern1project.databinding.FragmentAddDataBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddDataFragment extends Fragment {

    FragmentAddDataBinding binding;
    public AddDataFragment() {
        // Required empty public constructor
    }

    FirebaseDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = binding.inflate(inflater, container, false);
        View view = binding.getRoot();

        database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference();

        UserModel user = new UserModel();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.nameRecyclerView.getText().toString();
                String phone = binding.phoneNumberRecyclerView.getText().toString();
                String gender = binding.genderRecyclerView.getText().toString();
                String age = binding.ageRecyclerView.getText().toString();

                if(name.isEmpty()||phone.isEmpty()||gender.isEmpty()||age.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please fill all the Details first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    user.setAge(age);
                    user.setGender(gender);
                    user.setName(name);
                    user.setPhoneNumber(phone);
                    reference.child(getString(R.string.internkey)).push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Values are added in the DataBase", Toast.LENGTH_SHORT).show();
                            binding.nameRecyclerView.setText("");
                            binding.phoneNumberRecyclerView.setText("");
                            binding.genderRecyclerView.setText("");
                            binding.ageRecyclerView.setText("");
                        }
                    });
                }
            }
        });
        return view;
    }
}