package main.alive;

public enum StatusOfHuman {
    SLEEP("спит"),
    AWAKE("бодрствует"),
    DEAD("мёртв");

    private final String name;

    StatusOfHuman(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
