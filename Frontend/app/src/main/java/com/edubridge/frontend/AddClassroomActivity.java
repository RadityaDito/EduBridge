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

public class AddClassroomActivity extends AppCompatActivity {
    private EditText editName;
    private Button btnCreate;

    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classroom);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        // Initialize EditText
        editName = findViewById(R.id.addClassroom_editName);


        // Initialize Button
        btnCreate = findViewById(R.id.addClassroom_btncreate);

        // Set click listener for the button
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform actions when the button is clicked
                String name = editName.getText().toString();

                System.out.println(name);
                // TODO: Perform desired actions with the entered data
                createClassroom(name);
            }
        });
    }

    private void createClassroom(String name) {
        mApiService.createClassroom(name)
                .enqueue(new retrofit2.Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            // Registration successful, handle the response
                            // TODO: Handle the success response
                            Toast.makeText(mContext, "Successfully Create Classroom", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, DashboardActivity.class);
                            startActivity(intent);
                        } else {
                            // Registration failed
                            // TODO: Handle the failure response
                            Toast.makeText(mContext, "Failed to Create Classroom", Toast.LENGTH_SHORT).show();
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