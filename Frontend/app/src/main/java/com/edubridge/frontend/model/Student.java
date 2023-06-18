package com.edubridge.frontend.model;

public class Student {
    private String id;
    private String name;
    private int age;
    private String nomor_induk_siswa;
    private String classroom_name;

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

    public String getNomorIndukSiswa() {
        return nomor_induk_siswa;
    }

    public int getAge(){
        return age;
    }

    public void setNomorIndukSiswa(String nomor_induk_siswa) {
        this.nomor_induk_siswa = nomor_induk_siswa;
    }

    public String getClassroomName() {
        return classroom_name;
    }

    public void setClassroomName(String classroom_name) {
        this.classroom_name = classroom_name;
    }
}

