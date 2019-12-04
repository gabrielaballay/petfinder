package com.AballayGabriel.petfinder.ui.cargarMascota;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import static android.app.Activity.RESULT_OK;
import com.AballayGabriel.petfinder.R;

import java.util.Calendar;


public class CargarMascotaFragment extends Fragment {
    private Button cargarFecha,botonImagen;
    private TextView fechaPerdida;
    private ImageView imageView;
    private CargarMascotaViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(CargarMascotaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cargar_mascota, container, false);

        configView(root);
        cargarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colocarFecha(getContext());
            }
        });

        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
            }
        });
        return root;
    }
    private void configView(View view){
        cargarFecha=view.findViewById(R.id.btnFecha);
        fechaPerdida=view.findViewById(R.id.etFechaPerdida);
        botonImagen=view.findViewById(R.id.btnSubirFoto);
        imageView=view.findViewById(R.id.ivFotoMascota);
    }
    private void colocarFecha(Context context){
        int dia, mes, anio;
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
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
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("salida",RESULT_OK+"");
        if(requestCode==RESULT_OK){
            Uri path=data.getData();
            imageView.setImageURI(path);
        }
    }
}