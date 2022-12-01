package pe.idat.creacionescarrasco.uiA.Cuenta;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.idat.creacionescarrasco.R;

public class CuentaAFragment extends Fragment {

    private CuentaAViewModel mViewModel;

    public static CuentaAFragment newInstance() {
        return new CuentaAFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cuenta_a, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CuentaAViewModel.class);
        // TODO: Use the ViewModel
    }

}