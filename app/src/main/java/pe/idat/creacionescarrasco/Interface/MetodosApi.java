package pe.idat.creacionescarrasco.Interface;

import pe.idat.creacionescarrasco.model.LoginRequest;
import pe.idat.creacionescarrasco.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MetodosApi {
    @POST("login")
    Call<LoginResponse> iniciarSesion(@Body LoginRequest loginRequest);
}
