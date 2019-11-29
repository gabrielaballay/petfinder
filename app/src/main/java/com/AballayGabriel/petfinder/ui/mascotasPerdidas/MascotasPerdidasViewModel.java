package com.AballayGabriel.petfinder.ui.mascotasPerdidas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MascotasPerdidasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MascotasPerdidasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is mascotasPerdidas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}