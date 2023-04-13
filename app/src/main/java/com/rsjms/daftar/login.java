package com.rsjms.daftar;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rsjms.daftar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;




public class login extends AppCompatActivity {

    EditText username;
    EditText password;
    ProgressDialog pDialog;
    Button signin;
    Context context;
    CheckBox checPass,remember;
    SessionManager sessionManager;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = login.this;

        sessionManager = new SessionManager(this);

//        checkbox();
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        String check = sharedPreferences.getString("name","");
//        if(check.equals("true")){
//            Intent intent = new Intent(login.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        pDialog = new ProgressDialog(context);
        username = findViewById(R.id.txNorm);
        password = findViewById(R.id.txNIK);
        signin = findViewById(R.id.btnSignin);
        remember = findViewById(R.id.checkRememmber);
        checPass = (CheckBox) findViewById(R.id.checkPass);
        checPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(!isChecked){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String checkbox = preferences.getString("name","");
        if(checkbox.equals("true")){
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else if(checkbox.equals("false")){

        }

        signin.setOnClickListener(v -> {
            if(username.getText().toString().length() == 0){
                username.setError("No RM Tidak Boleh Kosong");
            }
            if(password.getText().toString().length() == 0){
                password.setError("NIK Tidak Boleh Kosong");
            }else {
                loginSistem();

            }
        });



        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("check", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name","true");
                    editor.apply();
                    Toast.makeText(login.this,"Checked", Toast.LENGTH_SHORT).show();
                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("check", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("name","false");
                    editor.apply();
                    Toast.makeText(login.this,"UnChecked", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    private void checkbox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        if(check.equals("true")){
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(check.equals("false")){
            Toast.makeText(login.this,"Login Dulu", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginSistem() {
        String url= "http://103.255.242.71/online/login.php";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("pesan");

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        if(status.equals("ok")){
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                String userName = object.getString("username");
                                String Name = object.getString("nm_pasien");
                                String Alamat = object.getString("alamat");

                                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name","true");
                                editor.apply();

                                sessionManager.createSession(userName,Name,Alamat);

                                Intent intent = new Intent(login.this, MainActivity.class);
                                startActivity(intent);
                                finish();


                            }

                        }else {
                            Toast.makeText(context, "Username dan Password Salah", Toast.LENGTH_SHORT).show();


                        }

                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(context, "Username dan Password Salah", Toast.LENGTH_SHORT).show();

                    }

                }, error -> Toast.makeText(context,"Gagal Login", Toast.LENGTH_LONG).show()){

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username.getText().toString().trim());
                params.put("password", password.getText().toString().trim());

                return params;

            }
        };

        RequestQueue queue = Volley.newRequestQueue(login.this);
        queue.add(stringRequest);
        pDialog.dismiss();

    }
}