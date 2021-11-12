package presenters;

public class LoginPresenter {
    private String username;
    public LoginPresenter(String username){
        this.username = username;
    };

    public String presentUser(){
        return this.username;
    }
}
