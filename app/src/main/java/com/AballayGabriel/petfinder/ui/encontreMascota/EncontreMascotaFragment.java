package com.AballayGabriel.petfinder.ui.encontreMascota;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.AballayGabriel.petfinder.Manifest;
import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Encontrada;
import com.AballayGabriel.petfinder.ui.inicio.MainActivity;
import com.AballayGabriel.petfinder.ui.inicio.MenuUsuarioActivity;

import java.util.Calendar;

import static android.Manifest.permission.CAMERA;
import static android.app.Activity.RESULT_OK;

public class EncontreMascotaFragment extends Fragment {
    private EditText lugar,descripcion;
    private TextView fecha;
    private String imgDeCode="";
    public String carga;
    private ImageView muestraFoto;
    private Button tomarFoto, subir,cargarFecha;
    private EncontreMascotaViewModel encontreMascotaViewModel;
    private static int REQUEST_IMAGE_CAPTURE=1;
    private Encontrada encontrada;
    Activity activity=getActivity();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        encontreMascotaViewModel =ViewModelProviders.of(this).get(EncontreMascotaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_encontre_mascota, container, false);
        configView(root);
        validaPermisos();
        cargarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colocarFecha();
            }
        });

        tomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
                subir.setEnabled(true);
            }
        });
        encontreMascotaViewModel.getFoto().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                muestraFoto.setImageBitmap(bitmap);
            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSubir();
                subir.setEnabled(false);
                getActivity().onBackPressed();
            }
        });
        return root;
    }

    private void configView(View view){
        lugar=view.findViewById(R.id.etLugarAllasgo);
        descripcion=view.findViewById(R.id.etDescAllasgo);
        fecha=view.findViewById(R.id.vttFechaEncuentro);
        tomarFoto=view.findViewById(R.id.btnTomarFoto);
        subir=view.findViewById(R.id.btnSubir);
        cargarFecha=view.findViewById(R.id.btnPonerFecha);
        muestraFoto=view.findViewById(R.id.imCargaFoto);
    }

    public void colocarFecha(){
        int dia, mes, anio;
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                fecha.setText(day+"/"+(month+1)+"/"+year);
            }
        },anio,mes,dia);
        datePickerDialog.show();
    }

    private void setSubir(){

        encontrada=new Encontrada();
        encontrada.setLugar(lugar.getText().toString());
        encontrada.setDescripcion(descripcion.getText().toString());
        encontrada.setFecha(fecha.getText().toString());
        encontrada.setFoto("generico");
        convertirImagen();
        encontrada.setImagen(imgDeCode);
        encontreMascotaViewModel.guardarEncuentro(encontrada);
    }

    /*****************************************************
     * *********** CONVIERTO LA IMAGEN A BITMAP **********
     ****************************************************/
    private void convertirImagen(){
        BitmapDrawable drawable = (BitmapDrawable) muestraFoto.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        if(bitmap!=null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            imgDeCode= Base64.encodeToString(b,Base64.DEFAULT);
        }
    }

    /*********************************************************************************************
     * ****************** TOMAR FOTOS ************************************************************
     *********************************************************************************************/

    public void tomarFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        encontreMascotaViewModel.respuetaDeCamara(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);
    }


    //********** PERMISOS CAMARA ************/

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }

        if((ContextCompat.checkSelfPermission(getContext(), CAMERA)== PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(CAMERA))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{CAMERA},100);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]== PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){

            }else{
                solicitarPermisosManual();
            }
        }

    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(getContext());
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getContext().getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Los permisos no fueron aceptados", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getContext());
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{CAMERA},100);
            }
        });
        dialogo.show();
    }
}

