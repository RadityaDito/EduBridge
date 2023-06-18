package com.edubridge.frontend.model;

import java.util.List;

public class StudentResponse {
    private String message;
    private List<Student> data;

    public String getMessage() {
        return message;
    }

    public List<Student> getData() {
        return data;
    }
}
