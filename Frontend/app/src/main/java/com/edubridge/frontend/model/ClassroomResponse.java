package com.edubridge.frontend.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ClassroomResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Classroom> data;

    @SerializedName("error")
    private boolean error;

    public String getMessage() {
        return message;
    }

    public List<Classroom> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }


}
