package org.chakuy.hbelga.ui.verlista.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class VlistaViewModel extends ViewModel {


    private final MutableLiveData<String> mText;

    public VlistaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Lista Equipos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
    // TODO: Implement the ViewModel
