package com.edubridge.frontend.model;

public class Student {
    private String id;
    private String name;
    private String nomor_induk_siswa;
    private String subject_name;

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

    public void setNomorIndukSiswa(String nomor_induk_siswa) {
        this.nomor_induk_siswa = nomor_induk_siswa;
    }

    public String getSubjectName() {
        return subject_name;
    }

    public void setSubjectName(String subject_name) {
        this.subject_name = subject_name;
    }
}

