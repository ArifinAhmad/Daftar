package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rsjms.daftar.R;

import org.json.JSONException;
import org.json.JSONObject;

public class detail_riwayat extends AppCompatActivity {
    EditText Tno_rawat, Tnm_poli,Tnm_dokter,Tstatus,Tcara,Tno,Tpesan;
    TextView Ttgl;
    Button back;
    String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat);

        ID = getIntent().getStringExtra("no_rawat");
        ambilRiwayat(ID);

        Tno_rawat = findViewById(R.id.Vnorawat);
        Tnm_poli = findViewById(R.id.VnmaPoli);
        Tnm_dokter = findViewById(R.id.Vnmadokter);
        Tstatus = findViewById(R.id.Vstts);
        Tcara = findViewById(R.id.Vcara);
        Tno = findViewById(R.id.Vnoreg);
        Tpesan = findViewById(R.id.Vpesan);
        Ttgl = findViewById(R.id.textView15);



        back = findViewById(R.id.Back);
        back.setOnClickListener(view -> {
            startActivity(new Intent(detail_riwayat.this, MainActivity.class));
        });
    }

    private void ambilRiwayat(String norawat) {
        String url = "http://103.255.242.71/online/detailriwayat.php?no_rawat="+norawat;

        RequestQueue queue = Volley.newRequestQueue(detail_riwayat.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, response -> {
            try {

                JSONObject jsonObject = response.getJSONObject("data");
                jsonObject.getString("tgl_registrasi");
                Tno_rawat.setText(jsonObject.getString("no_rawat"));
                Tnm_poli.setText(jsonObject.getString("nm_poli"));
                Tnm_dokter.setText(jsonObject.getString("nm_dokter"));
                Tstatus.setText(jsonObject.getString("status_lanjut"));
                Tcara.setText(jsonObject.getString("png_jawab"));
                Tno.setText(jsonObject.getString("no_reg"));
                Ttgl.setText(jsonObject.getString("tgl_registrasi"));


                String noPesan;
                int pesan;
                pesan = Integer.parseInt(String.valueOf(jsonObject.getInt("no_reg")));
                if(pesan <=6){
                    noPesan = "08:00";
                }else if(pesan <=12){
                    noPesan = "09:00";
                }else if(pesan <=18){
                    noPesan = "10:00";
                }else if(pesan <=24){
                    noPesan = "11:00";
                }else if(pesan <=30){
                    noPesan = "12:00";
                }else if(pesan <=36){
                    noPesan = "13:00";
                }else if(pesan <=42){
                    noPesan = "14:00";
                }else if(pesan <=48){
                    noPesan = "15:00";
                }else{
                    noPesan = "16:00";
                }

                Tpesan.setText("Anda Akan Dilayani Pada Kisaran Pukul" +" "+noPesan);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Toast.makeText(detail_riwayat.this, "Fail to get data..", Toast.LENGTH_SHORT).show();

            System.out.print(error.getMessage());
        });
        queue.add(jsonObjectRequest);
    }
}