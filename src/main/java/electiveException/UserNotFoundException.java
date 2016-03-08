package electiveException;

public class UserNotFoundException extends RuntimeException{
    private String message = "Invalid username or password";

    public UserNotFoundException() {}
    public UserNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
