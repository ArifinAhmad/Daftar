package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class pilihmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihmenu);

        CardView btnPoli = findViewById(R.id.cardBtnPoli);
        CardView btnSuket = findViewById(R.id.cardBtnSuket);
        CardView btnInfo = findViewById(R.id.cardBtnInfo);

        btnPoli.setOnClickListener(view -> startActivity(new Intent(pilihmenu.this, login.class)));
        btnSuket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(pilihmenu.this, "Maaf Menu ini belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });
        btnInfo.setOnClickListener(view -> startActivity(new Intent(pilihmenu.this, informasi.class)));


    }
}