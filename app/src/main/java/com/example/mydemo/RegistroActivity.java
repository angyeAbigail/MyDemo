package com.example.mydemo;

import android.app.ProgressDialog;
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

public class RegistroActivity extends AppCompatActivity {

    EditText txtEmail;
    EditText txtPassword;
    Button txtRegister;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        progressDialog=new ProgressDialog(this);
        //instancias igualmente que en el login
        auth=FirebaseAuth.getInstance();
        //instanciamos
        txtEmail=(EditText)findViewById(R.id.email);
        txtPassword=(EditText)findViewById(R.id.password);
        txtRegister=(Button)findViewById(R.id.register);
        //ahora le damos accion al botton registrer
        txtRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //porque declaramos string?mporque el metodo para registrar los estara esperando
                String userE=txtEmail.getText().toString();
                String passE=txtPassword.getText().toString();

                if(TextUtils.isEmpty(userE)){
                    Toast.makeText(RegistroActivity.this, "Ingrese correo", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(userE)){
                    Toast.makeText(RegistroActivity.this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("en proceso");
                progressDialog.show();

                //ahora usamos el metodo
                auth.createUserWithEmailAndPassword(userE,passE)
                        //le pasamos el contexto, en este caso la clase registro
                        .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //task=tareas, devuelve si la tarea se cumple o no, en el cual si se cumple

                                Toast.makeText(RegistroActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                                //en este caso si no logra registrarse
                                if(!task.isSuccessful()){
                                    Toast.makeText(RegistroActivity.this, "No se ha podido registrar", Toast.LENGTH_SHORT).show();
                                }
                                //una vez registradop debe pasarse a la actividad principal
                                Intent intent= new Intent(RegistroActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });

            }
        });
    }
}
