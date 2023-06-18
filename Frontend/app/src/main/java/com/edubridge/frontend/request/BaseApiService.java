package com.edubridge.frontend.request;

import com.edubridge.frontend.model.Classroom;
import com.edubridge.frontend.model.ClassroomResponse;
import com.edubridge.frontend.model.LoginResponse;
import com.edubridge.frontend.model.StudentResponse;
import com.edubridge.frontend.model.TeacherResponse;

import java.math.BigInteger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * The interface Base api service.
 */
public interface BaseApiService {

    /**
     * Login request call.
     *
     * @param username the username
     * @param password the password
     * @return the call
     */
    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResponse> loginRequest(@Field("username") String username,
                                     @Field("password") String password);

    //Get All Classrooms
    @GET("classroom/")
    Call<ClassroomResponse> getClassrooms();

    //Get Student By Subject
    @GET("/student/getStudentBySubject/{subject_name}")
    Call<StudentResponse> getStudents(@Path("subject_name") String subjectName);

    //Get Student By Classroom
    @GET("student/getStudentByClassroom/{classroom_name}")
    Call<StudentResponse> getStudentsByClass(@Path("classroom_name") String classroomName);

    //Get All Teacher
    @GET("teacher/")
    Call<TeacherResponse> getTeachers();

    //Add Student or Teacher
    @FormUrlEncoded
    @POST("auth/registerMobile")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("password") String password,
                                       @Field("role") String role,
                                       @Field("name") String name,
                                       @Field("age") String age,
                                       @Field("nomor_induk_siswa") String nomor_induk_siswa,
                                       @Field("classroom_name") String classroom_name);

    @FormUrlEncoded
    @POST("auth/registerMobile")
    Call<ResponseBody> registerRequestTeacher(@Field("username") String username,
                                       @Field("password") String password,
                                       @Field("role") String role,
                                       @Field("name") String name,
                                       @Field("age") String age,
                                       @Field("nomor_induk_guru") String nomor_induk_guru,
                                       @Field("classroom_name") String classroom_name);

    @FormUrlEncoded
    @POST("classroom/create")
    Call<ResponseBody> createClassroom(@Field("name") String name);

    @DELETE("student/deleteStudentById/{student_id}")
    Call <ResponseBody> deleteStudent(@Path("student_id") String student_id);

    @DELETE("student/deleteTeacherById/{teacher_id}")
    Call <Void> deleteTeacher(@Path("teacher_id") String student_id);

}
