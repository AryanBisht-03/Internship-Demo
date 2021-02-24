package com.example.intern1project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intern1project.databinding.DeleteRecyclerviewBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class deleteAdapter extends RecyclerView.Adapter<deleteAdapter.deleteViewHolder>{

    Context context;
    ArrayList<deleteModel> list;

    public deleteAdapter(Context context, ArrayList<deleteModel> list) {
        this.context = context;
        this.list = list;
    }

    public deleteAdapter(){

    }

    @NonNull
    @Override
    public deleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delete_recyclerview,parent,false);
        return new deleteAdapter.deleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull deleteViewHolder holder, int position) {

        deleteModel data = list.get(position);
        holder.binding.namedelete.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class deleteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        DeleteRecyclerviewBinding binding;

        public deleteViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = DeleteRecyclerviewBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            deleteModel data = list.get(position);
            Log.d("Aryan","Inside");
            new AlertDialog.Builder(context).setTitle("Are you sure u want to delete").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase.getInstance().getReference().child(context.getString(R.string.internkey)).child(data.getUid()).removeValue();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();

            return;
        }
    }

}
