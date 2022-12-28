package main.items.mechanisms.rocket;

import main.alive.Alive;
import main.exceptions.*;
import main.items.Item;
import main.items.mechanisms.Mechanism;
import main.items.mechanisms.StatusOfMechanism;
import main.positions.Location;
import main.other.*;
import main.positions.Place;
import main.positions.Position;
import main.positions.Space.Planet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static main.positions.Space.space;

public class Rocket extends Mechanism {
    private StatusOfRocket rocketStatus;
    private int speed;
    private final FlightComputer flightComputer;
    private Position position;
    private final ArrayList<Module> innerModules = new ArrayList<Module>();
    private static Engine mainEngine;
    private static Engine thrusterEngine;
    private final ArrayList<Alive> crew = new ArrayList<Alive>();

    public Rocket(String name, StatusOfRocket rocketStatus, Location position) {
        super(name, StatusOfMechanism.WORK);
        mainEngine = new Engine("Главный двигатель");
        thrusterEngine = new Engine("Поворотный двигатель");
        this.rocketStatus = rocketStatus;
        flightComputer = new FlightComputer();
        this.position = position;
        if (rocketStatus == StatusOfRocket.CRASHED) {
            setStatus(StatusOfMechanism.BROKEN);
            flightComputer.setStatus(StatusOfMechanism.BROKEN);
        }
    }

    public class Engine extends Mechanism{

        private Engine(String name) {
            super(name, StatusOfMechanism.DISABLED);
        }


        private void engineStatusPrints(StatusOfMechanism status) {
            if (status == StatusOfMechanism.WORK) {
                System.out.println(flightComputer + " включила " + this);
            } else {
                if (status == StatusOfMechanism.DISABLED) {
                    System.out.println(flightComputer + " выключила " + this);
                } else {
                    System.out.println(this + " сломался");
                }
            }
        }

        @Override
        public void setStatus(StatusOfMechanism status){
            mechanismStatus = status;
            engineStatusPrints(status);
        }

        @Override
        public void broke(){
            warning(this);
            setStatus(StatusOfMechanism.BROKEN);
            engineStatusPrints(StatusOfMechanism.BROKEN);
        }

        Hearable engineSound = new Hearable() {
            @Override
            public String getSound() {
                return "Чаф-чаф-чаф-чаф!";
            }
        };

        public Hearable getSound() {
            if (getStatus() == StatusOfMechanism.WORK) {
                return engineSound;
            } else {
                return new Hearable(){
                    @Override
                    public String getSound(){
                        return "";
                    }
                };
            }
        }
    }

    public class FlightComputer extends Mechanism {
        private Planet planet1;
        private Planet planet2;

        private FlightComputer() {
            super("Электронная управляющая машина", StatusOfMechanism.WORK);
        }

        public PlanetGetter getRoute() {
            class Route implements PlanetGetter{
                private final Planet planet1, planet2;
                private final String name;

                public Route(Planet planet1, Planet planet2) {
                    this.name = planet1.toString() + " --> " + planet2.toString();
                    this.planet1 = planet1;
                    this.planet2 = planet2;
                }

                @Override
                public Planet[] getPlanets() {
                    Planet[] planets = new Planet[2];
                    planets[0] = planet1;
                    planets[1] = planet2;
                    return planets;
                }
                @Override
                public String toString(){
                    return name;
                }
            }
            return new Route(planet1, planet2);
        }

        public void setRoute(Planet planet1, Planet planet2) {
            this.planet1 = planet1;
            this.planet2 = planet2;
        }
    }

    public class Module extends Place {
        private final int id = (int) (Math.random() * 1000);
        private final ArrayList<Item> items = new ArrayList<Item>();
        private final ArrayList<Alive> crew = new ArrayList<Alive>();

        public Module(String name) {
            super(name);
        }

        public void addCrew(Alive... h) {
            crew.addAll(Arrays.stream(h).toList());
            for (Alive alive : h) {
                alive.setPosition(this);
            }
        }

        public void deleteCrew(Alive... h) {
            crew.remove(h);
        }

        public void addItem(Item... item) {
            items.addAll(Arrays.stream(item).toList());
            for (Item value : item) {
                value.setPosition(this);
            }
        }

        public List<Alive> getCrew() {
            return crew.stream().toList();
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public boolean equals(Object b) {
            if (b == this) {
                return true;
            } else if (b == null) {
                return false;
            } else if (b.getClass() == this.getClass()) {
                return (((Module) b).id == id && Objects.equals(name, b.toString()));
            } else {
                return false;
            }
        }
    }

    public Engine getMainEngine(){
        return Rocket.mainEngine;
    }

    public Engine getThrusterEngine(){
        return Rocket.thrusterEngine;
    }


    public void crash() {
        rocketStatus = StatusOfRocket.CRASHED;
        System.out.println(this + " уничтожена!!");
        warning(this);
    }

    public void printStatus() {
        System.out.println(name + " " + rocketStatus +
                "\nЛокация: " + position +
                "\nМаршрут: " + getFlightComputer().getRoute() +
                "\nОтсеки: " + innerModules +
                "\nДвигатели: " + mainEngine + "\n\t\t" + thrusterEngine +
                "\nЭкипаж: " + crew);
    }

    public void addModule(Module... m) {
        innerModules.addAll(Arrays.stream(m).toList());
        for (Module module : m) {
            crew.addAll(module.crew);
        }
    }

