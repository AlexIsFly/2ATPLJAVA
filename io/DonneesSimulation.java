package io;

import carte.Carte;
import robot.Robots;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */
public class DonneesSimulation {
    private Carte carte;
    private Robots[] robotL;

    /**
     * Constructor of DonneesSimulation which holds important simulation data
     * @param map
     * @param robotL the list of robots present ont the map
     */
    public DonneesSimulation(Carte map, Robots[] robotL) {
        this.carte = map;
        this.robotL = robotL;
    }

    /**
     * Getter for robotL
     * @return the list of robot
     */
    public Robots[] getRobotL() {
        return this.robotL;
    }

    /**
     * Getter for the map
     * @return the map
     */
    public Carte getCarte() {
        return carte;
    }


    /**
     * Prints an ASCII map to stdout
     * For testing only
     */
    public void printMap() {
        this.carte.printMap();
    }
}
