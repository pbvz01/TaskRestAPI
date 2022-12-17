package ua.zakharov.restapplication.aop.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.zakharov.restapplication.aop.exception_handling.exception.UserNotFoundException;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handlerException(UserNotFoundException exp) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo(exp.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
