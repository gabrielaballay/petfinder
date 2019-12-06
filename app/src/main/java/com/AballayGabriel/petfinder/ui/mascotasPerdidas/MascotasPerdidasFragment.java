package com.AballayGabriel.petfinder.ui.mascotasPerdidas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Mascota;

import java.util.ArrayList;
import java.util.List;

public class MascotasPerdidasFragment extends Fragment {
    private MascotasPerdidasViewModel mascotasPerdidasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        mascotasPerdidasViewModel=ViewModelProviders.of(this).get(MascotasPerdidasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mascotas_perdidas, container, false);

        generarView(root);
        mascotasPerdidasViewModel.listaMascotasPerdidas();
        return root;
    }

    public void generarView(final View view){
        mascotasPerdidasViewModel=ViewModelProviders.of(this).get(MascotasPerdidasViewModel.class);
        mascotasPerdidasViewModel.getMascotasData().observe(this, new Observer<List<Mascota>>() {
            @Override
            public void onChanged(List<Mascota> mascotas) {
                ArrayAdapter<Mascota> adapter=new ListaMascotasFragment(getContext(),R.layout.fragment_lista_mascotas,mascotas,getLayoutInflater());
                ListView lv=view.findViewById(R.id.milista);
                lv.setAdapter(adapter);
                Log.d("salida mascotas","en el onchange");
            }
        });
    }
}

