package com.edubridge.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edubridge.frontend.request.BaseApiService;
import com.edubridge.frontend.request.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    private String id;
    private String name;
    private String classroomName;
    private int age;
    private String nomorInduk;
    private Button deleteButton;

    TextView nameTextView;
    TextView classroomStudentTextView;
    TextView ageTextView;
    TextView nomorIndukTextView;

    private Context mContext;
    private BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        mApiService = UtilsApi.getAPIService();
        mContext = this;

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        classroomName = intent.getStringExtra("classroomName");
        age = intent.getIntExtra("age", 0);
        nomorInduk = intent.getStringExtra("nomorInduk");

        Log.d("Data", "ID: " + id);
        Log.d("Data", "Name: " + name);
        Log.d("Data", "Classroom Name: " + classroomName);
        Log.d("Data", "Age: " + age + " Years Old");
        Log.d("Data", "Nomor Induk: " + nomorInduk);

        //Initialize Button
        deleteButton = findViewById(R.id.studentDeleteButton);
        // Initialize TextViews
        nameTextView = findViewById(R.id.name);
        classroomStudentTextView = findViewById(R.id.classroomStudent);
        ageTextView = findViewById(R.id.studentAge);
        nomorIndukTextView = findViewById(R.id.studentNomorInduk);

        nameTextView.setText(name);
        classroomStudentTextView.setText(classroomName);
        ageTextView.setText(String.valueOf(age) + " Years Old");
        nomorIndukTextView.setText(nomorInduk);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete student action here
                System.out.println(id);
                deleteStudent(id);
            }
        });
    }

    private void deleteStudent(String id) {
        mApiService.deleteStudent(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Succesfully Delete Student", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, DashboardActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(mContext, "FAILED TO DELETE", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "FAILED TO DELETE STUDENT", Toast.LENGTH_SHORT).show();
            }
        });
    }
}