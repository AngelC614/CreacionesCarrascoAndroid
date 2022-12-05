package pe.idat.creacionescarrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import pe.idat.creacionescarrasco.Interface.MetodosApi;
import pe.idat.creacionescarrasco.databinding.ActivityLoginBinding;
import pe.idat.creacionescarrasco.model.LoginRequest;
import pe.idat.creacionescarrasco.model.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnIngresar = binding.btnIngresar;
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticarDatos();
            }
        });
    }

    private void autenticarDatos() {
        LoginRequest loginrequest = new LoginRequest();

        loginrequest.setEmail(binding.inputEmail.getText().toString());
        loginrequest.setPassword(binding.inputContra.getText().toString());

        iniciarSesion(loginrequest);

    }

    private void iniciarSesion(LoginRequest loginrequest) {

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<LoginResponse> call = metodosApi.iniciarSesion(loginrequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                //Obtiene el token y lo muestra como mensaje
                Toast toast1 =
                        Toast.makeText(getApplicationContext(), response.body().getToken(), Toast.LENGTH_SHORT);

                toast1.show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                //Muestra un mensaje
                String texto = t.getMessage();
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                texto, Toast.LENGTH_SHORT);

                toast2.show();
            }
        });

    }

    //METODOS PARA IR A LA RESPECTIVA ACTIVIDAD
    private void irMenuPrincipalEmpleado() {
        Intent intentMenuPrincipal = new Intent(this, MainActivity.class);
        startActivity((intentMenuPrincipal));
    }
    private void irMenuPrincipalAdmin() {
        Intent intentMenuPrincipalAdmin = new Intent(this, AdminActivity.class);
        startActivity((intentMenuPrincipalAdmin));
    }
}