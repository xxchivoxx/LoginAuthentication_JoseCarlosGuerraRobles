package com.example.carlos.loginauthentication_josecarlosguerrarobles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Registro extends AppCompatActivity {
    EditText edtCorreo, edtContra;
    Button btnRegistrar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContra = findViewById(R.id.edtContra);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        firebaseAuth = FirebaseAuth.getInstance();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var = edtCorreo.getText().toString().trim();
                final ProgressDialog progressDialog = ProgressDialog.show(Registro.this, "Please wite...","Processing...");
                firebaseAuth.createUserWithEmailAndPassword(var,edtContra.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            Intent sig = new Intent(Registro.this, Login.class);
                            //sig.putExtra("Email",edtCorreo.getText().toString());
                            startActivity(sig);
                        }
                        else{
                            Log.e("ERROR",task.getException().toString());
                            Toast.makeText(Registro.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
