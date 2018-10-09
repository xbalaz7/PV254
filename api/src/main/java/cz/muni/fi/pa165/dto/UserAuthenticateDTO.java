package cz.muni.fi.pa165.dto;

public class UserAuthenticateDTO {
    private String password;
    private String email;

    public UserAuthenticateDTO() {}

    public UserAuthenticateDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
