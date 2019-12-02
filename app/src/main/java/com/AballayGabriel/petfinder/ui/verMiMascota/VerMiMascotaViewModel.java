package com.AballayGabriel.petfinder.ui.verMiMascota;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VerMiMascotaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VerMiMascotaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}