package com.AballayGabriel.petfinder.request;

import android.util.Log;

import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.model.Recompensa;
import com.AballayGabriel.petfinder.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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
    //private static final String PATH="http://10.70.155.9:45455/api/";
    private static final String PATH="http://192.168.43.100:45455/api/";
    private static  MyApiInterface myApiInteface;
    private static String accessToken=null;

    public static MyApiInterface getMyApiClient(){

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Log.d("salida",retrofit.baseUrl().toString());
        myApiInteface=retrofit.create(MyApiInterface.class);
        return myApiInteface;
    }

    public interface MyApiInterface {

        @POST("usuarios/login")
        Call<String> login(@Query("Email")String usuario, @Query("Clave") String clave
        );

        @GET("usuarios")
        Call<Usuario> leerUsuario(@Header("Authorization")String token
        );

        @FormUrlEncoded
        @PUT("usuarios/{id}")
        Call<Usuario> actualizar(
                @Header("Authorization")String token,
                @Path("id") int usuarioId,
                @Field("Apellido") String apellido,
                @Field("Nombre") String nombre,
                @Field("Ciudad") String Ciudad,
                @Field("Direccion") String direccion,
                @Field("Telefono") String telefono,
                @Field("Email") String email,
                @Field("Clave") String clave,
                @Field("Estado") int estado,
                @Field("ProvinciaId") int provinciaId
        );

        @FormUrlEncoded
        @POST("mascotas")
        Call<Mascota> mascotaPost(
                @Header("Authorization")String token,
                @Field("NombreMascota") String nombreMascota,
                @Field("Raza") String raza,
                @Field("Tamanio") String tamanio,
                @Field("Edad") int edad,
                @Field("Descripcion") String descripcion,
                @Field("Foto") String foto,
                @Field("Imagen") String imagen,
                @Field("Estado") int estado,
                @Field("Fecha") String fecha,
                @Field("RecompensaId") int recompensaId,
                @Field("Lugar")String lugar,
                @Field("UsuarioId") int usuarioId
        );

        @FormUrlEncoded
        @POST("recompensas")
        Call<Recompensa> recompensaPost(
                @Header("Authorization")String token,
                @Field("Monto") double monto,
                @Field("Tiempo") String tiempo,
                @Field("Estado") int estado
        );

        @GET("recompensas")
        Call<Recompensa> recompensaGet(@Header("Authorization")String token
        );


        @GET("mascotas")
        Call<List<Mascota>> mascotasPerdidasGet(@Header("Authorization")String token);

        @GET("mascotas/{id}")
        Call<List<Mascota>> misMascotasList(
                @Header("Authorization")String token,
                @Path("id") int id
        );

        @GET("mascotas")
        Call<List<Mascota>> mascotasList(@Header("Authorization")String token
        );

        //@GET("mascotas/encontradas")
        //Call<List<Mascota>> listaEncontradas(@Header("Authorization")String token
        //);
    }
}
