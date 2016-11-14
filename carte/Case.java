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
    private boolean incendie;
    private boolean incendieAffected;
    private int qteEau;

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

    public boolean isIncendieAffected() {
        return incendieAffected;
    }

    public void setIncendieAffected(boolean incendieAffected) {
        this.incendieAffected = incendieAffected;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public int[] getCoord() {
        int[] coord = new int[2];
        coord[0] = this.ligne;
        coord[1] = this.colonne;
        return coord;
    }

    public NatureTerrain getTerrain() {
        return terrain;
    }

    public boolean isIncendie() {
        return incendie;
    }

    void setIncendie(boolean incendie) {
        this.incendie = incendie;
    }

    public void setQteEau(int qteEau) {
        this.qteEau = qteEau;
    }

    public int getQteEau() {
        return qteEau;
    }

    //usage : case.equalsTerrain('EAU') renvoie true si case est de type EAU
    //les types de terrain sont dans enumdata->NatureTerrain
    public boolean equalsTerrain(String str) {
        return (this.terrain == NatureTerrain.valueOf(str));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Case aCase = (Case) o;

        if (ligne != aCase.ligne) return false;
        return colonne == aCase.colonne;

    }

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

