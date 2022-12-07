package pe.idat.creacionescarrasco.ui.Registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import pe.idat.creacionescarrasco.Interface.MetodosApi;
import pe.idat.creacionescarrasco.RetrofitClient;
import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.FragmentDashboardBinding;
import pe.idat.creacionescarrasco.model.ValidRoles;
import pe.idat.creacionescarrasco.model.registro.AsistenciasUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private Button btnentrada;
    private Button btnalmuerzo;
    private Button btnfinalmuerzo;
    private Button btnsalida;
    private TextView hola;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        VistaRegis();

        //final TextView textView = binding.textCuenta;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    private void VistaRegis(){

        String tok = VariablesGlobales.getToken();
        String id = VariablesGlobales.getUsuarioDeLaSesion().get_id().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<AsistenciasUser> call = metodosApi.llamarvistaregistro("Bearer " + tok, id, date);
        call.enqueue(new Callback<AsistenciasUser>() {
            @Override
            public void onResponse(Call<AsistenciasUser> call, Response<AsistenciasUser> response) {
                VariablesGlobales.asistenciasUser = response.body();
                btnentrada = binding.btnEntrada;
                hola = binding.idTituloRegistro;
                hola.setText(VariablesGlobales.asistenciasUser.getUser());
            }


            @Override
            public void onFailure(Call<AsistenciasUser> call, Throwable t) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}