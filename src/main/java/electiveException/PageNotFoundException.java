package electiveException;

public class PageNotFoundException extends RuntimeException{
    private String message = "Sorry, we cannot find this page.";

    public PageNotFoundException(){}

    public PageNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
