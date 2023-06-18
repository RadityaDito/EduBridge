package com.edubridge.frontend;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.edubridge.frontend.DashboardActivity;
import com.edubridge.frontend.R;
import com.edubridge.frontend.model.LoginResponse;
import com.edubridge.frontend.model.UserData;
import com.edubridge.frontend.request.BaseApiService;
import com.edubridge.frontend.request.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername;
    private EditText loginPassword;
    private Button loginButton;
    private ProgressDialog loading;

    private Context mContext;
    private BaseApiService mApiService;
    private SharedPreferences sharedPreferences;

    public static UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPreferences = getSharedPreferences("MyAccount", Context.MODE_PRIVATE);

        initComponents();
    }

    private void initComponents() {
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Please Wait For A Moment", true, false);

                requestLogin();
            }
        });
    }

    private void requestLogin() {
        mApiService.loginRequest(loginUsername.getText().toString(), loginPassword.getText().toString())
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            loading.dismiss();
                            try {
//                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                LoginResponse loginResponse = response.body();
                                UserData userData = loginResponse.getData();

                                if (!loginResponse.isError()) {
//                                    String message = jsonRESULTS.getString("message");
                                    String message = loginResponse.getMessage();
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                    ;
                                    // Simpan data ke Shared Preferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("token", loginResponse.getToken());
                                    editor.putString("id", userData.getId());
                                    editor.putString("name", userData.getName());
                                    editor.putString("role", userData.getRole());
                                    editor.apply();

                                    Intent intent = new Intent(mContext, DashboardActivity.class);
                                    startActivity(intent);
                                } else {
                                    String error_message = loginResponse.getMessage();

                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "LOGIN FAILED", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }

//    private void saveUserDataToSharedPreferences(UserData user) {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_USER_ID, user.getUserId());
//        editor.putString(KEY_USERNAME, user.getUsername());
//        editor.putString(KEY_EMAIL, user.getEmail());
//        editor.putString(KEY_GENDER, user.getGender());
//
//        editor.apply();
//    }
}
