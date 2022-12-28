package main.exceptions;

public class VarIsNotPositiveException extends RuntimeException {
    public VarIsNotPositiveException(String message){
        super(message);
    }
}
