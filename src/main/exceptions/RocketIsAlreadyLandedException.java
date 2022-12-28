package main.exceptions;

public class RocketIsAlreadyLandedException extends Exception{
    public RocketIsAlreadyLandedException(){
        super("Ракета уже на поверхности!");
    }
}
