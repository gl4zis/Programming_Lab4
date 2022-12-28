package main.exceptions;

import main.items.mechanisms.Mechanism;

public class MechanismIsBrokenException extends Exception{
    public MechanismIsBrokenException(String message){
        super(message);
    }
}
