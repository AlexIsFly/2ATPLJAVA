package io;

import carte.Carte;
import robot.Robots;

/**
 * Created by alexisgacel on 26/10/2016.
 */
public class DonneesSimulation {
    private Carte carte;
    private Robots[] robotL;

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
