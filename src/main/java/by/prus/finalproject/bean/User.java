package by.prus.finalproject.bean;

public class User extends Entity {

    private String login;
    private String email;
    private String password;
    private Role role;

    public User (){}

    public User(String login, String email, String password, Role role) {

        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
