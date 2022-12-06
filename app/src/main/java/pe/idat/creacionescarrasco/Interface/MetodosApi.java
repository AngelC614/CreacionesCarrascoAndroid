package pe.idat.creacionescarrasco.Interface;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.idat.creacionescarrasco.config.VariablesGlobales;
import pe.idat.creacionescarrasco.model.LoginRequest;
import pe.idat.creacionescarrasco.model.LoginResponse;
import pe.idat.creacionescarrasco.model.registro.RegistroResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MetodosApi {

    @POST("auth/login")
    Call<LoginResponse> iniciarSesion(@Body LoginRequest loginRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("hour-register/{id}/{date}")
    Call<RegistroResponse> llamarvistaregistro(@Path("id") String id, @Path("date") String date, @Header("Authorization") String auth);

}
