package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rsjms.daftar.R;

public class pengaduan extends AppCompatActivity {
    LinearLayout telepon,wa,sms;
    String number = "087865178666";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);

        telepon = findViewById(R.id.telepon);
        wa = findViewById(R.id.line1);
        sms = findViewById(R.id.line2);
        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = number.replace("+","").replace("","");
                Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid",number + "@s.whatsapp.net");
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                if(sendIntent.resolveActivity(getPackageManager()) ==null){
                    Toast.makeText(pengaduan.this, "Silahkan Install Whatsapp Dulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(Intent.createChooser(sendIntent, "Pilih Aplikasi :"));

            }
        });

        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String number = "087865178666";
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);

            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+ "rsjmutiarasukma@gmail.com"));
                startActivity(Intent.createChooser(intent, "Silahkan Pilih Aplikasi yang digunakan"));

            }
        });


    }

}