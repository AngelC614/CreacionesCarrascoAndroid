package pe.idat.creacionescarrasco.model;

import java.util.ArrayList;

public class LoginResponse {
    private User user;
    private String token;

    public LoginResponse(){
    }

    public class User {
        private String _id;
        private String email;
        private String password;
        private String names;
        private String lastnames;
        private String dni;
        private String sex;
        private String birth_date;
        private String phone_number;
        private String salary;
        private String work_position;
        private String[] roles;
        private boolean isActive;

        public String get_id() {
            return _id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getNames() {
            return names;
        }

        public String getLastnames() {
            return lastnames;
        }

        public String getDni() {
            return dni;
        }

        public String getSex() {
            return sex;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public String getSalary() {
            return salary;
        }

        public String getWork_position() {
            return work_position;
        }

        public String[] getRoles() {
            return roles;
        }

        public boolean isActive() {
            return isActive;
        }
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
