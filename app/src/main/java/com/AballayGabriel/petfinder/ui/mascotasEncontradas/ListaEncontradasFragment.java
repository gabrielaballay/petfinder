package com.AballayGabriel.petfinder.ui.mascotasEncontradas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Encontrada;
import com.AballayGabriel.petfinder.model.Mascota;

import java.util.List;
import java.util.Objects;


public class ListaEncontradasFragment extends ArrayAdapter<Encontrada> {
    private Context context;
    private List<Encontrada> listarMascotas;
    private LayoutInflater li;

    public ListaEncontradasFragment(@NonNull Context context, int resource, @NonNull List<Encontrada> objects, LayoutInflater li){
        super(context,resource,objects);
        this.context=context;
        this.listarMascotas=objects;
        this.li=li;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bitmap imagenMostar=null;
        View itemView=convertView;
        if(itemView==null){
            itemView=li.inflate(R.layout.fragment_lista_encontradas,parent,false);
        }
        Encontrada encontrada=listarMascotas.get(position);

        if(!encontrada.getImagen().isEmpty()) {
            byte[] byteCode = Base64.decode(encontrada.getImagen(), Base64.DEFAULT);
            imagenMostar = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
        }
        ImageView foto=itemView.findViewById(R.id.ivMascota);
        foto.setImageBitmap(imagenMostar);

        TextView nombre=itemView.findViewById((R.id.tvNombre2));
        nombre.setText(encontrada.getFecha());

        TextView lugar=itemView.findViewById(R.id.tvLugar2);
        lugar.setText(encontrada.getLugar());

        TextView descripcion=itemView.findViewById(R.id.tvDescripcion2);
        descripcion.setText(encontrada.getDescripcion());
        return itemView;
    }
}
