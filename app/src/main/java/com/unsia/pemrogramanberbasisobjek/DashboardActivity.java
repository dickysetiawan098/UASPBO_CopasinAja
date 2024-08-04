package com.unsia.pemrogramanberbasisobjek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class DashboardActivity extends BaseActivity {

    private Button ButtonRiwayatCopas;
    private ListView ListView;
    private CopasAdapter adapter;
    private ArrayList<CopasModel> CopasList;
    DatabaseReference dbCopas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        LoadLayout();
    }

    @Override
    protected void LoadLayout() {
        ButtonRiwayatCopas = findViewById(R.id.ButtonRiwayatCopas);
        ListView = findViewById(R.id.lv_list);

        ButtonRiwayatCopas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        CopasList = new ArrayList<>();
        adapter = new CopasAdapter(this);
        ListView.setAdapter(adapter);

        dbCopas = FirebaseDatabase.getInstance().getReference("dbcopas");

        dbCopas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CopasList.clear();

                for (DataSnapshot CopasSnapshot : dataSnapshot.getChildren()) {
                    CopasModel copas = CopasSnapshot.getValue(CopasModel.class);
                    if (copas != null) {
                        CopasList.add(copas);
                    }
                }

                adapter.setCopasList(CopasList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DashboardActivity.this, "Terjadi kesalahan: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CopasModel selectedCopas = CopasList.get(position);
                copyToClipboard(selectedCopas.getIsiText());
                Toast.makeText(DashboardActivity.this, "Teks sudah di salin", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void copyToClipboard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Teks sudah disalin", text);
        clipboard.setPrimaryClip(clip);
    }
}
