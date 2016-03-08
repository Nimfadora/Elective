package electiveException;

public class EmailAlreadyExistsException extends RuntimeException {
    private String message = "This email already exists";

    public EmailAlreadyExistsException() {}

    public EmailAlreadyExistsException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
