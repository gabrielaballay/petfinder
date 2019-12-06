package com.AballayGabriel.petfinder.ui.salir;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    Activity activity=getActivity();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        salirViewModel =
                ViewModelProviders.of(this).get(SalirViewModel.class);
        View root = inflater.inflate(R.layout.fragment_salir, container, false);
        new AlertDialog.Builder(getContext()).setTitle("").setMessage("Esta seguro que desea Salir?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();

            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().onBackPressed();

            }
        }).show();
        return root;
    }

}
