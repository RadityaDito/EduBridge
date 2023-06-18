package com.edubridge.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TeacherActivity extends AppCompatActivity {

    private String id;
    private String name;
    private String classroomName;
    private String subjectName;
    private int age;
    private String nomorInduk;


    TextView nameTextView;
    TextView subjectTeacherTextView;
    TextView ageTextView;
    TextView nomorIndukTextView;
    TextView classroomTeacherTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        classroomName = intent.getStringExtra("classroomName");
        subjectName = intent.getStringExtra("subjectName");
        age = intent.getIntExtra("age", 0);
        nomorInduk = intent.getStringExtra("nomorInduk");

        // Initialize TextViews
        nameTextView = findViewById(R.id.name);
        subjectTeacherTextView = findViewById(R.id.subjectTeacher);
        ageTextView = findViewById(R.id.teacherAge);
        nomorIndukTextView = findViewById(R.id.teacherNomorInduk);
        classroomTeacherTextView = findViewById(R.id.teacherClassroom);

        nameTextView.setText(name);
        subjectTeacherTextView.setText(subjectName);
        ageTextView.setText(String.valueOf(age) + " Years Old");
        nomorIndukTextView.setText(nomorInduk);
        classroomTeacherTextView.setText(classroomName);
    }
}