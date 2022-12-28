package main.positions;

import java.util.Objects;

public abstract class Place implements Position {
    protected final String name;
    private final int id = (int) (Math.random()*10000);
    public Place(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object b) {
        if (b == this) {
            return true;
        }
        else if (b == null) {
            return false;
        }
        else if (b.getClass() == this.getClass()) {
            return (((Place) b).id == id && Objects.equals(name, b.toString()));
        } else {
            return false;
        }
    }
}
