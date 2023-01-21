package com.mb.lab.iot_android_fireabase_nodemcu_esp8266;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class AutoAssignActionButton extends AppCompatActivity {
    private final String TAG = "AutoAssignActionButton";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_assign_action_button);

        LinearLayout layout = findViewById(R.id.linearButtonGroup);
//        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        for (int i = 0; i < 6; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            int mod = i % 2;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (mod == 1) {
                    row.setBackgroundColor(getColor(R.color.purple_200));
                } else {
                    row.setBackgroundColor(getColor(R.color.purple_700));
                }
            }
            row.setGravity(Gravity.CENTER);

            Button btnTag = new Button(this);
            btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            btnTag.setText("ROOM " + i);
            btnTag.setGravity(Gravity.CENTER);
            btnTag.setId(1 + (i * 4));
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + btnTag.getText().toString());
                }
            });
            row.addView(btnTag);

            layout.addView(row);
        }
//        setContentView(layout);

    }
}