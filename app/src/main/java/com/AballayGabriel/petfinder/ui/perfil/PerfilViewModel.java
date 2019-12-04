package com.AballayGabriel.petfinder.ui.perfil;

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

import com.AballayGabriel.petfinder.model.Usuario;
import com.AballayGabriel.petfinder.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutableLiveData;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuarioMutableLiveData(){
        if(usuarioMutableLiveData==null){
            usuarioMutableLiveData=new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void leer(){

        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");
        Call<Usuario> usuarioCall= ApiClient.getMyApiClient().leerUsuario(accessToken);

        usuarioCall.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario=response.body();
                usuarioMutableLiveData.postValue(usuario);
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplication(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void actualizar(Usuario usuario){
        SharedPreferences sp=context.getSharedPreferences("token",0);
        String accessToken=sp.getString("token","");

        Call<Usuario> proActualizado=ApiClient.getMyApiClient().actualizar(accessToken,usuario.getUsuarioId(),usuario.getApellido(),usuario.getNombre(),usuario.getCiudad(),
                usuario.getDireccion(),usuario.getTelefono(),usuario.getEmail(),usuario.getClave(),usuario.getEstado(),usuario.getProvinciaId());
        proActualizado.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getApplication(), "Datos Actualizados", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });

    }

}