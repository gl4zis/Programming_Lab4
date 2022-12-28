package main.items.mechanisms;

public enum StatusOfMechanism {
    WORK("работает"),
    BROKEN("сломан"),
    DISABLED("выключен");

    private final String name;

    StatusOfMechanism(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
