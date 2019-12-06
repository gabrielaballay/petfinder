package com.AballayGabriel.petfinder.ui.cargarMascota;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.model.Recompensa;
import com.AballayGabriel.petfinder.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CargarMascotaViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Mascota> mascotaMutableLiveData;
    private MutableLiveData<Recompensa> recompensaMD;

    public CargarMascotaViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Mascota> getMascotaMutableLiveData(){
        if(mascotaMutableLiveData==null){
            mascotaMutableLiveData=new MutableLiveData<>();
        }
        return mascotaMutableLiveData;
    }

    public LiveData<Recompensa> getRecompensaMutableLiveData(){
        if(recompensaMD==null){
            recompensaMD=new MutableLiveData<>();
        }
        return recompensaMD;
    }

    public void guardarMascota(Mascota mascota){

        Call<Mascota> masGuardada= ApiClient.getMyApiClient().mascotaPost(token(),mascota.getNombreMascota(),mascota.getRaza(),
                mascota.getTamanio(), mascota.getEdad(),mascota.getDescripcion(),mascota.getFoto(), mascota.getImagen(),mascota.getEstado(),
                mascota.getFecha(),mascota.getRecompensaId(),mascota.getLugar(),mascota.getUsuarioId());
        masGuardada.enqueue(new Callback<Mascota>() {
                @Override
                public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplication(), "Mascota Guardada", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Mascota> call, Throwable t) {
                    Log.d("salida t mensage",t.getMessage()+"");
                }
        });
    }

    public void guardarRecompensa(Recompensa recompensa){
        Call<Recompensa> cargarRecompensa=ApiClient.getMyApiClient().recompensaPost(token(),
                recompensa.getMonto(),recompensa.getTiempo(),recompensa.getEstado());
        cargarRecompensa.enqueue(new Callback<Recompensa>() {
            @Override
            public void onResponse(Call<Recompensa> call, Response<Recompensa> response) {
                Recompensa reco=response.body();
                recompensaMD.postValue(reco);
            }

            @Override
            public void onFailure(Call<Recompensa> call, Throwable t) {
                Log.d("salida t mensage",t.getMessage()+"");
            }
        });
    }

    public void leerRecompensa(){
        Call<Recompensa> cargarRecompensa=ApiClient.getMyApiClient().recompensaGet(token());
        cargarRecompensa.enqueue(new Callback<Recompensa>() {
            @Override
            public void onResponse(Call<Recompensa> call, Response<Recompensa> response) {
                Recompensa reco=response.body();
                recompensaMD.postValue(reco);
            }

            @Override
            public void onFailure(Call<Recompensa> call, Throwable t) {
                Log.d("salida t mensage",t.getMessage()+"");
            }
        });
    }


    public String token(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        return accessToken;
    }
}