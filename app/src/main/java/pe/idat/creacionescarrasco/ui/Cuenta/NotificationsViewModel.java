package pe.idat.creacionescarrasco.ui.Cuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.model.User;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(VariablesGlobales.getUsuarioDeLaSesion().getNames().toString());
    }

    public LiveData<String> getText() {
        return mText;
    }
}