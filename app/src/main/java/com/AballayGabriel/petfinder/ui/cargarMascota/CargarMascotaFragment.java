package com.AballayGabriel.petfinder.ui.cargarMascota;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.model.Recompensa;
import com.AballayGabriel.petfinder.ui.inicio.MenuUsuarioActivity;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class CargarMascotaFragment extends Fragment {
    private Button cargarFecha,botonImagen, guardar;
    private EditText nombreMascota,raza,tamanio,edad,ubicacion,descripcion,recompensa,duracion;
    private TextView fechaPerdida;
    private ImageView imageView;
    private String imgDeCode="";
    private CargarMascotaViewModel mascotaViewModel;
    private Mascota mascota;
    private Recompensa recompensaMascota;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mascotaViewModel =
                ViewModelProviders.of(this).get(CargarMascotaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cargar_mascota, container, false);
        mascotaViewModel=ViewModelProviders.of(this).get(CargarMascotaViewModel.class);

        configView(root);

        mascotaViewModel.getMascotaMutableLiveData().observe(this, new Observer<Mascota>() {
            @Override
            public void onChanged(Mascota mas) {
                mascota=mas;
            }
        });

        mascotaViewModel.getRecompensaMutableLiveData().observe(this, new Observer<Recompensa>() {
            @Override
            public void onChanged(Recompensa reco) {
                recompensaMascota=reco;
            }
        });


        cargarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colocarFecha();
            }
        });

        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               guardar();
               getActivity().onBackPressed();
            }
        });
        return root;
    }
    private void configView(View view){
        cargarFecha=view.findViewById(R.id.btnFecha);
        fechaPerdida=view.findViewById(R.id.etFechaPerdida);
        botonImagen=view.findViewById(R.id.btnSubirFoto);
        imageView=view.findViewById(R.id.ivFotoMiMascota);
        guardar=view.findViewById(R.id.btnGuardar);
        nombreMascota=view.findViewById(R.id.etNomMascota);
        raza=view.findViewById(R.id.etRaza);
        tamanio=view.findViewById(R.id.etTamanio);
        edad=view.findViewById(R.id.etEdadMascota);
        ubicacion=view.findViewById(R.id.etUbicacion);
        recompensa=view.findViewById(R.id.etRecompensa);
        duracion=view.findViewById(R.id.etDuracion);
        descripcion=view.findViewById(R.id.etDescripcion);
    }

    private void colocarFecha(){
        int dia, mes, anio;
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                fechaPerdida.setText(day+"/"+(month+1)+"/"+year);
            }
        },anio,mes,dia);
        datePickerDialog.show();
    }

    private void cargarImagen(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicacion..."),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            Uri path=data.getData();
            imageView.setImageURI(path);
         }
    }

    private void guardar(){
        if(!recompensa.getText().toString().isEmpty()){
            cargarRecompensa();
            mascota.setRecompensaId(recompensaMascota.getRecompensaId());
        }
        mascota=new Mascota();
        mascota.setNombreMascota(nombreMascota.getText().toString());
        mascota.setRaza(raza.getText().toString());
        mascota.setTamanio(tamanio.getText().toString());
        mascota.setEdad(Integer.parseInt(edad.getText().toString()));
        mascota.setDescripcion(descripcion.getText().toString());
        mascota.setEstado(1);
        mascota.setFecha(fechaPerdida.getText().toString());
        mascota.setUsuarioId(13);
        mascotaViewModel.leerRecompensa();
        mascota.setLugar(ubicacion.getText().toString());
        convertirImagen();
        mascota.setImagen(imgDeCode);
        mascota.setFoto("esta");
        mascotaViewModel.guardarMascota(mascota);
    }

    private void convertirImagen(){
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        if(bitmap!=null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            imgDeCode=Base64.encodeToString(b,Base64.DEFAULT);
        }
    }

    private void cargarRecompensa(){
        recompensaMascota=new Recompensa();

        recompensaMascota.setTiempo(duracion.getText().toString());
        recompensaMascota.setMonto(Double.parseDouble(recompensa.getText().toString()));
        recompensaMascota.setEstado(1);
        mascotaViewModel.guardarRecompensa(recompensaMascota);
    }
}