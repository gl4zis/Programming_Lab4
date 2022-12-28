package main.other;

import main.positions.Space;

@FunctionalInterface
public interface PlanetGetter {
    Space.Planet[] getPlanets();
}
