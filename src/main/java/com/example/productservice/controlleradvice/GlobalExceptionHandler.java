package com.example.productservice.controlleradvice;

import com.example.productservice.dtos.ExceptionDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //ExceptionDto
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDto> handleNullPointerException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("You are trying to access null object");;
        exceptionDto.setSolution("Please fill all the details and create the object first");

        ResponseEntity<ExceptionDto> response = new ResponseEntity<>(
                exceptionDto,
                HttpStatus.BAD_REQUEST);

        return response;
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithematicException(){
        ResponseEntity<String> response = new ResponseEntity<>(
            "There is a arithematic rxception, please verify all the values. " +
                "This is coming from controller advice.",
                HttpStatus.BAD_REQUEST
        );

        return response;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String> response = new ResponseEntity<>(
                "This is Array Index Out of Bound Exception, " +
                        "coming from controller advice",
                HttpStatus.NOT_ACCEPTABLE
        );

        return response;
    }
}
