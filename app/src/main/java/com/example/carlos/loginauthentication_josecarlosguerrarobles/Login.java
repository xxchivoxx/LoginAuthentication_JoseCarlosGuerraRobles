package com.example.carlos.loginauthentication_josecarlosguerrarobles;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText edtCorreo, edtContra;
    Button btnIniciar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContra = findViewById(R.id.edtContra);
        firebaseAuth = FirebaseAuth.getInstance();
        btnIniciar= findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "Please wite...","Processing...");
                firebaseAuth.signInWithEmailAndPassword(edtCorreo.getText().toString().trim(),edtContra.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            Intent sig = new Intent(Login.this, Perfil.class);
                            sig.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());

                            startActivity(sig);
                        }
                        else{
                            Log.e("ERROR",task.getException().toString());
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
