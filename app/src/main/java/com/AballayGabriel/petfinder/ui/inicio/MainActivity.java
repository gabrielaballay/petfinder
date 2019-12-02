package com.AballayGabriel.petfinder.ui.inicio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.AballayGabriel.petfinder.R;

public class MainActivity extends AppCompatActivity {
    private EditText name,pass;
    //private TextView error;
    private String token;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.etUser);
        pass=findViewById(R.id.etClave);
        //error=findViewById(R.id.error);
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        /*mainViewModel.getError().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {

                error.setVisibility(s);
            }
        });*/
        mainViewModel.getToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("salida","Lega"+name.getText().toString()+" "+pass.getText().toString());
                Intent i=new Intent(getApplicationContext(),MenuUsuarioActivity.class);
                startActivity(i);
            }
        });

    }

    public void ingresar(android.view.View view){
        Log.d("salida",name.getText().toString()+" "+pass.getText().toString());
        mainViewModel.ingresar(name.getText().toString(),pass.getText().toString());
        //Intent i=new Intent(getApplicationContext(),MenuUsuarioActivity.class);
        //startActivity(i);
    }
}
