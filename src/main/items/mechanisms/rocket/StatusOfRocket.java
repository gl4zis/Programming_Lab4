package main.items.mechanisms.rocket;

public enum StatusOfRocket {
    LANDED("приземлена"),
    FLYING("в полете"),
    INERTIAL_FLYING("летит по инерции"),
    RETURNING("возвращается"),
    CRASHED("уничтожена");

    private final String name;

    StatusOfRocket(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
