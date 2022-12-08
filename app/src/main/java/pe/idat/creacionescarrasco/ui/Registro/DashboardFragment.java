package pe.idat.creacionescarrasco.ui.Registro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        btnentrada = binding.btnEntrada;
        btnalmuerzo = binding.btnAlmuerzo;
        btnfinalmuerzo = binding.btnFinAlmuerzo;
        btnsalida = binding.btnSalida;
        hola = binding.idTituloRegistro;
        progressBar = binding.pb1;

        btnentrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnentrada.setEnabled(false);
                RegisStartTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                VistaRegis();

            }
        });
        btnalmuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnalmuerzo.setEnabled(false);
                RegisInitAlm();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                VistaRegis();
            }
        });
        btnfinalmuerzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnfinalmuerzo.setEnabled(false);
                RegiFinAlm();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                VistaRegis();
            }
        });
        btnsalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnsalida.setEnabled(false);
                RegiSalida();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                VistaRegis();
            }
        });

        VistaRegis();

        //final TextView textView = binding.textCuenta;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    private void ActivarVista(){
        progressBar.setVisibility(View.GONE);
        btnalmuerzo.setVisibility(View.VISIBLE);
        btnsalida.setVisibility(View.VISIBLE);
        btnfinalmuerzo.setVisibility(View.VISIBLE);
        btnentrada.setVisibility(View.VISIBLE);
        hola.setVisibility(View.VISIBLE);

    }

    private void VistaRegis(){

        String tok = VariablesGlobales.getToken();
        String id = VariablesGlobales.getUsuarioDeLaSesion().get_id().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<AsistenciasUser> call = metodosApi.llamarvistaregistro( id, date, "Bearer " + tok);
        call.enqueue(new Callback<AsistenciasUser>() {
            @Override
            public void onResponse(Call<AsistenciasUser> call, Response<AsistenciasUser> response) {
                VariablesGlobales.asistenciasUser = response.body();

                if (VariablesGlobales.asistenciasUser.getStart_time().equals("--:--")){
                    btnalmuerzo.setEnabled(false);
                    btnfinalmuerzo.setEnabled(false);
                    btnsalida.setEnabled(false);
                }
                else{
                    btnentrada.setEnabled(false);
                    btnalmuerzo.setEnabled(true);
                }
                if (VariablesGlobales.asistenciasUser.getLunch_start_time().equals("--:--")){

                    btnfinalmuerzo.setEnabled(false);
                    btnsalida.setEnabled(false);
                }
                else{
                    btnalmuerzo.setEnabled(false);
                    btnfinalmuerzo.setEnabled(true);
                }
                if (VariablesGlobales.asistenciasUser.getLunch_end_time().equals("--:--")){

                    btnsalida.setEnabled(false);
                }
                else{
                    btnsalida.setEnabled(true);
                    btnfinalmuerzo.setEnabled(false);
                }
                if (VariablesGlobales.asistenciasUser.getEnd_time().equals("--:--")){

                }
                else{
                    btnsalida.setEnabled(false);

                }
                ActivarVista();

            }


            @Override
            public void onFailure(Call<AsistenciasUser> call, Throwable t) {
            }
        });
    }


    private void RegisStartTime(){

        btnentrada.setEnabled(false);
        String tok = VariablesGlobales.getToken();
        String id = VariablesGlobales.getUsuarioDeLaSesion().get_id().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String hour = new SimpleDateFormat("HH:mm").format(new Date());

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<AsistenciasUser> call = metodosApi.regentrada("Bearer " + tok, id, date, hour);
        call.enqueue(new Callback<AsistenciasUser>() {
            @Override
            public void onResponse(Call<AsistenciasUser> call, Response<AsistenciasUser> response) {
                VariablesGlobales.asistenciasUser = response.body();
            }

            @Override
            public void onFailure(Call<AsistenciasUser> call, Throwable t) {
                String texto = t.getMessage();
                Toast toast2 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                texto, Toast.LENGTH_SHORT);

                toast2.show();
            }
        });
    }
    private void RegisInitAlm(){

        btnalmuerzo.setEnabled(false);
        String tok = VariablesGlobales.getToken();
        String id = VariablesGlobales.getUsuarioDeLaSesion().get_id().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String hour = new SimpleDateFormat("HH:mm").format(new Date());

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<AsistenciasUser> call = metodosApi.reginitalm("Bearer " + tok, id, date, hour);
        call.enqueue(new Callback<AsistenciasUser>() {
            @Override
            public void onResponse(Call<AsistenciasUser> call, Response<AsistenciasUser> response) {
                VariablesGlobales.asistenciasUser = response.body();
            }

            @Override
            public void onFailure(Call<AsistenciasUser> call, Throwable t) {
                String texto = t.getMessage();
                Toast toast2 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                texto, Toast.LENGTH_SHORT);

                toast2.show();
            }
        });
    }
    private void RegiFinAlm(){

        btnfinalmuerzo.setEnabled(false);
        String tok = VariablesGlobales.getToken();
        String id = VariablesGlobales.getUsuarioDeLaSesion().get_id().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String hour = new SimpleDateFormat("HH:mm").format(new Date());

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<AsistenciasUser> call = metodosApi.regfinalm("Bearer " + tok, id, date, hour);
        call.enqueue(new Callback<AsistenciasUser>() {
            @Override
            public void onResponse(Call<AsistenciasUser> call, Response<AsistenciasUser> response) {
                VariablesGlobales.asistenciasUser = response.body();
            }

            @Override
            public void onFailure(Call<AsistenciasUser> call, Throwable t) {
                String texto = t.getMessage();
                Toast toast2 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                texto, Toast.LENGTH_SHORT);

                toast2.show();
            }
        });
    }
    private void RegiSalida(){

        btnsalida.setEnabled(false);
        String tok = VariablesGlobales.getToken();
        String id = VariablesGlobales.getUsuarioDeLaSesion().get_id().toString();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String hour = new SimpleDateFormat("HH:mm").format(new Date());

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<AsistenciasUser> call = metodosApi.regsalida("Bearer " + tok, id, date, hour);
        call.enqueue(new Callback<AsistenciasUser>() {
            @Override
            public void onResponse(Call<AsistenciasUser> call, Response<AsistenciasUser> response) {
                VariablesGlobales.asistenciasUser = response.body();
            }

            @Override
            public void onFailure(Call<AsistenciasUser> call, Throwable t) {
                String texto = t.getMessage();
                Toast toast2 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                texto, Toast.LENGTH_SHORT);

                toast2.show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}