package com.AballayGabriel.petfinder.ui.verMiMascota;

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

import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerMiMascotaViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Mascota>> miMascotaMutableLD;

    public VerMiMascotaViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public MutableLiveData<List<Mascota>> getListaMascota(){
        if(miMascotaMutableLD==null){
            miMascotaMutableLD=new MutableLiveData<>();
        }
        return miMascotaMutableLD;
    }

    public void listaMiMascotas(){
        int id=Integer.parseInt(idRecuperado());
        Log.d("Salida",idRecuperado()+" sera esto");
        Call<List<Mascota>> datosMascotas=ApiClient.getMyApiClient().misMascotasList(token(),id);
        datosMascotas.enqueue(new Callback<List<Mascota>>() {
            @Override
            public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {

                if(response.isSuccessful()){
                    ArrayList<Mascota> mascotas=new ArrayList<>();
                    for (Mascota m: response.body()) {

                        mascotas.add(m);
                    }
                    miMascotaMutableLD.postValue(mascotas);
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

    private String idRecuperado(){
        SharedPreferences sp=context.getSharedPreferences("userId",0);
        String idUser=sp.getString("userId","");
        return idUser;
    }

}