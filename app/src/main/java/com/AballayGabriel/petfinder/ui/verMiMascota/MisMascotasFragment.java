package com.AballayGabriel.petfinder.ui.verMiMascota;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Mascota;

import java.util.List;

public class MisMascotasFragment extends ArrayAdapter<Mascota> {
    private Context context;
    private List<Mascota> listaMascotas;
    private LayoutInflater li;

    public MisMascotasFragment(@NonNull Context context, int resource, @NonNull List<Mascota> objects, LayoutInflater li){
        super(context,resource,objects);
        this.context=context;
        this.listaMascotas=objects;
        this.li=li;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Bitmap imagenMostar=null;
        View vista=convertView;
        if(vista==null){
            vista=li.inflate(R.layout.fragment_mis_mascotas,parent,false);
        }
        Mascota mascota=listaMascotas.get(position);

        if(!mascota.getImagen().isEmpty()) {
            byte[] byteCode = Base64.decode(mascota.getImagen(), Base64.DEFAULT);
            imagenMostar = BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length);
        }
        mascota.setImagenMostar(imagenMostar);
        ImageView foto=vista.findViewById(R.id.ivFotoMiMascota);
        foto.setImageBitmap(imagenMostar);
        TextView nombre=vista.findViewById((R.id.tvMiNombre));
        nombre.setText(mascota.getNombreMascota());
        TextView lugar=vista.findViewById(R.id.tvMiLugar);
        lugar.setText(mascota.getLugar());
        TextView descripcion=vista.findViewById(R.id.tvMiDescripcion);
        descripcion.setText(mascota.getDescripcion());
        return vista;
    }
}
