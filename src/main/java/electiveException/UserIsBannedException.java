package electiveException;

public class UserIsBannedException extends RuntimeException{
    private String message = "Your account is temporarily banned. Please, contact the administrator.";

    public UserIsBannedException(){}

    public UserIsBannedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
