package main.other;

import main.positions.Position;

@FunctionalInterface
public interface MaterialObject extends Seenable{
    void setPosition(Position p);
}
