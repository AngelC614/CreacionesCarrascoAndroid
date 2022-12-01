package pe.idat.creacionescarrasco.uiA.Lista;

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
import pe.idat.creacionescarrasco.databinding.FragmentInicioABinding;
import pe.idat.creacionescarrasco.databinding.FragmentListaABinding;
import pe.idat.creacionescarrasco.uiA.Inicio.InicioAViewModel;

public class ListaAFragment extends Fragment {

    private FragmentListaABinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListaAViewModel listaAViewModel =
                new ViewModelProvider(this).get(ListaAViewModel.class);

        binding = FragmentListaABinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textLista;
        listaAViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}