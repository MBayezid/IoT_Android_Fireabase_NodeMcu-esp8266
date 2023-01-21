package com.mb.lab.iot_android_fireabase_nodemcu_esp8266;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = findViewById(R.id.linearButtonGroup);

        if(isNetworkAvailable() && internetIsConnected()){
            ((TextView)findViewById(R.id.internetStateTxt)).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.txtHomeComponents)).setVisibility(View.VISIBLE);
            //dataSnapShot starting
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference root = database.getReference();

        DatabaseReference mDatabase = root.child("Room1");


        ArrayList<Components> componentsArrayList = new ArrayList<>();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "Number of children: " + snapshot.getChildrenCount());
                // dataSnapshot.getChildren()  give all the days e.g day 1,day 2  // you can get your count here
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key = ds.getKey();  // get key as day 1
                    boolean state = Boolean.TRUE.equals(ds.getValue(Boolean.class));
//                    Log.d(TAG, "onDataChange: " + key + ":" + state);
                    componentsArrayList.add(new Components(key, state));
                }
                try {

                    int i = 0;
                    for (; i < componentsArrayList.size(); i++) {
//                        Log.d(TAG, "component: " + componentsArrayList.get(i).getKey() + ":" + componentsArrayList.get(i).getState());

                        LinearLayout row = new LinearLayout(MainActivity.this);
                        row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        row.setGravity(Gravity.CENTER);

                        Button btnTag = new Button(MainActivity.this);
                        btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
                        if (componentsArrayList.get(i).getState()) {
                            btnTag.setText(componentsArrayList.get(i).getKey() + " : " + "ON");

                        } else {
                            btnTag.setText(componentsArrayList.get(i).getKey() + " : " + "OFF");
                        }

                        btnTag.setGravity(Gravity.CENTER);
                        btnTag.setId(1 + (i * 4));

                        int finalI = i;
                        int mod = i % 2;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (mod == 1) {
                                row.setBackgroundColor(getColor(R.color.indicator));
                                btnTag.setBackgroundColor(getColor(R.color.indicator_def));

                            } else {
                                row.setBackgroundColor(getColor(R.color.indicator_def));
                                btnTag.setBackgroundColor(getColor(R.color.indicator));
                            }
                        }

                        DatabaseReference myRef = database.getReference("Room1").child(componentsArrayList.get(finalI).getKey());//LED_STATUS is Firebase database LED_STATUS
                        Log.d(TAG, "myRef: " + myRef);
                        btnTag.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (componentsArrayList.get(finalI).getState()) {
                                    componentsArrayList.get(finalI).setState(false);
                                    myRef.setValue(false);//update after database
                                    btnTag.setText(componentsArrayList.get(finalI).getKey() + " : " + "OFF");

                                } else {
                                    componentsArrayList.get(finalI).setState(true);
                                    myRef.setValue(true);//update after database
                                    btnTag.setText(componentsArrayList.get(finalI).getKey() + " : " + "ON");

                                }
                                Log.d(TAG, "onClick: " + btnTag.getText().toString());
                            }
                        });
                        row.addView(btnTag);

                        layout.addView(row);

                    }
                } catch (Exception e) {
                    Log.d(TAG, "onCreate: " + e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "onCancelled: " + error);

            }
        };
        mDatabase.addListenerForSingleValueEvent(valueEventListener);
//snapshot ENDING
        }
        else {
            ((TextView)findViewById(R.id.internetStateTxt)).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.txtHomeComponents)).setVisibility(View.GONE);
        }




    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public boolean internetIsConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

}
