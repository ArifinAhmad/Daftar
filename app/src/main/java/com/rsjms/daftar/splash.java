package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.rsjms.daftar.R;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {




            }
        },3000);

        new Handler().postDelayed(() -> {
            ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                Toast.makeText(getApplicationContext(),"Terhubung Dengan Internet",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(splash.this,pilihmenu.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Cek Koneksi Internet Anda", Toast.LENGTH_LONG).show();
            }

        },100);

    }
}