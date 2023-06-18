package com.edubridge.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edubridge.frontend.R;
import com.edubridge.frontend.model.Item;
import com.edubridge.frontend.model.MyAdapter;
import com.edubridge.frontend.model.Teacher;
import com.edubridge.frontend.model.TeacherResponse;
import com.edubridge.frontend.request.BaseApiService;
import com.edubridge.frontend.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTeacherActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    private List<Teacher> teachers;
    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teacher);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPreferences = getSharedPreferences("MyAccount", Context.MODE_PRIVATE);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the previous activity
                Intent intent = new Intent(ListTeacherActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        getTeachers();
    }

    private void getTeachers() {
        mApiService.getTeachers().enqueue(new Callback<TeacherResponse>() {
            @Override
            public void onResponse(Call<TeacherResponse> call, Response<TeacherResponse> response) {
                if (response.isSuccessful()) {
                    // API call successful, handle the response
                    TeacherResponse teacherResponse = response.body();
                    teachers = teacherResponse.getData();
                    // Process the list of teachers
                    displayTeacherData();
                } else {
                    // API call failed
                    Toast.makeText(mContext, "FAILED TO GET TEACHERS", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeacherResponse> call, Throwable t) {
                Toast.makeText(mContext, "FAILED TO GET TEACHERS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayTeacherData() {
        // Create a list of items to display in the RecyclerView
        List<Item> items = new ArrayList<Item>();

        // Add items based on the retrieved teachers
        for (Teacher teacher : teachers) {
            String name = teacher.getName();
            String subject = teacher.getSubjectName();
            Item item = new Item(name, subject, R.drawable.teacher_avatar2);
            items.add(item);
        }

        // Set up the RecyclerView with the adapter and layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(getApplicationContext(), items);
        adapter.setItemClickListener(this); // Set the item click listener
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        // Handle item click event
        // Example: Intent to another activity
        Log.d("SharedPreferences", "Token: ");
        Intent intent = new Intent(ListTeacherActivity.this, TeacherActivity.class);


        intent.putExtra("id", teachers.get(position).getId());
        intent.putExtra("name", teachers.get(position).getName());
        intent.putExtra("classroomName", teachers.get(position).getClassroomName());
        intent.putExtra("subjectName", teachers.get(position).getSubjectName());
        intent.putExtra("age", teachers.get(position).getAge());
        intent.putExtra("nomorInduk", teachers.get(position).getNomorIndukGuru());

        startActivity(intent);
    }
}
