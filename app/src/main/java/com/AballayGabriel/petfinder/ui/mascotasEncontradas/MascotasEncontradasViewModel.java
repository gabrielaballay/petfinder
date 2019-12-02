package com.AballayGabriel.petfinder.ui.mascotasEncontradas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MascotasEncontradasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MascotasEncontradasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}