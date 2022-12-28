package main.exceptions;

public class SpeedIsAlreadyNull extends Exception{
    public SpeedIsAlreadyNull(){
        super("Скорость уже нулевая!");
    }
}
