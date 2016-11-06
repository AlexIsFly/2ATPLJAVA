package io;

import carte.Carte;
import robot.Robots;

/**
 * Created by alexisgacel on 26/10/2016.
 */
public class DonneesSimulation {
    private Carte carte;
    private Robots[] robotL;
    /*
    please add the others instance of Robots+Incendies as attributes
    and add it to the constructor below
    */
    public DonneesSimulation(Carte map, Robots[] robotL) {
        this.carte = map;
        this.robotL = robotL;
    }

    public void printMap() {
        this.carte.printMap();
    }

    public Carte getCarte() {
        return carte;
    }
    public Robots[] getRobotL() {
        return this.robotL;
    }
}
