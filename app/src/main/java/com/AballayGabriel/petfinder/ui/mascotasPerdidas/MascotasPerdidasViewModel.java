package com.AballayGabriel.petfinder.ui.mascotasPerdidas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.model.Usuario;
import com.AballayGabriel.petfinder.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotasPerdidasViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Mascota>> mascotasLista;

    public MascotasPerdidasViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<List<Mascota>> getMascotasData(){
        if(mascotasLista==null){
            mascotasLista=new MutableLiveData<>();
        }
        return mascotasLista;
    }

    public void listaMascotasPerdidas(){
        Call<List<Mascota>> datosMascotas=ApiClient.getMyApiClient().mascotasPerdidasGet(token(),-1);
        datosMascotas.enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                Log.d("Salida",response.message());
                if(response.isSuccessful()){
                    ArrayList<Mascota> mascotas=new ArrayList<>();
                    for (Mascota m: response.body()) {

                        mascotas.add(m);
                    }
                    mascotasLista.postValue(mascotas);
                }
            }

            @Override
            public void onFailure(Call<List<Mascota>> call, Throwable t) {
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