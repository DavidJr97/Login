package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.app.ProgressDialog.show;

public class RegistrarActivity extends AppCompatActivity {
    EditText nombres, apellidos, contraseña, emailId;
    Button bnRegistro;
    TextView registro;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.textCorreo);
        contraseña = findViewById(R.id.textContraseña);
        nombres = findViewById(R.id.textNombres);
        registro = findViewById(R.id.textView2);
        apellidos = findViewById(R.id.textApellidos);
        bnRegistro = findViewById(R.id.buttonRegistro);
        bnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nombres.getText().toString();
                String ap = apellidos.getText().toString();
                String email = emailId.getText().toString();
                String pwd = contraseña.getText().toString();


                if(name.isEmpty()) {
                    nombres.setError("Escriba su Nombre");
                    nombres.requestFocus();
                }
                else if(ap.isEmpty()) {
                    apellidos.setError("Escriba sus apellidos");
                    apellidos.requestFocus();
                }
                else if(email.isEmpty()){
                    emailId.setError("Escriba Correo Electrónico");
                    emailId.requestFocus();
                }

                else if(pwd.isEmpty()) {
                    contraseña.setError("Escriba una Contraseña");
                    contraseña.requestFocus();
                }

                else if(name.isEmpty() && ap.isEmpty() && email.isEmpty()  && pwd.isEmpty()){
                    Toast.makeText(RegistrarActivity.this,"Los Campos estan Vacios",Toast.LENGTH_SHORT).show();
                }

                if(!(name.isEmpty() && ap.isEmpty() && email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegistrarActivity.this, "No se Ha Podido Registrar", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(RegistrarActivity.this, MainActivity.class));
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(RegistrarActivity.this, "Ocurrió un Error", Toast.LENGTH_SHORT).show();
                    }
            }






        });
    }




}
