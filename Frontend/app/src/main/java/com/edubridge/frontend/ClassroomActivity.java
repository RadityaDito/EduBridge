package com.edubridge.frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.edubridge.frontend.model.ClassAdapter;
import com.edubridge.frontend.model.Classroom;
import com.edubridge.frontend.model.ClassItem;
import com.edubridge.frontend.model.ClassAdapter;
import com.edubridge.frontend.model.Student;
import com.edubridge.frontend.model.ClassroomResponse;
import com.edubridge.frontend.request.BaseApiService;
import com.edubridge.frontend.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassroomActivity extends AppCompatActivity implements ClassAdapter.ItemClickListener {

    private List<Classroom> classrooms;
    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPreferences = getSharedPreferences("MyAccount", Context.MODE_PRIVATE);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the previous activity
                Intent intent = new Intent(ClassroomActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        getClassrooms();
    }

    private void getClassrooms() {
        mApiService.getClassrooms().enqueue(new Callback<ClassroomResponse>() {
            @Override
            public void onResponse(Call<ClassroomResponse> call, Response<ClassroomResponse> response) {
                if (response.isSuccessful()) {
                    // API call successful, handle the response
                    ClassroomResponse classroomResponse = response.body();
                    classrooms = classroomResponse.getData();
                    // Process the list of students
                    displayClassroomData();
                } else {
                    // API call failed
                    Toast.makeText(mContext, "FAILED TO GET CLASSROOM", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClassroomResponse> call, Throwable t) {
                Toast.makeText(mContext, "FAILED TO GET CLASSROOM", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayClassroomData() {
        // Create a list of items to display in the RecyclerView
        List<ClassItem> items = new ArrayList<ClassItem>();



        // Add items based on the retrieved students
        for (Classroom classroom : classrooms) {
            String name = classroom.getName();
            String id = "";
            ClassItem item = new ClassItem(name, id, R.drawable.classroom2);
            items.add(item);
            System.out.println(classroom.getName());
            System.out.println(classroom.getId());
        }

        // Set up the RecyclerView with the adapter and layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ClassAdapter adapter = new ClassAdapter(getApplicationContext(), items);
        adapter.setItemClickListener(this); // Set the item click listener
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        // Handle item click event
        // Example: Intent to another activity
        Log.d("SharedPreferences", "Token: ");
        Intent intent = new Intent(ClassroomActivity.this, ListActivity.class);
        intent.putExtra("name", classrooms.get(position).getName());
        startActivity(intent);
    }


}