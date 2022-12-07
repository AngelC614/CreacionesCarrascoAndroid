package pe.idat.creacionescarrasco.Interface;

import pe.idat.creacionescarrasco.model.LoginRequest;
import pe.idat.creacionescarrasco.model.LoginResponse;
import pe.idat.creacionescarrasco.model.registro.AsistenciasUser;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MetodosApi {

    @POST("auth/login")
    Call<LoginResponse> iniciarSesion(@Body LoginRequest loginRequest);

    @GET("hour-register/{id}/{date}")
    Call<AsistenciasUser> llamarvistaregistro(@Header("Authorization") String auth, @Path("id") String id, @Path("date") String date);

}
