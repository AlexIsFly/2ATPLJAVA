package carte;

import enumdata.NatureTerrain;

/**
 * Created by alexisgacel on 23/10/2016.
 */

public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain terrain;

    public Case(int ligne, int colonne, NatureTerrain terrain) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.terrain = terrain;
    }

    public Case() {
        this.ligne = 0;
        this.colonne = 0;
        this.terrain = NatureTerrain.EAU;
    }

    public NatureTerrain getTerrain() {
        return terrain;
    }
}

