package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailId, pwds;
    Button btningreso, btnregistro;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.textemail);
        pwds= findViewById(R.id.textContra);
        btningreso= findViewById(R.id.buttonIngresar);
        btnregistro= findViewById(R.id.buttonRegistrar);

        mAuthStateListener = new FirebaseAuth.AuthStateListener()  {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent( MainActivity.this, HomeActivity.class);
                    startActivity(i);
            }
                else{
                    Toast.makeText(MainActivity.this, "Por Favor Iniciar Sesión", Toast.LENGTH_SHORT).show();
                }


                }


        };
        btningreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = pwds.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Escriba Correo Electrónico");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()) {
                    pwds.setError("Escriba una Contraseña");
                    pwds.requestFocus();
                }

                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Los Campos estan Vacios",Toast.LENGTH_SHORT).show();
                }

                else if(!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if (!task.isSuccessful()){
                              Toast.makeText(MainActivity.this, "Inicio de Sesión Errónea, Intente de Nuevo", Toast.LENGTH_SHORT).show();
                          }
                          else{
                              Intent intToHome = new Intent( MainActivity.this, HomeActivity.class);
                              startActivity(intToHome);
                          }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Ocurrió un Error", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (v.getContext(), RegistrarActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}

