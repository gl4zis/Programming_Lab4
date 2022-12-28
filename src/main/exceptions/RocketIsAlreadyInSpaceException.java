package main.exceptions;

public class RocketIsAlreadyInSpaceException extends Exception{
    public RocketIsAlreadyInSpaceException(){
        super("Ракета уже в космосе!");
    }
}
