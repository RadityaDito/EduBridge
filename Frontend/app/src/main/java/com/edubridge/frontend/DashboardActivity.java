package com.edubridge.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.edubridge.frontend.request.BaseApiService;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {

    private CardView studentCard;
    private CardView teacherCard;
    private CardView addStudentCard;
    private CardView addTeacherCard;
    private CardView addClassroomCard;

    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Assign Id to Variable
        studentCard = findViewById(R.id.studentCard);
        teacherCard = findViewById(R.id.teacherCard);
        addStudentCard = findViewById(R.id.addStudentCard);
        addTeacherCard = findViewById(R.id.addTeacherCard);
        addClassroomCard = findViewById(R.id.addClassroomCard);


        sharedPreferences = getSharedPreferences("MyAccount", Context.MODE_PRIVATE);

        // Retrieve the stored data from shared preferences
        String token = sharedPreferences.getString("token", "");
        String id = sharedPreferences.getString("id", "");
        String name = sharedPreferences.getString("name", "");
        String role = sharedPreferences.getString("role", "");

        // Use the retrieved data as needed
        // For example, print the values
        Log.d("SharedPreferences", "Token: " + token);
        Log.d("SharedPreferences", "ID: " + id);
        Log.d("SharedPreferences", "Name: " + name);
        Log.d("SharedPreferences", "Role: " + role);




        studentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the CardView is clicked

                // Create an Intent to navigate to the desired activity
                Intent intent = new Intent(DashboardActivity.this, ClassroomActivity.class);
                startActivity(intent);
            }
        });

        teacherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the CardView is clicked

                // Create an Intent to navigate to the desired activity
                Intent intent = new Intent(DashboardActivity.this, ListTeacherActivity.class);
                startActivity(intent);
            }
        });

        addStudentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the CardView is clicked

                // Create an Intent to navigate to the desired activity
                Intent intent = new Intent(DashboardActivity.this, addStudentActivity.class);
                startActivity(intent);
            }
        });

        addTeacherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the CardView is clicked

                // Create an Intent to navigate to the desired activity
                Intent intent = new Intent(DashboardActivity.this, addTeacherActivity.class);
                startActivity(intent);
            }
        });

        addClassroomCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform action when the CardView is clicked

                // Create an Intent to navigate to the desired activity
                Intent intent = new Intent(DashboardActivity.this, AddClassroomActivity.class);
                startActivity(intent);
            }
        });

    }
}