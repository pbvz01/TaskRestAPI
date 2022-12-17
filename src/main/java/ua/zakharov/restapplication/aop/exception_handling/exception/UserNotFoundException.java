package ua.zakharov.restapplication.aop.exception_handling.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String message = "Incorrect ID. The user was not be found";
    public UserNotFoundException(){
        super(message);
    }
}
