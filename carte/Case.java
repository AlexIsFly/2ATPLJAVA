package carte;

import enumdata.NatureTerrain;

/**
 * Created by Riffard - Gacel - Dorr
 * For Project Java ISSC - IMAG 2016
 */

public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain terrain;
    private boolean incendie;
    private boolean incendieAffected;
    private int qteEau;

    /**
     * Constructor for Case
     * @param ligne
     * @param colonne
     * @param terrain
     */
    Case(int ligne, int colonne, NatureTerrain terrain) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.terrain = terrain;
        this.incendie = false;
        this.incendieAffected = false;
        this.qteEau = 0;
    }

    public Case() {
        this.ligne = 0;
        this.colonne = 0;
        this.terrain = NatureTerrain.EAU;
        this.incendie = false;
        this.incendieAffected = false;
        this.qteEau = 0;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public NatureTerrain getTerrain() {
        return terrain;
    }

    public boolean isIncendie() {
        return incendie;
    }

    public boolean isIncendieAffected() {
        return incendieAffected;
    }

    public int getQteEau() {
        return qteEau;
    }


    public void setIncendieAffected(boolean incendieAffected) {
        this.incendieAffected = incendieAffected;
    }

    void setIncendie(boolean incendie) {
        this.incendie = incendie;
    }

    public void setQteEau(int qteEau) {
        this.qteEau = qteEau;
    }

    public int[] getCoord() {
        int[] coord = new int[2];
        coord[0] = this.ligne;
        coord[1] = this.colonne;
        return coord;
    }


    /**
     * @param str String corresponding of the terrain name
     * @return True if the terrain is the one specified by str, false otherwise
     */
    public boolean equalsTerrain(String str) {
        return (this.terrain == NatureTerrain.valueOf(str));
    }

    /**
     * @return formatted string description of a Case
     */
    @Override
    public String toString() {
        return "Case{" +
                "coord=" + "(" + getCoord()[0] + ", " + getCoord()[1] + ")" +
                ", terrain=" + terrain +
                ", incendie=" + incendie +
                ", intensité=" + qteEau +
                '}';
    }
}

