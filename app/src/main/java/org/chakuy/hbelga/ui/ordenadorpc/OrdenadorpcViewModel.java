package org.chakuy.hbelga.ui.ordenadorpc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrdenadorpcViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public OrdenadorpcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Añadir Equipos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}