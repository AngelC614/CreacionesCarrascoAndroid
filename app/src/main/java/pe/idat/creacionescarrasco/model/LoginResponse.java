package pe.idat.creacionescarrasco.model;

import java.util.ArrayList;
public class LoginResponse {
    private User user;
    private String token;

    public LoginResponse(){
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
