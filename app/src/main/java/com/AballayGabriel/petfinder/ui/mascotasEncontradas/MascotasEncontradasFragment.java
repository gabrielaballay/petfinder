package com.AballayGabriel.petfinder.ui.mascotasEncontradas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Encontrada;
import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.ui.mascotasPerdidas.ListaMascotasFragment;
import com.AballayGabriel.petfinder.ui.mascotasPerdidas.MascotasPerdidasViewModel;

import java.util.List;

public class MascotasEncontradasFragment extends Fragment {

    private MascotasEncontradasViewModel mascotasEncontradasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        mascotasEncontradasViewModel=ViewModelProviders.of(this).get(MascotasEncontradasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mascotas_perdidas, container, false);

        generarView(root);
        mascotasEncontradasViewModel.listaMascotasEncontradas();
        return root;
    }

    public void generarView(final View view){
        mascotasEncontradasViewModel=ViewModelProviders.of(this).get(MascotasEncontradasViewModel.class);
        mascotasEncontradasViewModel.getMascotasEncontradasData().observe(this, new Observer<List<Encontrada>>() {
            @Override
            public void onChanged(List<Encontrada> mascotas) {
                ArrayAdapter<Encontrada> adapter=new ListaEncontradasFragment(getContext(),R.layout.fragment_lista_encontradas,mascotas,getLayoutInflater());
                ListView lv=view.findViewById(R.id.listaencontradas);
                lv.setAdapter(adapter);
                Log.d("salida mascotas","en el onchange");
            }
        });
    }
}


