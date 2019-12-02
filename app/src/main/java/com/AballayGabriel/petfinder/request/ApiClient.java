package com.AballayGabriel.petfinder.request;

import android.util.Log;

import com.AballayGabriel.petfinder.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClient {
    private static final String PATH="https://10.76.165.159:45455/api/";
    private static  MyApiInterface myApiInteface;
    private static String accessToken=null;


    public static MyApiInterface getMyApiClient(){


        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Log.d("salida",retrofit.baseUrl().toString());
        myApiInteface=retrofit.create(MyApiInterface.class);
        Log.d("salida",myApiInteface.toString());
        return myApiInteface;
    }





    public interface MyApiInterface {

        @POST("usuarios/login")
        Call<String> login(@Query("Email")String usuario, @Query("Clave") String clave);



        @GET("usuarios")
        Call<Usuario> leer(@Header("Authorization")String token);

        @FormUrlEncoded
        @PUT("usuarios/{id}")
        Call<Usuario> actualizar(@Header("Authorization")String token, @Path("id") int groupId, @Field("Nombre")String nombre, @Field("Apellido") String apellido, @Field("Dni") int dni, @Field("Correo")String correo, @Field("Clave")String clave, @Field("EstadoPropietario") int estado, @Field("Telefono")long telefono);
        // @GET("test")
        // Call<Data> leer();

        //listarClientes.php
        //@GET("listarClientes.php")
        //Call<List<Cliente>> getClientes();

        //@GET("insertarClientes.php")
        //Call<Cliente> createCliente(@Query("dni") int dni, @Query("apellido") String apellido, @Query("nombre") String nombre);
    }
}
