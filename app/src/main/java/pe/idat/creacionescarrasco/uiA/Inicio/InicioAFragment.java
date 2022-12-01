package pe.idat.creacionescarrasco.uiA.Inicio;

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
import pe.idat.creacionescarrasco.databinding.FragmentInicioABinding;
import pe.idat.creacionescarrasco.uiA.Cuenta.CuentaAViewModel;

public class InicioAFragment extends Fragment {

    private FragmentInicioABinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InicioAViewModel inicioAViewModel =
                new ViewModelProvider(this).get(InicioAViewModel.class);

        binding = FragmentInicioABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textInicio;
        inicioAViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}