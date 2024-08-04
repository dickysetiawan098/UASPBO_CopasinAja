package com.unsia.pemrogramanberbasisobjek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.unsia.pemrogramanberbasisobjek.adapter.CopasAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView ListView;
    private Button ButtonSave;

    private CopasAdapter adapter;
    private ArrayList<CopasModel> CopasList;
    DatabaseReference dbCopas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbCopas = FirebaseDatabase.getInstance().getReference("dbcopas");

        ListView = findViewById(R.id.lv_list);
        ButtonSave = findViewById(R.id.ButtonSave);
        ButtonSave.setOnClickListener(this);

        CopasList = new ArrayList<>();
        adapter = new CopasAdapter(this);
        ListView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbCopas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CopasList.clear();

                for (DataSnapshot CopasSnapshot : dataSnapshot.getChildren()) {
                    CopasModel copas = CopasSnapshot.getValue(CopasModel.class);

                    // Tambahkan log untuk memeriksa data
                    Log.d("MainActivity", "ID: " + copas.getId() + ", Isi: " + copas.getIsiText());

                    CopasList.add(copas);
                }

                CopasAdapter adapter = new CopasAdapter(MainActivity.this);
                adapter.setCopasList(CopasList);
                ListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
//        dbCopas.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                CopasList.clear();
//
//                for (DataSnapshot CopasSnapshot : dataSnapshot.getChildren()) {
//                    CopasModel copas = CopasSnapshot.getValue(CopasModel.class);
//                    if (copas != null) {
//                        CopasList.add(copas);
//                    }
//                }
//
//                adapter.setCopasList(CopasList);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(MainActivity.this, "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
    }
//    protected void onStart() {
//        super.onStart();
//
//        dbCopas.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                CopasList.clear();
//
//                for (DataSnapshot CopasSnapshot : dataSnapshot.getChildren()) {
//                    CopasModel copas = CopasSnapshot.getValue(CopasModel.class);
//                    CopasList.add(copas);
//                }
//
//                CopasAdapter adapter = new CopasAdapter(MainActivity.this);
//                adapter.setCopasList(CopasList);
//                ListView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(MainActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ButtonSave) {
            Intent intent = new Intent(MainActivity.this, CreateActivity.class);
            startActivity(intent);
        }
    }
}