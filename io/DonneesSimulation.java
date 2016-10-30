package io;

import carte.Carte;

/**
 * Created by alexisgacel on 26/10/2016.
 */
public class DonneesSimulation {
    private Carte carte;
    /*
    please add the others instance of Robots+Incendies as attributes
    and add it to the constructor below
    */
    public DonneesSimulation(Carte map) {
        this.carte = map;
    }

    public void printMap() {
        this.carte.printMap();
    }

    public Carte getCarte() {
        return carte;
    }
}
