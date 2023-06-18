package com.edubridge.frontend.model;

import java.util.List;

public class TeacherResponse {
    private String message;
    private List<Teacher> data;
    private boolean error;

    // Generate getters and setters for the fields

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Teacher> getData() {
        return data;
    }

    public void setData(List<Teacher> data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
