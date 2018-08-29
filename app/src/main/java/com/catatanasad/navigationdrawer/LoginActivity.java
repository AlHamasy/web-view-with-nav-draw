package com.catatanasad.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtLogin;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogin = findViewById(R.id.edt_login);
        btnLogin = findViewById(R.id.btn_login);

        // todo panggil kelas preference
        final Preference preference = new Preference(LoginActivity.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = edtLogin.getText().toString();

                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(LoginActivity.this, "Harap isi nama", Toast.LENGTH_SHORT).show();
                }
                else {
                    preference.setNama(nama);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }


            }
        });

    }

    // todo membuat class preference
    public static class Preference{

        String KEY_NAME = "NAMA";
        String PREF_NAME = "SIMPAN";
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        // todo membuat constructor
        public Preference(Context context){
            sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        }

        // todo method get nama
        public String getNama(){
            return sharedPreferences.getString(KEY_NAME, null);
        }

        // todo method set nama
        public void setNama(String nama){
            editor = sharedPreferences.edit();
            editor.putString(KEY_NAME, nama).apply();
        }

        // todo method untuk logout
        public void logout(){
            editor = sharedPreferences.edit();
            editor.clear().commit();
        }

    }
}











