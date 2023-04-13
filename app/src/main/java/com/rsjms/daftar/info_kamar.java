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
import com.rsjms.daftar.Model.KamarModel;

import com.rsjms.daftar.adapter.KamarAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class info_kamar extends AppCompatActivity {
    private ArrayList<KamarModel> kamarModelArrayList = new ArrayList<>();
    RecyclerView rv_data_kamar;
    KamarAdapter adapter;
    List<KamarModel> kamars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kamar);


        rv_data_kamar = findViewById(R.id.rv_data_kamar);
        kamars = new ArrayList<>();
        rv_data_kamar.setLayoutManager(new LinearLayoutManager(this));
        rv_data_kamar.setAdapter(adapter);

        getDataKamar();
    }


    private void getDataKamar() {
        String url = "http://103.255.242.71/online/infobed.php";
        RequestQueue queue = Volley.newRequestQueue(info_kamar.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i=0;i<dataArray.length(); i++){
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        kamarModelArrayList.add(new KamarModel(jsonObject.getString("nm_bangsal"),
                                jsonObject.getString("jumlah"),
                                jsonObject.getString("isi"),
                                jsonObject.getString("kosong")));
                        adapter = new KamarAdapter(kamarModelArrayList,info_kamar.this );
                        rv_data_kamar.setLayoutManager(new LinearLayoutManager(info_kamar.this));
                        rv_data_kamar.setAdapter(adapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(info_kamar.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
                System.out.print(error.getMessage());
            }
        });
        queue.add(jsonObjectRequest);

    }
}