package main.other;
import main.positions.Space.Planet;

public abstract class OrbitMath {
    public static final double G = 6.6743E-11;
    public static double firstSpaceSpeed(Planet planet){
        return Math.sqrt(2 * G * planet.getMass() / planet.getSize());
    }

    public static double secondSpaceSpeed(Planet planet){
        return firstSpaceSpeed(planet) * Math.sqrt(2);
    }

    @Override
    public int hashCode(){
        return 0;
    }
    @Override
    public boolean equals(Object obj){
        return obj.getClass() == this.getClass();
    }
}
