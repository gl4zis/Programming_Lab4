package main.items.mechanisms;

import main.items.Item;
import main.items.mechanisms.rocket.StatusOfRocket;

public abstract class Mechanism extends Item {
    protected StatusOfMechanism mechanismStatus;

    protected Mechanism(String name, StatusOfMechanism status) {
        super(name);
        this.mechanismStatus = status;
    }

    public void broke(){
        warning(this);
        setStatus(StatusOfMechanism.BROKEN);
    }

    protected void warning(Mechanism error) {
        System.out.println("!!!ОПАСНОСТЬ!!! НЕ исправен механизм: " + error);
    }

    public StatusOfMechanism getStatus() {
        return mechanismStatus;
    }

    public void setStatus(StatusOfMechanism status) {
        mechanismStatus = status;
    }
}
