package com.rsjms.daftar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rsjms.daftar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Daftar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText tanggal,txNama,txNorm,iddokter,idCara;
    TextView rm,nama;
    Button klikOk;
    String ambilData,ambilTanggal;
    DatePickerDialog.OnDateSetListener setListener;
    Spinner spinnerPoli;
    Spinner spinnerDokter;
    Spinner spinerCaraBayar;
    Spinner spinIdpoli;
    Spinner spinnerIdDokter;
    Spinner spinIdCara;
    ArrayList<String> poliList = new ArrayList<>();
    ArrayList<String> dokterList = new ArrayList<>();
    ArrayList<String> carabayarList = new ArrayList<>();
    ArrayList<String> idpolilist = new ArrayList<>();
    ArrayList<String> iddokterlist = new ArrayList<>();
    ArrayList<String> idcaralist = new ArrayList<>();
    ArrayAdapter<String> poliAdapter;
    ArrayAdapter<String> dokterAdapter;
    ArrayAdapter<String> caraBayarAdapter;
    ArrayAdapter<String> idpoliAdapter;
    ArrayAdapter<String> idDokterAdapter;
    ArrayAdapter<String> idCaraAdapter;

    RequestQueue requestQueue;
    SessionManager sessionManager;
    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        tanggal = findViewById(R.id.tgl);
        txNorm = findViewById(R.id.txno_rm);
        spinnerPoli = findViewById(R.id.spinpilihPoli);
        spinnerDokter = findViewById(R.id.spinDokter);
        spinnerIdDokter = findViewById(R.id.spinIdDokter);
        spinerCaraBayar = findViewById(R.id.spinCarabayar);
        spinIdpoli = findViewById(R.id.idspinPOLI);
        spinIdCara = findViewById(R.id.idspinCara);
        klikOk = findViewById(R.id.klikDaftar);
        rm = findViewById(R.id.textView14);
        nama = findViewById(R.id.textView15);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.userDetail();
        String mUsername = user.get(SessionManager.USERNAME);
        String mName = user.get(SessionManager.NAME);
        txNorm.setText(mUsername);
        rm.setText(mUsername);
        nama.setText(mName);

        Intent intent = getIntent();
        ambilData = intent.getStringExtra("tanggal");
        tanggal.setText(ambilData);

        tampilCaraBayar();
        jadwalDokter(ambilData);

        klikOk.setOnClickListener(view -> {

            if(tanggal.getText().toString().length() == 0){
                tanggal.setError("Tanggal Kosong");
            }if(spinnerPoli.getCount()==0){
                Toast.makeText(getApplicationContext(), "Poli Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Daftar.this, Daftar2.class));
            }else {
                daftarpasien();

            }
        });

    }

    private void jadwalDokter(String tgl){
        requestQueue = Volley.newRequestQueue(this);

        String url = "http://103.255.242.71/online/carijadwal.php?tgl_registrasi="+tgl;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, response -> {
                    try{
                        JSONArray jsonArray = response.getJSONArray("data");
                        for(int i=0; i <jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String pilihPoli = jsonObject.optString("nm_poli");
                            String idPoli = jsonObject.optString("kd_poli");
                            poliList.add(pilihPoli);

                            poliAdapter = new ArrayAdapter<>(Daftar.this,
                                    android.R.layout.simple_spinner_item, poliList);

                            poliAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinnerPoli.setAdapter(poliAdapter);



                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinnerPoli.setOnItemSelectedListener(this);

    }


    public void tampilCaraBayar(){
        String url ="http://103.255.242.71/online/carabayar.php";
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String caraBayar = jsonObject.optString("png_jawab");

                        carabayarList.add(caraBayar);
                        caraBayarAdapter = new ArrayAdapter<>(Daftar.this,
                                android.R.layout.simple_spinner_item, carabayarList);

                        caraBayarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinerCaraBayar.setAdapter(caraBayarAdapter);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinerCaraBayar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getId() == R.id.spinCarabayar) {
                    String text = parent.getItemAtPosition(position).toString();
                    cariID(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void cariID(String idcara){
        idcaralist.clear();
        String url = "http://103.255.242.71/online/carabayar2.php?png_jawab="+idcara;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray1 = response.getJSONArray("data");
                    for(int a=0;a<jsonArray1.length();a++){
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(a);
                        String idCara = jsonObject1.optString("kd_pj");

                        idcaralist.add(idCara);
                        idCaraAdapter = new ArrayAdapter<>(Daftar.this,
                                android.R.layout.simple_spinner_item, idcaralist);

                        idCaraAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinIdCara.setAdapter(idCaraAdapter);
                    }

                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest1);

    }

    private void daftarpasien() {
        String url ="http://103.255.242.71/online/pendaftaran.php";
        final String tgl = tanggal.getText().toString().trim();
        final String pol = spinIdpoli.getSelectedItem().toString();
        final String dok = spinnerIdDokter.getSelectedItem().toString();
        final String cara = spinIdCara.getSelectedItem().toString();
        final String rkm = txNorm.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang Proses");

        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("pesan");
                        if(status.equals("Berhasil Terdaftar")){

                            AlertDialog alertDialog = new AlertDialog.Builder(Daftar.this)
                                    .setTitle("Pesan")
                                    .setMessage("Anda Berhasil Daftar")
                                            .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    startActivity(new Intent(Daftar.this, HasilDaftar.class));
                                                }
                                            })
                                    .show();

                           /* new SweetAlertDialog(Daftar.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Berhasil")
                                    .setContentText("Anda Berhasil Daftar")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            startActivity(new Intent(Daftar.this, HasilDaftar.class));
                                        }
                                    })
                                    .show();*/
                            progressDialog.dismiss();


                        }else if(status.equals("Sudah Terdaftar")){

                            AlertDialog alertDialog = new AlertDialog.Builder(Daftar.this)
                                    .setTitle("Pesan")
                                    .setMessage("Maaf Anda Sudah  Terdaftar, Silahkan Cek Riwayat Anda")
                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Daftar.this, riwayat_pasien.class));
                                        }
                                    })
                                    .show();


                             progressDialog.dismiss();

                        }else if(status.equals("Jam Lewat")){

                            AlertDialog alertDialog = new AlertDialog.Builder(Daftar.this)
                                    .setTitle("Pesan")
                                    .setMessage("Maaf, Pendaftaran Sudah Lewat, Silahkan Pilih Tanggal Lain")
                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Daftar.this, Daftar2.class));
                                        }
                                    })
                                    .show();


                            progressDialog.dismiss();

                        }else if(status.equals("Lewat")){

                            AlertDialog alertDialog = new AlertDialog.Builder(Daftar.this)
                                    .setTitle("Pesan")
                                    .setMessage("Pendaftaran Sudah Lewat, Silahkan Pilih Tanggal Lain")
                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Daftar.this, Daftar2.class));
                                        }
                                    })
                                    .show();


                            progressDialog.dismiss();

                        }else if(status.equals("Hari Lebih")){
                            AlertDialog alertDialog = new AlertDialog.Builder(Daftar.this)
                                    .setTitle("Pesan")
                                    .setMessage("Maaf, Pendaftaran, Maksimal 3 Hari Kedepan, Silahkan Pilih Tanggal Lain")
                                    .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Daftar.this, Daftar2.class));
                                        }
                                    })
                                    .show();


                            progressDialog.dismiss();

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }


                }, error -> {
            Toast.makeText(Daftar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tgl_registrasi" , tgl);
                params.put("kd_poli" , pol);
                params.put("kd_dokter" , dok);
                params.put("kd_pj" , cara);
                params.put("no_rkm_medis" , rkm);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Daftar.this);
        requestQueue.add(request);


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(adapterView.getId() == R.id.spinpilihPoli){
            dokterList.clear();
            idpolilist.clear();
            iddokterlist.clear();
            String selectPoli = adapterView.getSelectedItem().toString();
            String url = "http://103.255.242.71/online/namapoli.php?nm_poli="+selectPoli;
            requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String nmDokter = jsonObject.optString("nm_dokter");
                            String idPoli = jsonObject.optString("kd_poli");
                            String idDokter = jsonObject.optString("kd_dokter");

                            dokterList.add(nmDokter);
                            idpolilist.add(idPoli);
                            iddokterlist.add(idDokter);

                            dokterAdapter = new ArrayAdapter<>(Daftar.this,
                                    android.R.layout.simple_spinner_item, dokterList);
                            idpoliAdapter = new ArrayAdapter<>(Daftar.this,
                                    android.R.layout.simple_spinner_item, idpolilist);
                            idDokterAdapter = new ArrayAdapter<>(Daftar.this,
                                    android.R.layout.simple_spinner_item, iddokterlist);

                            idDokterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            idpoliAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            dokterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                           /* idDokterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
                            spinnerDokter.setAdapter(dokterAdapter);
                            spinIdpoli.setAdapter(idpoliAdapter);
                            spinnerIdDokter.setAdapter(idDokterAdapter);


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            requestQueue.add(jsonObjectRequest);
            spinnerDokter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(parent.getId() == R.id.spinDokter) {
                        String text = parent.getItemAtPosition(position).toString();
                        cariIDdokter(text);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void cariIDdokter(String iddokter){
        iddokterlist.clear();
        String url = "http://103.255.242.71/online/cariiddokter.php?nm_dokter="+iddokter;
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray1 = response.getJSONArray("data");
                    for(int a=0;a<jsonArray1.length();a++){
                        JSONObject jsonObject1 = jsonArray1.getJSONObject(a);
                        String idDokter = jsonObject1.optString("kd_dokter");

                        iddokterlist.add(idDokter);
                        idDokterAdapter = new ArrayAdapter<>(Daftar.this,
                                android.R.layout.simple_spinner_item, iddokterlist);

                        idDokterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerIdDokter.setAdapter(idDokterAdapter);
                    }

                }catch (JSONException e){
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest1);

    }








}