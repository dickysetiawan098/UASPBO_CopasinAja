package com.unsia.pemrogramanberbasisobjek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText TextBoxIsi;
    private Button ButtonSave;
    private CopasModel CopasModel;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        TextBoxIsi = findViewById(R.id.TextBoxIsi);
        ButtonSave = findViewById(R.id.ButtonSave);

        ButtonSave.setOnClickListener(this);

        CopasModel = new CopasModel();
    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ButtonSave) {
            SaveIsi();
        }

    }
    private void SaveIsi()
    {
        String Isi = TextBoxIsi.getText().toString().trim();

        boolean isEmptyFields = false;

        if (TextUtils.isEmpty(Isi)) {
            isEmptyFields = true;
            TextBoxIsi.setError("Field ini tidak boleh kosong");
        }

        if (!isEmptyFields) {
            Toast.makeText(CreateActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();

            DatabaseReference DBCopas = mDatabase.child("dbcopas");

            String id = DBCopas.push().getKey();
            CopasModel.setId(id);
            CopasModel.setIsiText(Isi);

            //insert data
            if (id != null) {
                DBCopas.child(id).setValue(CopasModel).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

//    private void SaveIsi()
//    {
//        String Isi = TextBoxIsi.getText().toString().trim();
////        String nim = edtNim.getText().toString().trim();
//
//        boolean isEmptyFields = false;
//
//        if (TextUtils.isEmpty(Isi)) {
//            isEmptyFields = true;
//            TextBoxIsi.setError("Field ini tidak boleh kosong");
//        }
//
//        if (! isEmptyFields) {
//
//            Toast.makeText(CreateActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();
//
//            DatabaseReference DBCopas = mDatabase.child("dbcopas");
//
//            String id = DBCopas.push().getKey();
//            CopasModel.SetId(id);
//            CopasModel.SetIsiText(Isi);
//
//            //insert data
//            DBCopas.child(id).setValue(CopasModel);
//
//            finish();
//
//        }
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_create);
//    }
}