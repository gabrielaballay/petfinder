package com.AballayGabriel.petfinder.ui.encontreMascota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EncontreMascotaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EncontreMascotaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}