package pe.idat.creacionescarrasco.Interface;

import pe.idat.creacionescarrasco.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface MetodosApi {
    @POST("/inicio")
    Call<LoginResponse> iniciarSesion(@Field("email") String email, @Field("password") String password);
}
