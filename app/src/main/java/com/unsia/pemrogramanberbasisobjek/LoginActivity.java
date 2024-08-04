package com.unsia.pemrogramanberbasisobjek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.unsia.pemrogramanberbasisobjek.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText TextBoxUsername;
    private EditText TextBoxPassword;

    private Button ButtonLogind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoadLayout();
    }
    protected void LoadLayout() {

        TextBoxUsername = findViewById(R.id.TextBoxUsername);
        TextBoxPassword = findViewById(R.id.TextBoxPassword);
        ButtonLogind = findViewById(R.id.ButtonLogind);

        ButtonLogind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = TextBoxUsername.getText().toString();
                String password = TextBoxPassword.getText().toString();

                if (username.equals("demo") && password.equals("demo")) {
                    Log.d("LoginActivity", "Credentials are correct. Navigating to DashboardActivity.");
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Selamat datang!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Maaf, Username dan Password yang anda masukan salah! mohon ulangi kembali", Toast.LENGTH_SHORT).show();
                    Log.d("LoginActivity", "Invalid credentials.");
                }
            }
        });

//        ButtonLogind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TextBoxUsername.getText().toString().equals("demo") &&
//                        TextBoxPassword.getText().toString().equals("demo")) {
//                    Intent intent = new Intent(LoginActivity.this,
//                            DashboardActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        });
    }
}