package com.example.attendenceviewerforteachers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvAttendance;
    ArrayList<String> list,list2;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        list2=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        getdataFromFirebase();
        subscribeToMessage();

        rvAttendance = findViewById(R.id.rvAttendance);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvAttendance.setLayoutManager(manager);

    }
    private void getdataFromFirebase() {
        progressDialog.setMessage("loading data please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        FirebaseDatabase.getInstance().getReference("Attendence").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.getChildrenCount();
                Map<String, String> td = (HashMap<String, String>) dataSnapshot.getValue();
                Iterator myVeryOwnIterator = td.keySet().iterator();
                while (myVeryOwnIterator.hasNext()) {
                    String key = (String) myVeryOwnIterator.next();
                    list.add(key);
                }
     for (int i=0;i<list.size();i++) {
    FirebaseDatabase.getInstance().getReference("Attendence").child(list.get(0)).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

/*                Map<String, String> td = (HashMap<String, String>) dataSnapshot.getValue();
                Iterator myVeryOwnIterator = td.keySet().iterator();
                while (myVeryOwnIterator.hasNext()) {
                    String key = (String) myVeryOwnIterator.next();
                    list2.add(key);
                    UserListAdapter userListAdapter = new UserListAdapter(context,list2);
                    holder.rvUsers.setAdapter(userListAdapter);

                }*/

            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                list2.add(snapshot.getKey());
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
}
     AttendanceAdapter attendanceAdapter = new AttendanceAdapter(getApplicationContext(), count,list2,list.get(0));
                rvAttendance.setAdapter(attendanceAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



    }
    private void subscribeToMessage() {
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (!task.isSuccessful()) {
                            Log.e("message", "fcm");
                        }
                    }
                });
    }
}