    public void mainEngineON() throws MechanismIsBrokenException {
        if (mainEngine.getStatus() != StatusOfMechanism.BROKEN) {
            mainEngine.setStatus(StatusOfMechanism.WORK);
            System.out.println(mainEngine + " " + mainEngine.getStatus());
            for (Alive alive : crew) {
                alive.hear(mainEngine.engineSound);
            }
        } else {
            throw new MechanismIsBrokenException(mainEngine + " сломан");
        }
    }

    public void thrusterEngineON() throws MechanismIsBrokenException {
        if (thrusterEngine.getStatus() != StatusOfMechanism.BROKEN) {
            thrusterEngine.setStatus(StatusOfMechanism.WORK);
            System.out.println(thrusterEngine + " " + thrusterEngine.getStatus());
            for (Alive alive : crew) {
                alive.hear(thrusterEngine.engineSound);
            }
        } else {
            throw new MechanismIsBrokenException(thrusterEngine + " сломан");
        }
    }

    public void mainEngineOFF() {
        if (mainEngine.getStatus() != StatusOfMechanism.BROKEN) {
            mainEngine.setStatus(StatusOfMechanism.DISABLED);
        }
    }

    public void thrusterEngineOFF() {
        if (thrusterEngine.getStatus() != StatusOfMechanism.BROKEN) {
            thrusterEngine.setStatus(StatusOfMechanism.DISABLED);
        }
    }

    public FlightComputer getFlightComputer() {
        return flightComputer;
    }

    public void setMaxSpeed() {
        if (rocketStatus == StatusOfRocket.FLYING) {
            if (flightComputer.planet1.getMass() >= flightComputer.planet2.getMass()) {
                speed = (int) OrbitMath.secondSpaceSpeed(flightComputer.planet1);
            } else {
                speed = (int) OrbitMath.secondSpaceSpeed(flightComputer.planet2);
            }
            System.out.println(name + " достигла максимальной скорости");
            mainEngineOFF();
            rocketStatus = StatusOfRocket.INERTIAL_FLYING;
            System.out.println("Все двигатели выключены\n" +
                    this + " " + rocketStatus);
        }
    }

    public void changeSpeed(int endSpeed)
            throws InterruptedException, VarIsNotPositiveException {
            if (endSpeed < 0){
                throw new VarIsNotPositiveException("Конечная скорость " + this + " отрицательная!");
            }
            try {
                mainEngineON();
                System.out.println("Скорость: " + speed + "m/s");
                int deltaSpeed = Math.abs(speed - endSpeed) / 11;
                if (endSpeed < speed) {
                    for (int i = 10; i >= 0; i--) {
                        Thread.sleep(deltaSpeed / 2);
                        speed = deltaSpeed * i;
                        System.out.println("Скорость: " + speed + "m/s");
                    }
                } else {
                    for (int i = 0; i <= 10; i++) {
                        Thread.sleep(deltaSpeed / 2);
                        speed = deltaSpeed * i;
                        System.out.println("Скорость: " + speed + "m/s");
                    }
                }
                mainEngineOFF();
            } catch (MechanismIsBrokenException e){
                System.out.println(e.getMessage());
            }
    }

    public void launchOnOrbit()
            throws InterruptedException, RocketIsAlreadyInSpaceException {
        if (rocketStatus == StatusOfRocket.LANDED) {
            changeSpeed((int) OrbitMath.firstSpaceSpeed((Planet) position));
            rocketStatus = StatusOfRocket.FLYING;
            position = space;
        } else {
            throw new RocketIsAlreadyInSpaceException();
        }
    }

    public void brake(int endSpeed)
            throws InterruptedException, SpeedIsAlreadyNull {
        if (speed != 0) {
            System.out.println(this + " летит, повернутая против движения");
            changeSpeed(endSpeed);
        } else {
            throw new SpeedIsAlreadyNull();
        }
    }

    public void land()
            throws InterruptedException, RocketIsAlreadyLandedException {
        if (rocketStatus != StatusOfRocket.LANDED) {
            try {
                brake(0);
                if (speed == 0) {
                    rocketStatus = StatusOfRocket.LANDED;
                    position = flightComputer.planet2;
                    System.out.println(this + " приземлилась на " + position);
                } else {
                    crash();
                    System.out.println(this + " врезалась в " + flightComputer.planet2);
                }
            } catch (SpeedIsAlreadyNull e){
                System.out.println(e.getMessage());
            }
        } else {
            throw new RocketIsAlreadyLandedException();
        }
    }

    private Planet[] getSeenablePlanets() {
        return flightComputer.getRoute().getPlanets();
    }

    public void turnAround() throws InterruptedException{
        if (rocketStatus == StatusOfRocket.FLYING || rocketStatus == StatusOfRocket.INERTIAL_FLYING) {
            try {
                thrusterEngineON();
                Thread.sleep(500);
                System.out.println(name + " поворачивается");
                for (Alive alive : crew) {
                    PhysicalForces.centrifugalForce(alive);
                }
                System.out.println(getSeenablePlanets()[0].toString() + " и " + getSeenablePlanets()[1].toString() + " вращаются");
                Thread.sleep(500);
                thrusterEngineOFF();
            } catch (MechanismIsBrokenException e){
                System.out.println(e.getMessage());
            }
        }
    }
}