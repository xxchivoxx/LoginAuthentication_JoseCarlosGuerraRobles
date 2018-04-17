package com.example.carlos.loginauthentication_josecarlosguerrarobles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {
    TextView txtCorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        txtCorreo = findViewById(R.id.txtCorreo);

        txtCorreo.setText(getIntent().getExtras().getString("Email"));
    }
}
