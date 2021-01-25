package by.prus.finalproject.bean;

public class User extends Entity {

    private String login;
    private String email;
    private String password;
    private Role role;
    private int clientId;
    private int managerId;

    public User (){}

    public User(String login, String email, String password, Role role) {

        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public int getManagerId() { return managerId; }
    public void setManagerId(int managerId) { this.managerId = managerId; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
