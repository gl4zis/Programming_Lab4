package main.positions;

import main.positions.Position;

public abstract class Location implements Position {
    protected String name;

    Location(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
