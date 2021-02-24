package com.example.intern1project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intern1project.databinding.UserdatashowBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    ArrayList<UserModel> list;

    public UserAdapter(Context context, ArrayList<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userdatashow,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = list.get(position);
        holder.binding.nameRecyclerView.setText(user.getName());
        holder.binding.ageRecyclerView.setText(user.getAge());
        holder.binding.genderRecyclerView.setText(user.getGender());
        holder.binding.phoneNumberRecyclerView.setText(user.getPhoneNumber());
        int x = position+1;
        holder.binding.heading.setText("User "+x);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder{

        UserdatashowBinding binding;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = UserdatashowBinding.bind(itemView);

        }
    }
}
