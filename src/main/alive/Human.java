package main.alive;

import main.items.mechanisms.rocket.Rocket;
import main.other.Hearable;
import main.other.MaterialObject;
import main.positions.Position;
import main.other.Seenable;
import main.items.*;

import java.util.Objects;

public class Human implements Alive, Seenable {
    private final int id = (int) (Math.random() * 100);
    private final String name;
    private StatusOfHuman status;
    private Position position;

    public Human(String name, StatusOfHuman status) {
        this.name = name;
        this.status = status;
    }

    public void sleep() {
        status = StatusOfHuman.SLEEP;
        closeEyes();
        System.out.println(this + " заснул");
    }

    public void wakeUp() {
        status = StatusOfHuman.AWAKE;
        System.out.println(this + " проснулся");
        openEyes();
    }

    public void think(String idea) {
        System.out.println(this + " подумал, что " + idea);
    }

    public void rejoice(String aboutWhat) {
        System.out.println(this + " обрадовался, что " + aboutWhat);
    }

    public void understand(String what, boolean success) {
        if (success) {
            System.out.println(this + " понял, что " + what);
        } else {
            System.out.println(this + " не понял, " + what);
        }
    }

    public void exclaim(){
        System.out.println(this + " взвизгнул");
    }

    public void closeEyes(){
        System.out.println(this + " закрыл глаза");
    }

    public void openEyes(){
        System.out.println(this + " открыл глаза");
    }

    public void watch(){
        System.out.println(this + " ничего не увидел");
    }

    public void watch(Seenable onWhat) {
        System.out.println(this + " смотрит на " + onWhat);
    }

    public void watch(Seenable onWhat, Item.SimpleItem.TransparentItem throughWhat) {
        if (throughWhat.getPosition().equals(position)) {
            System.out.println(this + " смотрит через " + throughWhat + " на " + onWhat);
        }
    }

    public void watch(Item.SimpleItem.TransparentItem.Window w) {
        if (w.getPosition().equals(position)) {
            System.out.println(this + " смотрит в " + w);
        }
    }

    public void hear(Hearable sound) {
        if (sound.getSound().equals("")) {
            System.out.println(this + " ничего не слышит");
        } else {
            System.out.println(this + " слышит: \"" + sound.getSound() + "\"");
        }
    }

    @Override
    public void setPosition(Position p) {
        position = p;
    }

    public void goTo(Position p) {
        if (status == StatusOfHuman.AWAKE) {
            if (p != position){
                System.out.println(this + " перешел из " + position + " в " + p);
                ((Rocket.Module) (position)).deleteCrew(this);
                position = p;
                ((Rocket.Module) (position)).addCrew(this);
            }
        }
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
            return (((Human) b).id == id && Objects.equals(name, b.toString()));
        } else {
            return false;
        }
    }
}
