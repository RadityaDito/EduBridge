package com.edubridge.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edubridge.frontend.request.BaseApiService;
import com.edubridge.frontend.request.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class addTeacherActivity extends AppCompatActivity {
    private EditText editName, editAge, editNomorInduk, editUsername, editPassword, editClassroom;
    private Button btnCreate;

    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        // Initialize EditText
        editName = findViewById(R.id.addTeacher_editname);
        editAge = findViewById(R.id.addTeacher_editdesc);
        editNomorInduk = findViewById(R.id.addTeacher_nomorInduk);
        editClassroom = findViewById(R.id.addTeacher_classroom);
        editUsername = findViewById(R.id.addTeacher_username);
        editPassword = findViewById(R.id.addTeacher_password);

        // Initialize Button
        btnCreate = findViewById(R.id.addTeacher_btncreate);

        // Set click listener for the button
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform actions when the button is clicked
                String name = editName.getText().toString();
                String age = editAge.getText().toString();
                String nomorInduk = editNomorInduk.getText().toString();
                String classroomName = editClassroom.getText().toString();
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                System.out.println(name);
                System.out.println(age);
                System.out.println(nomorInduk);
                System.out.println(username);
                System.out.println(password);
                System.out.println(classroomName);
                // TODO: Perform desired actions with the entered data
                registerTeacher(username,password,"Teacher", name, age, nomorInduk, classroomName);
            }
        });
    }

    private void registerTeacher(String username, String password, String role, String name, String age, String nomorInduk, String classroomName) {
        mApiService.registerRequestTeacher(username, password, role, name, age, nomorInduk, classroomName)
                .enqueue(new retrofit2.Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            // Registration successful, handle the response
                            // TODO: Handle the success response
                            Toast.makeText(mContext, "Successfully Add Teacher", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, DashboardActivity.class);
                            startActivity(intent);
                        } else {
                            // Registration failed
                            // TODO: Handle the failure response
                            Toast.makeText(mContext, "Failed to Add Teacher", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        // Registration request failed
                        // TODO: Handle the failure case
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                    }
                });
    }
}