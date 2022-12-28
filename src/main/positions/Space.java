package main.positions;

import main.exceptions.VarIsNotPositiveException;
import main.other.Seenable;

public class Space extends Location {
    private Space() {
        super("космос");
    }
    public static Space space = new Space();

    public class Planet extends Location implements Seenable {
        private final double size;
        private final double mass;

        public Planet(String name, int size, int mass){
            super(name);
            if (size <= 0){
                throw new VarIsNotPositiveException("Диаметр " + this + " неположителен!");
            } else if (mass <= 0) {
                throw new VarIsNotPositiveException("Масса " + this + " неположительна!");
            } else {
                this.size = size * 10E2;
                this.mass = mass * 10E19;
            }
        }

        public double getSize(){
            return size;
        }

        public double getMass(){
            return mass;
        }
    }

    @Override
    public int hashCode() {
        return -1;
    }

    @Override
    public boolean equals(Object b) {
        return (b.getClass() == this.getClass());
    }
}