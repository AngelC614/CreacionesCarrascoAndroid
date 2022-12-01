package pe.idat.creacionescarrasco.uiA.Cuenta;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.idat.creacionescarrasco.R;
import pe.idat.creacionescarrasco.databinding.FragmentCuentaABinding;
import pe.idat.creacionescarrasco.databinding.FragmentDashboardBinding;
import pe.idat.creacionescarrasco.ui.Registro.DashboardViewModel;

public class CuentaAFragment extends Fragment {
    private FragmentCuentaABinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CuentaAViewModel cuentaAViewModel =
                new ViewModelProvider(this).get(CuentaAViewModel.class);

        binding = FragmentCuentaABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCuenta;
        cuentaAViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}