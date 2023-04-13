package com.rsjms.daftar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsjms.daftar.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private TextView username,name,akun,alamat;
    BottomNavigationView bottomNavigationView;
    ImageButton logout;
    ImageView fb,ig,web;
    AlertDialog.Builder dialog;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        username = (TextView) findViewById(R.id.infoUser);
        name = (TextView) findViewById(R.id.infoNORM);

        alamat = (TextView) findViewById(R.id.infoAlamat);
        HashMap<String, String> user = sessionManager.userDetail();
        String mUsername = user.get(SessionManager.USERNAME);
        String mName = user.get(SessionManager.NAME);
        String mAlamat = user.get(SessionManager.ALAMAT);

        username.setText(mName);
        name.setText(mUsername);

        alamat.setText(mAlamat);

        CardView btnDaftar = findViewById(R.id.pendftaran);
        CardView btnJadwal = findViewById(R.id.jadwal);
        CardView btnRiwayat = findViewById(R.id.riwayat);
        CardView btnkamar = findViewById(R.id.infokamar);
        CardView btnInfo = findViewById(R.id.informasi);
        CardView btnPengaduan = findViewById(R.id.pengaduan);

        btnJadwal.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, jadwal_dokter.class)));
        btnRiwayat.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,riwayat_pasien.class)));
        btnkamar.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,info_kamar.class)));
        btnDaftar.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,Daftar2.class)));
        btnInfo.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,informasi.class)));
        btnPengaduan.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,pengaduan.class)));


        logout = findViewById(R.id.idlogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Logout")
                        .setMessage("Apakah Anda Yakin Keluar")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences preferences = getSharedPreferences(login.SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("name","false");
                                editor.apply();
                                Intent intent = new Intent(MainActivity.this, login.class);
                                startActivity(intent);
                                sessionManager.logout();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();




            /*    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Apakah Anda Yakin ?")
                        .setContentText("Anda Akan Keluar Dari Halaman ini")
                        .setConfirmText("Logout")

                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {

                                SharedPreferences preferences = getSharedPreferences(login.SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("name","false");
                                editor.apply();
                                Intent intent = new Intent(MainActivity.this, login.class);
                                startActivity(intent);
                                sessionManager.logout();
                            }
                        })
                        .show();*/

            }
        });

        fb = findViewById(R.id.lineFB);
        ig = findViewById(R.id.lineIG);
        web = findViewById(R.id.lineWEB);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://m.facebook.com/100063478933677/");
            }
        });

        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://instagram.com/rsj_mutiarasukma?igshid=YmMyMTA2M2Y=");
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoURL("https://rsjmutiarasukma.ntbprov.go.id/");
            }
        });

    }

    private void gotoURL(String shareURL) {
        Uri uri = Uri.parse(shareURL);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


}