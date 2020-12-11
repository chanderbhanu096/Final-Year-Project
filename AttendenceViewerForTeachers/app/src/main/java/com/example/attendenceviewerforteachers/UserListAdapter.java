package com.example.attendenceviewerforteachers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.Holder> {
    Context context;
    ArrayList<String> list2;

    public UserListAdapter(Context context, ArrayList<String> list2) {
        this.context = context;
        this.list2 = list2;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("aaabb", "list 2 size---" + list2.size());
        View view = LayoutInflater.from(context).inflate(R.layout.userlist, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.tvUser.setText(list2.get(i)+"  is present");
    }
    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvUser;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUserName);
        }
    }
}
