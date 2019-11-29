package com.AballayGabriel.petfinder.ui.mascotasPerdidas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.AballayGabriel.petfinder.R;

public class MascotasPerdidasFragment extends Fragment {

    private MascotasPerdidasViewModel mascotasPerdidasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mascotasPerdidasViewModel =
                ViewModelProviders.of(this).get(MascotasPerdidasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mascotas_perdidas, container, false);
        mascotasPerdidasViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}