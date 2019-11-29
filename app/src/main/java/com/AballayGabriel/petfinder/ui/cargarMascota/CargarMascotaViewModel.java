package com.AballayGabriel.petfinder.ui.cargarMascota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CargarMascotaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CargarMascotaViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("Cargar Mascota");
    }

    /*public LiveData<String> getText() {
        return mText;
    }*/
}