package main.items;

import main.other.MaterialObject;
import main.positions.Position;
import main.other.Seenable;

import java.util.Objects;

public abstract class Item implements Seenable, MaterialObject {
    private final int id = (int) (Math.random() * 10000);
    protected String name;
    private Position position;

    protected Item(String name) {
        this.name = name;
    }

    public static class SimpleItem extends Item {
        public SimpleItem(String name) {
            super(name);
        }

        public static class TransparentItem extends SimpleItem {
            public TransparentItem(String name) {
                super(name);
            }
            public static class Window extends TransparentItem {
                public Window(String name) {
                    super(name);
                }
            }
        }
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
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
            return (((Item) b).id == id && Objects.equals(name, b.toString()));
        } else {
            return false;
        }
    }
}
