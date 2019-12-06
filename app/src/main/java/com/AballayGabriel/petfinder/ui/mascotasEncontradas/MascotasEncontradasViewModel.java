package com.AballayGabriel.petfinder.ui.mascotasEncontradas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.AballayGabriel.petfinder.model.Encontrada;

public class MascotasEncontradasViewModel extends AndroidViewModel {

    private MutableLiveData<Encontrada> encontradaMutableLD;


    public MascotasEncontradasViewModel(@NonNull Application application) {
        super(application);
    }

}