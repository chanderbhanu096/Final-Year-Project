package com.example.attendenceviewerforteachers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.Holder> {

    Context context;
    int count;
    ArrayList<String> list, list2;
    String s;

    public AttendanceAdapter(Context context, int count, ArrayList<String> list, String s) {
        this.context = context;
        this.count = count;
        this.list = list;
    this.s=s;
    }

    @NonNull
    @Override
    public AttendanceAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.attendate, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AttendanceAdapter.Holder holder, final int i) {
        holder.textView.setText(list.get(i)+ " is present on " + s);
        list2 = new ArrayList<>();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvDate);

        }
    }
}
