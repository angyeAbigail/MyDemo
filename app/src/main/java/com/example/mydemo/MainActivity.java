package com.example.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText edtNombre, edtEdad, editText;
    Spinner spnGaseosa;
    Button btnGuardar;

    DatabaseReference DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB= FirebaseDatabase.getInstance().getReference();

        edtNombre=(EditText)findViewById(R.id.nombre);
        edtEdad=(EditText)findViewById(R.id.edad);
        spnGaseosa=(Spinner)findViewById(R.id.gaseosa);
        btnGuardar=(Button)findViewById(R.id.grabar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name=edtNombre.getText().toString();
                String age=edtEdad.getText().toString();
                String soda=spnGaseosa.getSelectedItem().toString();

                String id=DB.push().getKey();

                clase d=new clase(id,name,age,soda);

                DB.child("usuario").child(id).setValue(d);

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this, "ingresa nombre", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(age)){
                    Toast.makeText(MainActivity.this, "ingresa edad", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

        });
    }

}
