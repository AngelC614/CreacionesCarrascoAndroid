package pe.idat.creacionescarrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import pe.idat.creacionescarrasco.Interface.MetodosApi;
import pe.idat.creacionescarrasco.databinding.ActivityLoginBinding;
import pe.idat.creacionescarrasco.model.LoginResponse;
import pe.idat.creacionescarrasco.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

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
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String emailingresado = binding.inputEmail.getText().toString();
        String contraingresada = binding.inputContra.getText().toString();

        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<LoginResponse> call = metodosApi.iniciarSesion(emailingresado, contraingresada);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //RECIBIR EL RESPONSE Y SACAR EL VALOR DE LOS ROLES, TAL VEZ CON JSONOBJECT

                //USAR UN IF:
                //DEPENDIENDO DEL ROL, VA A CIERTA ACTIVIDAD

                //SACAR EL VALOR DEL USUARIO PARA ALMACENARLO
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("ErrorTag", t.getMessage());
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