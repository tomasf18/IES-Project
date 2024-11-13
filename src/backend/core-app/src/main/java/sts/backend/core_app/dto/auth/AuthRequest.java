package sts.backend.core_app.dto.auth;

public class AuthRequest {
    private String usernameOrEmail;
    private String password;

    public String getUsernameOrEmail() {
        return this.usernameOrEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
