package com.edubridge.frontend.model;

public class Teacher {
    private String id;
    private String name;
    private int age;
    private String nomor_induk_guru;
    private String subject_id;
    private String subject_name;
    private String classroom_id;
    private String classroom_name;

    // Generate getters and setters for the fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNomorIndukGuru() {
        return nomor_induk_guru;
    }

    public void setNomorIndukGuru(String nomor_induk_guru) {
        this.nomor_induk_guru = nomor_induk_guru;
    }

    public String getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubjectName() {
        return subject_name;
    }

    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getClassroomId() {
        return classroom_id;
    }

    public void setClassroomId(String classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getClassroomName() {
        return classroom_name;
    }

    public void setClassroomName(String classroom_name) {
        this.classroom_name = classroom_name;
    }
}

