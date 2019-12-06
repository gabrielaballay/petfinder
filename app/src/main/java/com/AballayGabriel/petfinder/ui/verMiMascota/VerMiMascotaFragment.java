package com.AballayGabriel.petfinder.ui.verMiMascota;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.AballayGabriel.petfinder.R;
import com.AballayGabriel.petfinder.model.Mascota;
import com.AballayGabriel.petfinder.ui.mascotasPerdidas.ListaMascotasFragment;

import java.util.List;

public class VerMiMascotaFragment extends Fragment {
    private VerMiMascotaViewModel verMiMascotaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        verMiMascotaViewModel= ViewModelProviders.of(this).get(VerMiMascotaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ver_mi_mascota,container,false);

        crearVista(root);
        verMiMascotaViewModel.listaMiMascotas();
        return root;
    }

    public void crearVista(final View view){
        verMiMascotaViewModel= ViewModelProviders.of(this).get(VerMiMascotaViewModel.class);
        verMiMascotaViewModel.getListaMascota().observe(this, new Observer<List<Mascota>>() {
            @Override
            public void onChanged(List<Mascota> mascotas) {
                ArrayAdapter<Mascota> adapter=new ListaMascotasFragment(getContext(),R.layout.fragment_mis_mascotas,mascotas,getLayoutInflater());
                ListView lv=view.findViewById(R.id.verMiMascotas);
                lv.setAdapter(adapter);
            }
        });
    }

}