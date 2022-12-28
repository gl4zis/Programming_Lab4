package main;


import main.alive.Human;
import main.alive.StatusOfHuman;
import main.exceptions.RocketIsAlreadyLandedException;
import main.items.Item;
import main.items.mechanisms.rocket.Rocket;
import main.items.mechanisms.rocket.StatusOfRocket;
import main.positions.Space.Planet;

import static main.positions.Space.space;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        Human dunno = new Human("Незнайка", StatusOfHuman.SLEEP);
        Human donut = new Human("Пончик", StatusOfHuman.SLEEP);
        Rocket rocket = new Rocket("Ракета", StatusOfRocket.FLYING, space);
        Planet earth = space.new Planet("Земля", 12742, 59722);
        Planet mun = space.new Planet("Луна", 3474, 735);
        rocket.getFlightComputer().setRoute(earth, mun);
        Rocket.Module lowerModule = rocket.new Module("Нижний отсек");
        Rocket.Module upperModule = rocket.new Module("Верхний отсек");
        Item bed = new Item.SimpleItem("Спальное место");
        Item.SimpleItem.TransparentItem.Window window = new Item.SimpleItem.TransparentItem.Window("иллюминатор");
        lowerModule.addItem(bed, rocket.getMainEngine(), rocket.getThrusterEngine());
        lowerModule.addCrew(dunno, donut);
        upperModule.addItem(window);
        rocket.addModule(lowerModule, upperModule);

        rocket.printStatus();
        System.out.println();

        Thread.sleep(2000);

        rocket.setMaxSpeed();
        System.out.println();

        Thread.sleep(2000);

        donut.wakeUp();
        dunno.wakeUp();
        System.out.println();

        Thread.sleep(2000);

        donut.hear(rocket.getMainEngine().getSound());
        dunno.hear(rocket.getMainEngine().getSound());
        System.out.println();

        Thread.sleep(2000);

        dunno.goTo(upperModule);
        donut.think("двигатель испортился");
        donut.rejoice("ракета возвращается");
        donut.goTo(upperModule);
        System.out.println();

        Thread.sleep(2000);

        dunno.understand("остановилась ли ракета", false);
        donut.understand("остановилась ли ракета", false);
        System.out.println();

        Thread.sleep(2000);

        rocket.turnAround();
        dunno.think(rocket + " столкнулась с " + mun);
        donut.think(rocket + " столкнулась с " + mun);
        dunno.exclaim();
        donut.exclaim();
        donut.watch(mun, window);
        dunno.watch(mun, window);
        System.out.println();

        Thread.sleep(3000);

        donut.closeEyes();
        Thread.sleep(2000);
        donut.openEyes();
        donut.watch(window);
        donut.watch();
        donut.think(rocket + " врезалась в " + mun + " и расколола ее на кусочки, которые превратились в звездочки");
        System.out.println();

        Thread.sleep(5000);

        try {
            rocket.land();
        } catch (RocketIsAlreadyLandedException e){
            System.out.println(e.getMessage());
        }
    }
}