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
import com.edubridge.frontend.model.Student;
import com.edubridge.frontend.model.StudentResponse;
import com.edubridge.frontend.request.BaseApiService;
import com.edubridge.frontend.request.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements MyAdapter.ItemClickListener {

    private List<Student> students;
    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;
    private ProgressDialog loading;

    String classroomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPreferences = getSharedPreferences("MyAccount", Context.MODE_PRIVATE);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate to the previous activity
                Intent intent = new Intent(ListActivity.this, ClassroomActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        classroomName = intent.getStringExtra("name");
        System.out.println(classroomName);

        TextView classroomNameTextView = findViewById(R.id.classroomName);
        classroomNameTextView.setText(classroomName + " Students");


        getStudentByClass();
    }

    private void getStudentByClass() {
        mApiService.getStudentsByClass(classroomName).enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful()) {
                    // API call successful, handle the response
                    StudentResponse studentResponse = response.body();
                    students = studentResponse.getData();
                    // Process the list of students
                    displayStudentData();
                } else {
                    // API call failed
                    Toast.makeText(mContext, "FAILED TO GET STUDENT", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                Toast.makeText(mContext, "FAILED TO GET STUDENT", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayStudentData() {
        // Create a list of items to display in the RecyclerView
        List<Item> items = new ArrayList<Item>();



        // Add items based on the retrieved students
        for (Student student : students) {
            String name = student.getName();
            String nomorIndukSiswa = student.getNomorIndukSiswa();
//            System.out.println(student.getClassroomName());
//            System.out.println(student.getAge());
//            System.out.println(student.getName());
//            System.out.println(student.getNomorIndukSiswa());
            Item item = new Item(name, nomorIndukSiswa, R.drawable.student_avatar);
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
        Intent intent = new Intent(ListActivity.this, StudentActivity.class);

        intent.putExtra("id", students.get(position).getId());
        intent.putExtra("name", students.get(position).getName());
        intent.putExtra("classroomName", students.get(position).getClassroomName());
        intent.putExtra("age", students.get(position).getAge());
        intent.putExtra("nomorInduk", students.get(position).getNomorIndukSiswa());


        startActivity(intent);
    }
}
