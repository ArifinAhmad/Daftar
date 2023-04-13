package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rsjms.daftar.Model.RiwayatModel;
import com.rsjms.daftar.R;
import com.rsjms.daftar.adapter.RiwayatAdafter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class riwayat_pasien extends AppCompatActivity {
    SessionManager sessionManager;
    private TextView nNama;
    ListView list;
    private final ArrayList<RiwayatModel> riwayatModelArrayList = new ArrayList<>();
    RecyclerView data_riwayat;
    List<RiwayatModel> riwayatModels;
    RiwayatAdafter adafter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pasien);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.userDetail();
        String mUsername = user.get(SessionManager.USERNAME);
        String mName = user.get(SessionManager.NAME);

        data_riwayat = findViewById(R.id.rv_riwayat);
        riwayatModels = new ArrayList<>();
        data_riwayat.setLayoutManager(new LinearLayoutManager(this));
        data_riwayat.setAdapter(adafter);


        ambilRiwayat(mUsername);

    }

    private void ambilRiwayat(String username) {
        String url = "http://103.255.242.71/online/riwayat.php?no_rkm_medis="+ username;

        RequestQueue queue = Volley.newRequestQueue(riwayat_pasien.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray dataArray = response.getJSONArray("data");
                            for(int i=0; i < dataArray.length();i++){
                                JSONObject jsonObject = dataArray.getJSONObject(i);
                                riwayatModelArrayList.add(new RiwayatModel(jsonObject.getString("tgl_registrasi"),
                                        jsonObject.getString("no_rawat"),
                                        jsonObject.getString("nm_poli"),
                                        jsonObject.getString("nm_dokter"),
                                        jsonObject.getString("status_lanjut"),
                                        jsonObject.getString("png_jawab"),
                                        jsonObject.getString("no_reg")));
                                adafter = new RiwayatAdafter(riwayatModelArrayList, riwayat_pasien.this);
                                data_riwayat.setLayoutManager(new LinearLayoutManager(riwayat_pasien.this));


                                data_riwayat.setAdapter(adafter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(riwayat_pasien.this, "Tidak Ada Riwayat", Toast.LENGTH_SHORT).show();
                System.out.print(error.getMessage());

            }
        });

        queue.add(jsonObjectRequest);

    }
}