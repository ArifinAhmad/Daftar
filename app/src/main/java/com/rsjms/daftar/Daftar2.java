package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.rsjms.daftar.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Daftar2 extends AppCompatActivity {
    SessionManager sessionManager;
    EditText EdNama,EdNo,tanggal;
    TextView rm,nama;
    Button pilih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar2);
        tanggal = (EditText) findViewById(R.id.tgl2);
        EdNama = (EditText) findViewById(R.id.pilihSatu);
        EdNo = (EditText) findViewById(R.id.pilihDua);
        pilih = (Button) findViewById(R.id.pilihPoli);
        rm = (TextView) findViewById(R.id.textView14);
        nama = (TextView) findViewById(R.id.textView15);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.userDetail();
        String mUsername = user.get(SessionManager.USERNAME);
        String mName = user.get(SessionManager.NAME);
        EdNama.setText(mName);
        EdNo.setText(mUsername);
        rm.setText(mUsername);
        nama.setText(mName);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Daftar2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        calendar.set(year,month,day);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = dateFormat.format(calendar.getTime());
                        tanggal.setText(dateString);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();

            }

        });

        pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tanggal.getText().toString().length() == 0){
                    tanggal.setError("Tanggal Kunjungan Harus Diisi");
                }else {
                    String ambilNorm = EdNo.getText().toString();
                    String tanggal2 = tanggal.getText().toString();
                    Intent intent = new Intent(Daftar2.this, Daftar.class);
                    intent.putExtra("no_rkm_medis", ambilNorm);
                    intent.putExtra("tanggal", tanggal2);


                    Daftar2.this.startActivity(intent);
                }
            }
        });

    }
}