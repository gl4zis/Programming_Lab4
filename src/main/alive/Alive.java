package main.alive;

import main.other.Hearable;
import main.other.MaterialObject;

public interface Alive extends MaterialObject {
    void hear(Hearable sound);
}
