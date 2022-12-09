package pe.idat.creacionescarrasco.ui.Cuenta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView usuariotxt = binding.txtusuario;
        final TextView nombretxt = binding.txtNombres;
        final TextView emailtxt = binding.txtEmail;
        final TextView apellidostxt = binding.txtApellido;
        final TextView dnitxt = binding.txtDni;
        final TextView sexotxt = binding.txtSexo;
        final TextView fechatxt = binding.txtFecha;
        final TextView numerotxt = binding.txtNumero;
        final TextView salariotxt = binding.txtSalario;
        final TextView cargotxt = binding.txtCargo;

        usuariotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getNames());
        nombretxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getNames());
        emailtxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getEmail());
        apellidostxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getLastnames());
        dnitxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getDni());
        usuariotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getNames());
        fechatxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getBirth_date());
        numerotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getPhone_number());
        salariotxt.setText(VariablesGlobales.getUsuarioDeLaSesion().getSalary());
        if (VariablesGlobales.getUsuarioDeLaSesion().getSex().toString().equals("M")){
            sexotxt.setText("Masculino");
        }
        else {
            sexotxt.setText("Femenino");
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}