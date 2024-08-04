package com.unsia.pemrogramanberbasisobjek;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    // Abstract method to set up views
    protected abstract void LoadLayout();
}
