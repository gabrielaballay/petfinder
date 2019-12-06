package com.AballayGabriel.petfinder.ui.encontreMascota;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.AballayGabriel.petfinder.model.Encontrada;
import com.AballayGabriel.petfinder.request.ApiClient;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class EncontreMascotaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Encontrada> mldEncontrada;
    private MutableLiveData<Bitmap> foto;

    public LiveData<Bitmap> getFoto(){
        if(foto==null){
            foto=new MutableLiveData<>();
        }
        return foto;
    }

    public EncontreMascotaViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Encontrada> getEncontrada()
    {
        if(mldEncontrada==null){
            mldEncontrada=new MutableLiveData<>();
        }
        return mldEncontrada;
    }

    public void guardarEncuentro(Encontrada encontrada){
        int id=Integer.parseInt(idRecuperado());

        final Call<Encontrada> respuesta= ApiClient.getMyApiClient().encontradaPost(token(),encontrada.getFoto(),encontrada.getImagen(),
                encontrada.getDescripcion(),encontrada.getFecha(),encontrada.getLugar(),id);
        respuesta.enqueue(new Callback<Encontrada>() {
            @Override
            public void onResponse(Call<Encontrada> call, Response<Encontrada> response) {
                Log.d("Salida",response.message());
                if(response.isSuccessful()){
                    Toast.makeText(getApplication(), "Se Guardo con Exito !!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Encontrada> call, Throwable t) {
                Toast.makeText(getApplication(), "Error al cargar"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



    public void respuetaDeCamara(int requestCode, int resultCode, @Nullable Intent data, int REQUEST_IMAGE_CAPTURE){
        Log.d("salida",requestCode+"");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Recupero los datos provenientes de la camara.
            Bundle extras = data.getExtras();
            //Casteo a bitmap lo obtenido de la camara.
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Rutina para optimizar la foto,
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            foto.setValue(imageBitmap);
        }
    }

    private String token(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        return accessToken;
    }

    private String idRecuperado(){
        SharedPreferences sp=context.getSharedPreferences("userId",0);
        String idUser=sp.getString("userId","");
        return idUser;
    }
}