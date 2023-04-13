package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rsjms.daftar.Model.jadwalModel;

import com.rsjms.daftar.adapter.JadwalAdafter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jadwal_dokter extends AppCompatActivity {
    private ArrayList<jadwalModel> JadwalModelsArray = new ArrayList<>();
    RecyclerView rv_data;
    List<jadwalModel> jadwals;
    JadwalAdafter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dokter);

        rv_data = findViewById(R.id.rv_data_jadwal);
        jadwals = new ArrayList<>();
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(adapter);

        getData3();

    }


    private void getData3() {
        String url = "http://103.255.242.71/online/jadwal_dokter.php";
        RequestQueue queue = Volley.newRequestQueue(jadwal_dokter.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        JadwalModelsArray.add(new jadwalModel(jsonObject.getString("nm_dokter"),
                                jsonObject.getString("nm_poli"),
                                jsonObject.getString("jam_mulai"),
                                jsonObject.getString("jam_selesai")));

                        adapter = new JadwalAdafter(JadwalModelsArray,jadwal_dokter.this);
                        rv_data.setLayoutManager(new LinearLayoutManager(jadwal_dokter.this));

                        rv_data.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(jadwal_dokter.this, "Maaf ini Hari Libur", Toast.LENGTH_SHORT).show();
                System.out.print(error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);

    }

}