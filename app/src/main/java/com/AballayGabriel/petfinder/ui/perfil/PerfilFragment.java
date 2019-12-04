package com.AballayGabriel.petfinder.ui.perfil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Usuario;
import com.AballayGabriel.petfinder.ui.inicio.MainActivity;
import com.AballayGabriel.petfinder.ui.inicio.MenuUsuarioActivity;

import java.security.Principal;

public class PerfilFragment extends Fragment {
    private EditText apellido,nombre,ciudad,direccion,telefono,email,clave,provincia;
    Button editar, guardar;
    private PerfilViewModel perfilViewModel;
    private Usuario usuarioVisto;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        inicializar(root);

        perfilViewModel=ViewModelProviders.of(this).get(PerfilViewModel.class);

        perfilViewModel.leer();

        perfilViewModel.getUsuarioMutableLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {

                usuarioVisto=usuario;
                fijarDatos(usuario);
            }
        });




        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setTitle("").setMessage("Desea guardar los datos?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aceptar();

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fijarDatos(usuarioVisto);
                    }
                }).show();
            }
        });
        return root;
    }

    public void editar(){
        apellido.setEnabled(true);
        nombre.setEnabled(true);
        ciudad.setEnabled(true);
        direccion.setEnabled(true);
        telefono.setEnabled(true);
        email.setEnabled(true);
        provincia.setEnabled(true);

        editar.setVisibility(View.GONE);
        guardar.setVisibility(View.VISIBLE);

    }

    public void aceptar(){

        usuarioVisto.setApellido(apellido.getText().toString());
        usuarioVisto.setNombre(nombre.getText().toString());
        usuarioVisto.setCiudad(ciudad.getText().toString());
        usuarioVisto.setDireccion(direccion.getText().toString());
        usuarioVisto.setTelefono(telefono.getText().toString());
        usuarioVisto.setEmail(email.getText().toString());
        usuarioVisto.setClave(clave.getText().toString());
        usuarioVisto.setEstado(1);
        usuarioVisto.setProvinciaId(Integer.parseInt(provincia.getText().toString()));
        perfilViewModel.actualizar(usuarioVisto);
        fijarDatos(usuarioVisto);
    }

    public void fijarDatos(Usuario sesion){
        apellido.setText(String.valueOf(sesion.getApellido()));
        nombre.setText(String.valueOf(sesion.getNombre()));
        ciudad.setText(String.valueOf(sesion.getCiudad()));
        direccion.setText(String.valueOf(sesion.getDireccion()));
        telefono.setText(String.valueOf(sesion.getTelefono()));
        email.setText(String.valueOf(sesion.getEmail()));
        clave.setText(String.valueOf(sesion.getClave()));
        provincia.setText(String.valueOf(sesion.getProvinciaId()));
        Log.d("salida",clave.getText()+"");

        apellido.setEnabled(false);
        nombre.setEnabled(false);
        ciudad.setEnabled(false);
        direccion.setEnabled(false);
        telefono.setEnabled(false);
        email.setEnabled(false);
        provincia.setEnabled(false);

        editar.setVisibility(View.VISIBLE);
        guardar.setVisibility(View.GONE);

    }

    public void inicializar(View view){
        apellido=view.findViewById(R.id.etApellido);
        nombre=view.findViewById(R.id.etNombre);
        ciudad=view.findViewById(R.id.etCiudad);
        direccion=view.findViewById(R.id.etDireccion);
        telefono=view.findViewById(R.id.edTelefono);
        email=view.findViewById(R.id.etMail);
        clave=view.findViewById(R.id.etPass);
        provincia=view.findViewById(R.id.etProvincia);

        guardar=view.findViewById(R.id.btnGuardar);
        editar=view.findViewById(R.id.btnEditar);
    }
}