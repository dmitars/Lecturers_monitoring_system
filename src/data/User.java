package data;

public class User {
    public String login;
    public String password;
    public boolean isAdministrator = false;
    private int id = 0;

    public User(String login,String password){
        this.login = login;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
