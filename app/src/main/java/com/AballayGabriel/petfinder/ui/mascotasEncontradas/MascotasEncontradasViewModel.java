package com.AballayGabriel.petfinder.ui.mascotasEncontradas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.AballayGabriel.petfinder.model.Encontrada;
import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotasEncontradasViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Encontrada>> mascotasEncontradasLista;

    public MascotasEncontradasViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<List<Encontrada>> getMascotasEncontradasData(){
        if(mascotasEncontradasLista==null){
            mascotasEncontradasLista=new MutableLiveData<>();
        }
        return mascotasEncontradasLista;
    }

    public void listaMascotasEncontradas(){
        Call<List<Encontrada>> datosMascotas= ApiClient.getMyApiClient().mascotasEncontradasGet(token());
        datosMascotas.enqueue(new Callback<List<Encontrada>>() {
            @Override
            public void onResponse(Call<List<Encontrada>> call, Response<List<Encontrada>> response) {
                Log.d("Salida",response.message());
                if(response.isSuccessful()){
                    ArrayList<Encontrada> mascotas=new ArrayList<>();
                    for (Encontrada m: response.body()) {

                        mascotas.add(m);
                    }
                    mascotasEncontradasLista.postValue(mascotas);
                }
            }

            @Override
            public void onFailure(Call<List<Encontrada>> call, Throwable t) {
                Toast.makeText(getApplication(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    private String token(){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        return accessToken;
    }


}