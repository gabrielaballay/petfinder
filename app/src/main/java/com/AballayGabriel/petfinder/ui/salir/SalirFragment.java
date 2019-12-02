package com.AballayGabriel.petfinder.ui.salir;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AballayGabriel.petfinder.R;

public class SalirFragment extends Fragment {
    private SalirViewModel salirViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        salirViewModel =
                ViewModelProviders.of(this).get(SalirViewModel.class);
        View root = inflater.inflate(R.layout.fragment_salir, container, false);
        /*salirViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });*/
        return root;
    }

}
