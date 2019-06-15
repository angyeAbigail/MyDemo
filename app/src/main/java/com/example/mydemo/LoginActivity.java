package com.example.mydemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail;
    EditText txtPassword;
    Button btnLogin;
    Button btnRegistrar;

    //llamando al autenticador
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //instacias
        auth=FirebaseAuth.getInstance();

        txtEmail=(EditText)findViewById(R.id.email);
        txtPassword=(EditText)findViewById(R.id.password);
        btnLogin=(Button)findViewById(R.id.login);
        btnRegistrar=(Button)findViewById(R.id.register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userE= txtEmail.getText().toString();
                String passE= txtPassword.getText().toString();
                //ahora vamos a validar por su uno de los campos esta vacio
                if(TextUtils.isEmpty(userE)){
                    //si falta el correo
                    Toast.makeText(LoginActivity.this, "inserte correo", Toast.LENGTH_SHORT).show();
                    return;
                }
                //si falta contraseña
                if (TextUtils.isEmpty(passE)){
                    Toast.makeText(LoginActivity.this, "Inserte Contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }
                //ahora usaremos el auth para que se loguee una vez registrado
                auth.signInWithEmailAndPassword(userE,passE)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "A ocurrido un horor...", Toast.LENGTH_SHORT).show();
                                }else{

                                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
            }
        });

        //ahora le damos accion al botton registro

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //este boton nos manda a la actividad de registro asi que crearemos un intent
                Intent intent = new Intent(LoginActivity.this,RegistroActivity.class);
                startActivity(intent);
            }
        });

    }
}
