package pe.idat.creacionescarrasco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pe.idat.creacionescarrasco.Interface.MetodosApi;
import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.databinding.ActivityPosicionTrabajoActivityBinding;
import pe.idat.creacionescarrasco.model.WorkPositionRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearPosicionTrabajoActivty extends AppCompatActivity {
    private ActivityPosicionTrabajoActivityBinding binding;
    private Button btnCreacionPosicionTrabajo;
    private Button btnIrInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPosicionTrabajoActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnCreacionPosicionTrabajo = binding.btnCrearPosicionTrabajo;
        btnIrInicio = binding.btnRegresarInicioAdminPT;

        // BOTON PARA IR AL INICIO DEL ADMIN
        btnIrInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarInicio();
            }
        });

        // BOTON PARA CREAR LA POSICION DE TRABAJO
        btnCreacionPosicionTrabajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recogerDatos();
            }
        });
    }

    private void recogerDatos() {
        WorkPositionRequest workPositionRequest = new WorkPositionRequest();
        workPositionRequest.setName(binding.inputTextNombrePosicionTrabajo.getText().toString());
        workPositionRequest.setDescription(binding.inputTextDescripcionPosicionTrabajo.getText().toString());
        workPositionRequest.setWork_start_time(binding.inputTextHoraInicioPosicionTrabajo.getText().toString());
        workPositionRequest.setWork_end_time(binding.inputTextHoraSalidaPosicionTrabajo.getText().toString());

        crearPosicionTrabajo(workPositionRequest);
    }

    private void regresarInicio() {
        Intent intentIrInicioAdmin = new Intent(this, AdminActivity.class);
        startActivity(intentIrInicioAdmin);
    }

    private void crearPosicionTrabajo(WorkPositionRequest workPositionRequest) {
        MetodosApi metodosApi = RetrofitClient.getRetrofitInstance().create(MetodosApi.class);
        Call<WorkPositionRequest> call = metodosApi.creacionPosicionTrabajo("Bearer " + VariablesGlobales.getToken(), workPositionRequest);
        call.enqueue(new Callback<WorkPositionRequest>() {
            @Override
            public void onResponse(Call<WorkPositionRequest> call, Response<WorkPositionRequest> response) {
                if(response.code() == 400){
                    Toast toasterrorinput1 = Toast.makeText(getApplicationContext(),
                            "Los datos no tienen la forma esperada, ingrese nuevamente", Toast.LENGTH_SHORT);
                    toasterrorinput1.show();
                }
                else if (response.code() == 401){
                    Toast toasterrorinput2 = Toast.makeText(getApplicationContext(),
                            "El Token no es válido, expiró o no se envió", Toast.LENGTH_SHORT);
                    toasterrorinput2.show();
                }

                else{
                    Toast toastsuccess = Toast.makeText(getApplicationContext(),
                            "¡La Posición de Trabajo se creó correctamente!", Toast.LENGTH_SHORT);
                    toastsuccess.show();

                    binding.inputTextNombrePosicionTrabajo.setText("");
                    binding.inputTextDescripcionPosicionTrabajo.setText("");
                    binding.inputTextHoraInicioPosicionTrabajo.setText("");
                    binding.inputTextHoraSalidaPosicionTrabajo.setText("");
                }
            }

            @Override
            public void onFailure(Call<WorkPositionRequest> call, Throwable t) {
                String texto = t.getMessage();
                Toast toastfailure =
                        Toast.makeText(getApplicationContext(),
                                texto, Toast.LENGTH_SHORT);

                toastfailure.show();
            }
        });
    }
}