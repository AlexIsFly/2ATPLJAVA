package carte;

import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 23/10/2016.
 * For Project Java ISSC - IMAG 2016
 */

public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain terrain;

    Case(int ligne, int colonne, NatureTerrain terrain) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.terrain = terrain;
    }

    public Case() {
        this.ligne = 0;
        this.colonne = 0;
        this.terrain = NatureTerrain.EAU;
    }

    public boolean equalsTerrain(String str) {
        return (this.terrain == NatureTerrain.valueOf(str));
    }

    int getLigne() {
        return ligne;
    }

    int getColonne() {
        return colonne;
    }

    NatureTerrain getTerrain() {
        return terrain;
    }
}

