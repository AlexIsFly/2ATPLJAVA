package io;

import carte.Carte;

/**
 * Created by alexisgacel on 26/10/2016.
 */
public class DonneesSimulation {
    private Carte map;
    /*
    please add the others instance of Robots+Incendies as attributes
    and add it to the constructor below
    */
    public DonneesSimulation(Carte map) {
        this.map = map;
    }

    public void printMap() {
        this.map.printMap();
    }
}
